package tipqc.cite.techproject.magnacarta.iwatch.notification;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.MainActivity;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Registration_Activity;
import tipqc.cite.techproject.magnacarta.iwatch.activity.RegSignDahboardActivity;




public class NotifNoConnection extends Activity {

    private static final String LOG_TAG = "CheckNetworkStatus";
    private NetworkChangeReceiver receiver;
    private boolean isConnected = false;
    private TextView networkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noconnection_layout);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

        //networkStatus = (TextView) findViewById(R.id.net);
    }



    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "onDestory");
        super.onDestroy();

        unregisterReceiver(receiver);

    }


    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {

            Log.v(LOG_TAG, "Receieved notification about network status");
            isNetworkAvailable(context);

        }


        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            if(!isConnected){
                                Intent view = new Intent(getApplicationContext(), NotifificationActivity.class);

                                startActivity(view);

                                // closing this screen
                                finish();
                                isConnected = true;
                                //do your processing here ---
                                //if you need to post any data to the server or get status
                                //update from the server
                            }
                            return true;
                        }
                    }
                }
            }
            // Log.v(LOG_TAG, "You are not connected to Internet!");
            showAlertDialog(NotifNoConnection.this, "No Internet Connection",
                    "Please enable your wifi or data connection.", false);

            isConnected = false;
            return false;
        }
    }
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent view = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(view);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


}