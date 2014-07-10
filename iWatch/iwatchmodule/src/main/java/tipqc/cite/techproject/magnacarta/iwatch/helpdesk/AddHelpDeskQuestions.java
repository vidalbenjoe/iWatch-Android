package tipqc.cite.techproject.magnacarta.iwatch.helpdesk;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;

//import android.widget.DatePicker;

public class AddHelpDeskQuestions extends Activity {


    EditText Question;
    String uid, fname, mname, lname, qname;
    SharedPreferences sp;
    public static String file = "Files";

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();


    // url to create new product
    private static String url_add_database = "http://iwatchpcw.hostoi.com/add_question.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpdesk_add_question);

        sp = getSharedPreferences(file,0);

        Question = (EditText) findViewById(R.id.Question);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        SharedPreferences.Editor ed = sp.edit();
        uid = sp.getString("storeduid", "");
        fname = sp.getString("storedfname", "");
        mname = sp.getString("storedmname", "");
        lname = sp.getString("storedlname", "");
        qname = lname+", "+fname+" "+mname;

        // button click event
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // adding data in background thread
                new AddingData().execute();


            }
        });

    }


//	}
    /**
     * Background Async Task to add text
     * */
    class AddingData extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddHelpDeskQuestions.this);
            pDialog.setMessage("Sending Question..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Adding do database
         * */
        protected String doInBackground(String... args) {

            String question = Question.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("question", question));
            params.add(new BasicNameValuePair("uid", uid));
            params.add(new BasicNameValuePair("qname", qname));
            JSONObject json = jsonParser.makeHttpRequest(url_add_database,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(AddHelpDeskQuestions.this, "Question Sent Succesfully - PCW will review your question if valid", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent i = new Intent(getApplicationContext(), ViewHelpDeskQuestions.class);
                    // Toast.makeText(getBaseContext(),"Sent",Toast.LENGTH_SHORT).show();
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(AddHelpDeskQuestions.this, "Message Not Sent, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
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
        }

    }
    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}