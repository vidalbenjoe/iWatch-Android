package tipqc.cite.techproject.magnacarta.iwatch.Profile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.activity.RegSignDahboardActivity;
import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;


public class Registration_Activity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText etEmail, etPword1, etPword2, etFname, etMname, etLname, etMobile, etAddress, etBdate;
    Button btnRegister, btnCancel;
    AlertDialog.Builder alertDialog;

    // url to create new product
    private static String url_create_user = "http://iwatchpcw.hostoi.com/signup_ac.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        // Edit Text
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPword1 = (EditText) findViewById(R.id.etPword1);
        etPword2 = (EditText) findViewById(R.id.etPword2);
        etFname = (EditText) findViewById(R.id.etFname);
        etMname = (EditText) findViewById(R.id.etMname);
        etLname = (EditText) findViewById(R.id.etLname);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etBdate = (EditText) findViewById(R.id.etBdate);


        // Create button
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);


        // button click event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().isEmpty() || etPword1.getText().toString().isEmpty() ||
                        etPword2.getText().toString().isEmpty() || etFname.getText().toString().isEmpty() ||
                        etMname.getText().toString().isEmpty() || etLname.getText().toString().isEmpty() ||
                        etMobile.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty() ||
                        etBdate.getText().toString().isEmpty()){
                    alertDialog = new AlertDialog.Builder(Registration_Activity.this);
                    alertDialog.setMessage("You must fill in all of the fields.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                        }
                    });
                    alertDialog.show();
                }else if(etPword1.getText().toString().length()<8){
                    alertDialog = new AlertDialog.Builder(Registration_Activity.this);
                    alertDialog.setMessage("Password must be at least 8 characters.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            etPword1.setText("");
                            etPword2.setText("");
                            etPword1.requestFocus();
                        }
                    });
                    alertDialog.show();
                }else if((etMobile.getText().toString().length()<11)||(etMobile.getText().toString().length()>11)){
                    alertDialog = new AlertDialog.Builder(Registration_Activity.this);
                    alertDialog.setMessage("Enter your 11-digit mobile number");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            etMobile.setText("");
                            etMobile.requestFocus();
                        }
                    });
                    alertDialog.show();
                }else if((etMobile.getText().toString().charAt(0)!='0')&&(etMobile.getText().toString().charAt(1)!='9')){
                    alertDialog = new AlertDialog.Builder(Registration_Activity.this);
                    alertDialog.setMessage("Your mobile number should start at 09");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            etMobile.setText("");
                            etMobile.requestFocus();
                        }
                    });
                    alertDialog.show();
                }

                else{
                    //new CreateNewUser().execute();
                    if(etPword1.getText().toString().contentEquals(etPword2.getText().toString())){

                        new CreateNewUser().execute();

                    }else{
                        alertDialog = new AlertDialog.Builder(Registration_Activity.this);
                        alertDialog.setMessage("Password did not match.");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which) {
                                etPword1.setText("");
                                etPword2.setText("");
                                etPword1.requestFocus();
                            }
                        });
                        alertDialog.show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                Intent i = new Intent(getApplicationContext(), RegSignDahboardActivity.class);
                startActivity(i);

                // closing this screen
                finish();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration_Activity.this);
            pDialog.setMessage("Creating User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating user
         * */
        protected String doInBackground(String... args) {
            String strEmail = etEmail.getText().toString();
            String strPword = etPword1.getText().toString();
            String strFname = etFname.getText().toString();
            String strMname = etMname.getText().toString();
            String strLname = etLname.getText().toString();
            String strMobile = etMobile.getText().toString();
            String strAddress = etAddress.getText().toString();
            String strBdate = etBdate.getText().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", strEmail));
            params.add(new BasicNameValuePair("pword", strPword));
            params.add(new BasicNameValuePair("fname", strFname));
            params.add(new BasicNameValuePair("mname", strMname));
            params.add(new BasicNameValuePair("lname", strLname));
            params.add(new BasicNameValuePair("mobile", strMobile));
            params.add(new BasicNameValuePair("address", strAddress));
            params.add(new BasicNameValuePair("birthdate", strBdate));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    //Intent i = new Intent(getApplicationContext(), Main.class);
                    //startActivity(i);
                    //finish();

                } else {
                    // failed
                    Toast.makeText(getApplicationContext(), "Failed",
                            Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            alertDialog = new AlertDialog.Builder(Registration_Activity.this);
            alertDialog.setTitle("Account Verification");
            alertDialog.setMessage("Please sign in to your Email and verify your account registration before you can login to iWatch.");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which) {
                    Intent i = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
            alertDialog.show();
        }


    }
}