package tipqc.cite.techproject.magnacarta.iwatch.Profile;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.MainActivity;
import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Login_Activity extends Activity {

    Button btnLogin;
    EditText etEmail, etPword;
    String uid, email, pword, fname, mname, lname, date_created, mobile, address, birthdate, points;
    TextView tvSignup;
    AlertDialog.Builder alertDialog;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String url_user_detials = "http://iwatchpcw.hostoi.com/login_mobile.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USER = "user";
    private static final String TAG_UID = "uid";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PWORD = "pword";
    private static final String TAG_FNAME = "fname";
    private static final String TAG_MNAME = "mname";
    private static final String TAG_LNAME = "lname";
    private static final String TAG_DATE_CREATED = "date_created";
    private static final String TAG_MOBILE = "mobile";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_BIRTHDATE = "birthdate";
    private static final String TAG_POINTS = "points";

    String password_from_cp;
    CheckBox mCbShowPwd;
    CheckBox cbRemember;
    SharedPreferences sp;
    public static String file = "Files";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login_layout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sp = getSharedPreferences(file,0);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPword = (EditText) findViewById(R.id.etPword);
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        tvSignup = (TextView) findViewById(R.id.tvSignup);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);


        etEmail.setText(sp.getString("storedemail", ""));
        etPword.setText(sp.getString("storedpw", ""));
        cbRemember.setChecked(sp.getBoolean("storedcheck", false));

        mCbShowPwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    etPword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    etPword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences.Editor ed = sp.edit();
                ed.putString("storedemail", etEmail.getText().toString());
                ed.putString("storedpword", etPword.getText().toString());
                ed.commit();

                email = etEmail.getText().toString();
                pword = etPword.getText().toString();

                if(etEmail.getText().toString().isEmpty() || etPword.getText().toString().isEmpty()){
                    alertDialog = new AlertDialog.Builder(Login_Activity.this);
                    alertDialog.setTitle("Insufficient Input");
                    alertDialog.setMessage("Please fill up all fields");
                    alertDialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                            try{
                                Intent i = new Intent(getApplicationContext(), Registration_Activity.class);
                                startActivity(i);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            finish();
                        }
                    });
                    alertDialog.setNegativeButton("Login",  new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                        }
                    });
                    alertDialog.show();
                }else{

                    new UserDetails().execute();

                    if(cbRemember.isChecked()){
                        ed.putString("storedemail", etEmail.getText().toString());
                        ed.putString("storedpword", etPword.getText().toString());
                        ed.putBoolean("storedcheck", true);
                        ed.commit();
                    }else{
                        ed.putString("storedemail", "");
                        ed.putString("storedpword", "");
                        ed.putBoolean("storedcheck", false);
                        ed.commit();
                    }

                }//

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), Registration_Activity.class);
                startActivity(i);

            }
        });
    }



    @Override
    public void onBackPressed(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }



    class UserDetails extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login_Activity.this);
            pDialog.setMessage("Loading user details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting user details in background thread
         * */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
            //Thread thread = new Thread() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {

                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("email", email));
                        params.add(new BasicNameValuePair("pword", pword));

                        // getting user details by making HTTP request
                        // Note that user details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_user_detials, "GET", params);

                        // check your log for json response
                        Log.d("Single User Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received User details
                            JSONArray userObj = json
                                    .getJSONArray(TAG_USER); // JSON Array

                            // get first USER object from JSON Array
                            JSONObject user = userObj.getJSONObject(0);
                            uid = user.getString(TAG_UID);
                            email = user.getString(TAG_EMAIL);
                            pword = user.getString(TAG_PWORD);
                            fname = user.getString(TAG_FNAME);
                            mname = user.getString(TAG_MNAME);
                            lname = user.getString(TAG_LNAME);
                            //date_created = user.getString(TAG_DATE_CREATED);
                            mobile = user.getString(TAG_MOBILE);
                            address = user.getString(TAG_ADDRESS);
                            birthdate = user.getString(TAG_BIRTHDATE);
                            points = user.getString(TAG_POINTS);


                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("storeduid",uid);
                            ed.putString("storedemail", etEmail.getText().toString());
                            ed.putString("storedpword", etPword.getText().toString());
                            ed.putString("storedfname",fname);
                            ed.putString("storedmname",mname);
                            ed.putString("storedlname",lname);
                            //ed.putString("storeddatecreated",date_created);
                            ed.putString("storedmobile",mobile);
                            ed.putString("storedaddress",address);
                            ed.putString("storedbdate",birthdate);
                            ed.putString("storedpoints",points);
                            ed.commit();


                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                        }else{
                            // user with uname not found
                            Toast.makeText(Login_Activity.this,"User doesn't exist", Toast.LENGTH_SHORT).show();
                            // Starting new intent
                            Intent in = new Intent(getApplicationContext(),
                                    Login_Activity.class);
                            // starting new activity and expecting some response back
                            startActivityForResult(in, 100);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }


}