package tipqc.cite.techproject.magnacarta.iwatch.helpdesk;


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.AlertDialog;
        import android.app.ListActivity;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.Button;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import tipqc.cite.techproject.magnacarta.R;
        import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;


public class ViewHelpDeskAnswers extends ListActivity implements View.OnClickListener{

    TextView tvPost_id, tvEdit, tvDelete, tvQuestion;

    //should be from sharedPref
    String pst_uid, qid, answer, ans_by, question;
    SharedPreferences sp;
    public static String file = "Files";

    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();//for delete post

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();//for get all posts

    ArrayList<HashMap<String, String>> postsLists;

    // url to get all posts list
    private static String url_posts = "http://iwatchpcw.hostoi.com/view_answer.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ANSWERS = "answers";
    private static final String TAG_QID = "qid";

    private static final String TAG_ANS_BY = "ans_by";// name of user who commented on a questions
    private static final String TAG_DATE_ANS = "date_ans";//date of comment
    private static final String TAG_ANSWER = "answer"; // their answer

    //  JSONArray
    JSONArray answers = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_answer);
        sp = getSharedPreferences(file,0);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        // getting product details from intent
        Intent i = getIntent();

        // getting product id (pid) from intent
        qid = i.getStringExtra(TAG_QID);

        SharedPreferences.Editor ed = sp.edit();
        question = sp.getString("storedquestion", question);
        tvQuestion.setText("Question:\n"+question);
        // Hashmap for ListView
        postsLists = new ArrayList<HashMap<String, String>>();




        // Loading posts in Background Thread
        new LoadAllposts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single user
        // launching Edit user Screen

    }
    //Toast.makeText(TicTacToe.this,""+myL, Toast.LENGTH_SHORT).show();


    // Response from Edit user Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted user
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /**
     * Background Async Task to Load all user by making HTTP Request
     * */
    class LoadAllposts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ViewHelpDeskAnswers.this);
            pDialog.setMessage("Loading Answers. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }



        /**
         * getting All posts from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("qid", qid));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_posts, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Posts: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // posts found
                    // Getting Array of posts
                    answers = json.getJSONArray(TAG_ANSWERS);

                    // looping through All posts
                    for (int i = 0; i < answers.length(); i++) {
                        JSONObject c = answers.getJSONObject(i);

                        // Storing each json item in variable

                        //qid = "2";
                        String ans_by = c.getString(TAG_ANS_BY);
                        String date_ans = c.getString(TAG_DATE_ANS);
                        String answer = c.getString(TAG_ANSWER);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_QID, qid);

                        map.put(TAG_ANS_BY, ans_by);
                        map.put(TAG_DATE_ANS, date_ans);
                        map.put(TAG_ANSWER, answer);
                        // adding HashList to ArrayList
                        postsLists.add(map);
                    }
                } else {

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
            // dismiss the dialog after getting all posts
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            ViewHelpDeskAnswers.this, postsLists,
                            R.layout.list_answer, new String[] {TAG_QID,TAG_ANS_BY
                            , TAG_DATE_ANS, TAG_ANSWER},
                            new int[] { R.id.tvPost_id, R.id.answer_by,  R.id.date_answer, R.id.answer });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }//end of get all posts class

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }

}