package tipqc.cite.techproject.magnacarta.iwatch.notification;


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.AlertDialog;
        import android.app.ListActivity;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemClickListener;



        import tipqc.cite.techproject.magnacarta.R;
        import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;

public class NotifificationActivity extends ListActivity {

    TextView tvNdate, tvNtitle, tvNmessage;
    String ndate, ntitle, nmessage;
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();//for delete post

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();//for get all posts

    ArrayList<HashMap<String, String>> postsList;

    // url to get all posts list
    private static String url_posts = "http://iwatchpcw.hostoi.com/viewnotifs.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_NDATE = "ndate";
    private static final String TAG_NTITLE = "ntitle";
    private static final String TAG_NMESSAGE = "nmessage";

    private static final String TAG_NOTIFICATION = "notification";

    //  JSONArray
    JSONArray notifications = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notif);

        // Hashmap for ListView
        postsList = new ArrayList<HashMap<String, String>>();



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

            }
        });


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
            pDialog = new ProgressDialog(NotifificationActivity.this);
            pDialog.setMessage("Loading Notifications. Please wait...");
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
                    notifications = json.getJSONArray(TAG_NOTIFICATIONS);

                    // looping through All posts
                    for (int i = 0; i < notifications.length(); i++) {
                        JSONObject c = notifications.getJSONObject(i);

                        // Storing each json item in variable

                        String ndate = c.getString(TAG_NDATE);
                        String ntitle = c.getString(TAG_NTITLE);
                        String nmessage = c.getString(TAG_NMESSAGE);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_NDATE, ndate);
                        map.put(TAG_NTITLE, ntitle);
                        map.put(TAG_NMESSAGE, nmessage);
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
                            NotifificationActivity.this, postsList,
                            R.layout.list_notifs, new String[] {TAG_NDATE, TAG_NTITLE, TAG_NMESSAGE},
                            new int[] { R.id.tvNDate, R.id.tvNTitle, R.id.tvNMessage});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }//end of get all posts class

}