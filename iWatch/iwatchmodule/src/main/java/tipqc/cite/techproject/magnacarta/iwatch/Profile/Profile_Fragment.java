package tipqc.cite.techproject.magnacarta.iwatch.Profile;


        import java.util.ArrayList;
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
        import android.text.Editable;
        import android.text.InputType;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import tipqc.cite.techproject.magnacarta.R;
        import tipqc.cite.techproject.magnacarta.iwatch.MainActivity;
        import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;

public class Profile_Fragment extends Activity {

    Button btnEdit, btnBack;
    TextView tvEmail, tvName, tvMobile, tvAddress, tvBdate, tvAge, tvPoints;
    String uid, email, pword, fname, mname, lname, name, date_created, mobile, address, birthdate, points;
    String pword3;
    SharedPreferences sp;
    public static String file = "Files";
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String url_user_detials = "http://iwatchpcw.hostoi.com/login_mobile2.php";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        sp = getSharedPreferences(file,0);

        btnEdit = (Button) findViewById(R.id.btnEdit);


        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvName = (TextView) findViewById(R.id.tvName);
        tvMobile = (TextView) findViewById(R.id.tvMobile);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvBdate = (TextView) findViewById(R.id.tvBdate);
        tvPoints = (TextView) findViewById(R.id.tvPoints);


        SharedPreferences.Editor ed = sp.edit();
        uid = sp.getString("storeduid", "");
        email = sp.getString("storedemail", "");
        pword = sp.getString("storedpword", "");
        fname = sp.getString("storedfname", "");
        mname = sp.getString("storedmname", "");
        lname = sp.getString("storedlname", "");
        date_created = sp.getString("storeddatecreated", "");
        mobile = sp.getString("storedmobile", "");
        address = sp.getString("storedaddress", "");
        birthdate = sp.getString("storedbdate", "");
        points = sp.getString("storedpoints", "");


        tvEmail.setText("Email:"+"\n\t"+email);
        tvName.setText("Name:"+"\n\t"+lname+", "+fname+" "+mname);
        tvMobile.setText("Mobile:"+"\n\t"+mobile);
        tvAddress.setText("Address:"+"\n\t"+address);
        tvBdate.setText("Birthdate:"+"\n\t"+birthdate);
        tvPoints.setText("Points:"+"\n\t"+points);

        ed.putString("storedemail", email);
        ed.putString("storedpword", pword);
        ed.commit();

        btnEdit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                editDB();

            }
        });



    }

    public void editDB(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(email);
        alert.setMessage("Type your password");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setSelection(input.getText().length());
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText();
                pword = value.toString();
                // Do something with value!
                new UserDetails().execute();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    class UserDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile_Fragment.this);
            pDialog.setMessage("Please wait...");
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
                            pword = user.getString(TAG_PWORD);


                            Intent i = new Intent(getApplicationContext(), ProfileEdit.class);
                            startActivity(i);
                            finish();

                        }else{
                            // user with uname not found
                            Toast.makeText(Profile_Fragment.this,"Wrong Password", Toast.LENGTH_SHORT).show();
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