package tipqc.cite.techproject.magnacarta.iwatch.emergency;





        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.app.Activity;
        import java.util.ArrayList;
        import android.telephony.SmsManager;
        import android.net.Uri;
        import android.content.ContentValues;
        import android.content.Intent;

        import tipqc.cite.techproject.magnacarta.R;


public class EmergencyResponder extends Activity {

    private EditText phoneNumber, messageText;
    private Button  sendAndSaveButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_layout);

        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        messageText = (EditText)findViewById(R.id.messageText);



        Button sendAndSaveButton = (Button) findViewById(R.id.sendAndSaveButton);


        sendAndSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(phoneNumber.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a Phone Number.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(messageText.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter your message.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(messageText.getText().toString().trim().length() > 160) {
                    sendLongSMS();
                    //Save in SENT folder


                }
                else {

                    //Save in SENT folder

                }
            }
        });

    }




    public void sendLongSMS() {
        String phoneNo = "09265038571";
        //String message = "Hello World! Now we are going to demonstrate how to send a message with more than 160 characters from your Android application.";

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(messageText.getText().toString());
        smsManager.sendMultipartTextMessage(phoneNumber.getText().toString(), null, parts, null, null);

        Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
    }


    public void invokeSMSApp() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.putExtra("sms_body", messageText.getText().toString()); //"Hello World!");
        smsIntent.putExtra("address", phoneNumber.getText().toString()); //"0123456789");
        smsIntent.setType("vnd.android-dir/mms-sms");

        startActivity(smsIntent);
    }


}
