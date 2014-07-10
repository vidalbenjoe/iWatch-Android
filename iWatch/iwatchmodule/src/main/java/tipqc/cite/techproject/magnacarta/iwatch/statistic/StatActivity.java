package tipqc.cite.techproject.magnacarta.iwatch.statistic;


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
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemClickListener;

        import tipqc.cite.techproject.magnacarta.R;
        import tipqc.cite.techproject.magnacarta.iwatch.report.JSONParser;


public class StatActivity extends ListActivity {

    TextView tvPost_date, tvRcategory, tvCount;
    String post_date, category, count;

    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();//for get all posts

    ArrayList<HashMap<String, String>> postsList;

    // url to get all posts list
    private static String url_posts = "http://iwatchpcw.hostoi.com/viewstats.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_YEARMONTH = "yearmonth";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_COUNT = "count";
    private static final String TAG_REPORTS = "reports";

    //  JSONArray
    JSONArray reports = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stat);
        // Hashmap for ListView
        postsList = new ArrayList<HashMap<String, String>>();

        new LoadAllstat().execute();

        ListView lv = getListView();

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
    class LoadAllstat extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StatActivity.this);
            pDialog.setMessage("Loading Statistical Reports..");
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
                    reports = json.getJSONArray(TAG_REPORTS);

                    // looping through All posts
                    for (int i = 0; i < reports.length(); i++) {
                        JSONObject c = reports.getJSONObject(i);

                        // Storing each json item in variable
                        String yearmonth = c.getString(TAG_YEARMONTH);
                        String category = c.getString(TAG_CATEGORY);
                        String count = c.getString(TAG_COUNT);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_YEARMONTH, yearmonth);
                        map.put(TAG_CATEGORY, category);
                        map.put(TAG_COUNT, count);
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
                            StatActivity.this, postsList,
                            R.layout.list_stat, new String[] {TAG_YEARMONTH, TAG_CATEGORY, TAG_COUNT},
                            new int[] { R.id.tvPost_date, R.id.tvRcategory, R.id.tvCount });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }//end of get all posts class
}
