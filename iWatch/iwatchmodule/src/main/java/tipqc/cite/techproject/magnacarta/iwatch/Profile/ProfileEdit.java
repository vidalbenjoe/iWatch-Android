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
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import tipqc.cite.techproject.magnacarta.R;
        import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;


public class ProfileEdit extends Activity {

    Button btnSave, btnCancel;
    EditText etPword1, etPword2;
    String uid, email, pword, pword1, pword2;
    AlertDialog.Builder alertDialog;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String url_update_password = "http://iwatchpcw.hostoi.com/update_password.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USER = "user";
    private static final String TAG_UID = "uid";
    private static final String TAG_PWORD = "pword";

    SharedPreferences sp;
    public static String file = "Files";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.profile_edit);

        sp = getSharedPreferences(file,0);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etPword1 = (EditText) findViewById(R.id.etPword1);
        etPword2 = (EditText) findViewById(R.id.etPword2);

        SharedPreferences.Editor ed = sp.edit();
        uid = sp.getString("storeduid", "");
        email = sp.getString("storedemail", "");
        pword = sp.getString("storedpword", "");


        pword1 = etPword1.getText().toString();
        pword2 = etPword2.getText().toString();

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (etPword1.getText().toString().isEmpty() || etPword2.getText().toString().isEmpty()){

                    alertDialog = new AlertDialog.Builder(ProfileEdit.this);
                    alertDialog.setTitle("Insufficient Input");
                    alertDialog.setMessage("Please fill up all fields");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                        }
                    });
                    alertDialog.show();
                }
                else if(etPword1.getText().toString().length()<8){
                    alertDialog = new AlertDialog.Builder(ProfileEdit.this);
                    alertDialog.setMessage("Password must be at least 8 characters.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            etPword1.setText("");
                            etPword2.setText("");
                            etPword1.requestFocus();
                        }
                    });
                    alertDialog.show();
                }
                else {
                    if (etPword1.getText().toString().contentEquals(etPword2.getText().toString())){
                        new SavePassword().execute();
                    }
                    else{
                        alertDialog = new AlertDialog.Builder(ProfileEdit.this);
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
                Intent i = new Intent(getApplicationContext(), Profile_Fragment.class);
                startActivity(i);
                finish();
            }
        });
    }

    class SavePassword extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProfileEdit.this);
            pDialog.setMessage("Saving changes ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            pword = etPword1.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_UID, uid));
            params.add(new BasicNameValuePair(TAG_PWORD, pword));

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_password,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated

                } else {
                    alertDialog = new AlertDialog.Builder(ProfileEdit.this);
                    alertDialog.setMessage("Failed to changed password.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent i = new Intent(getApplicationContext(), Profile_Fragment.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    alertDialog.show();
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
            // dismiss the dialog once product uupdated
            pDialog.dismiss();
            alertDialog = new AlertDialog.Builder(ProfileEdit.this);
            alertDialog.setMessage("Successfully Changed Password.");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    Intent i = new Intent(getApplicationContext(), Profile_Fragment.class);
                    startActivity(i);
                    finish();
                }
            });
            alertDialog.show();
        }
    }
}