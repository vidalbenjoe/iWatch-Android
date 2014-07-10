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


public class ViewHelpDeskQuestions extends ListActivity implements View.OnClickListener{

    TextView tvPost_id, tvEdit, tvDelete, tvPost_content;
    Button btnAddPost;
    String uid; //should be from sharedPref
    String pst_uid, qid, question;
    SharedPreferences sp;
    public static String file = "Files";

    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();//for delete post

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();//for get all posts

    ArrayList<HashMap<String, String>> postsList;

    // url to get all posts list
    private static String url_posts = "http://iwatchpcw.hostoi.com/view_posts.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_QUESTIONS = "questions";//json node
    private static final String TAG_QID = "qid";//unique id of question
    private static final String TAG_UID = "uid";//user id
    private static final String TAG_QNAME = "qname";//name of user
    private static final String TAG_DATE_POSTED = "date_posted";
    private static final String TAG_QUESTION = "question";//posted questios


    //  JSONArray
    JSONArray questions = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpdesk_questionlayout);
        sp = getSharedPreferences(file,0);
        // Hashmap for ListView
        postsList = new ArrayList<HashMap<String, String>>();


        btnAddPost = (Button) findViewById(R.id.btnAddPost);

        //tvEdit.set
        //tvDelete.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);


        // Loading posts in Background Thread
        new LoadAllposts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single user
        // launching Edit user Screen
        lv.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                /**	uid should be holded in this class via sharedpref
                 *	then compared to the uid retrieved from users table
                 * */

                String qid = ((TextView) view.findViewById(R.id.tvPost_id)).getText()
                        .toString();
                question = ((TextView) view.findViewById(R.id.tvPost_content)).getText()
                        .toString();
                //Toast.makeText(ViewPost.this,""+qid, Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor ed = sp.edit();
                ed.putString("storedquestion", question);
                ed.commit();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        ViewHelpDeskAnswers.class);
                // sending pid to next activity
                in.putExtra(TAG_QID, qid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });
    }
    public void diabox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("Post Option");
        // Setting Dialog Message
        //alertDialog.setMessage("Game Option");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_launcher);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                //go to EditPost class
                qid = ((TextView) findViewById(R.id.tvPost_id)).getText()
                        .toString();
                Toast.makeText(ViewHelpDeskQuestions.this,pst_uid, Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(getApplicationContext(),
                //		EditPost.class);
                // sending uid to next activity
                //i.putExtra(TAG_PST_ID, pst_id);
                // starting new activity and expecting some response back
                //startActivityForResult(i, 100);
                //finish();

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    @Override
    public void onClick(View v){
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.btnAddPost:
                //Toast.makeText(Posts.this,"Add Post", Toast.LENGTH_SHORT).show();
                // Starting new intent
                Intent i = new Intent(getApplicationContext(),
                        AddHelpDeskQuestions.class);
                // starting new activity and expecting some response back
                startActivityForResult(i, 100);
                finish();
                break;
            default:
                break;

        }
    }

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
            pDialog = new ProgressDialog(ViewHelpDeskQuestions.this);
            pDialog.setMessage("Loading Posts. Please wait...");
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
                    questions = json.getJSONArray(TAG_QUESTIONS);

                    // looping through All posts
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject c = questions.getJSONObject(i);

                        // Storing each json item in variable
                        qid = c.getString(TAG_QID);
                        pst_uid = c.getString(TAG_UID);
                        String qname = c.getString(TAG_QNAME);
                        String date_posted = c.getString(TAG_DATE_POSTED);
                        String question = c.getString(TAG_QUESTION);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_QID, qid);
                        map.put(TAG_UID, uid);
                        map.put(TAG_QNAME, qname);
                        map.put(TAG_DATE_POSTED, date_posted);
                        map.put(TAG_QUESTION, question);
                        // adding HashList to ArrayList
                        postsList.add(map);
                    }
                } else {
                    // no posts found
                    // Launch Add New user Activity
                    //Intent i = new Intent(getApplicationContext(),
                    //ProfileTabs.class);
                    // Closing all previous activities
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(i);
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
                            ViewHelpDeskQuestions.this, postsList,
                            R.layout.helpdesk_list_question, new String[] {TAG_QID, TAG_UID, TAG_QNAME
                            , TAG_DATE_POSTED, TAG_QUESTION},
                            new int[] { R.id.tvPost_id, R.id.tvUid, R.id.tvPost_name,  R.id.tvPost_date, R.id.tvPost_content });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }//end of get all posts class

}