package tipqc.cite.techproject.magnacarta.iwatch.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;

public class ReportBugActivity extends Activity {

    Button button;
    EditText destinationAddress;
    EditText sbj;
    EditText messageBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_bug_layout);

        sbj = (EditText) findViewById(R.id.subject);
        messageBody = (EditText) findViewById(R.id.messageBody);
        button = (Button) findViewById(R.id.button);
        destinationAddress = (EditText) findViewById(R.id.destinationAddress);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                String[] to = new String[]{"iwatchmagnacarta@gmail.com", "",};

                String subject = sbj.getText().toString();
                String message = messageBody.getText().toString();
                //String to = destinationAddress.getText().toString();

                Intent emailActivity = new Intent(Intent.ACTION_SEND);

                //set up the recipient address

                emailActivity.putExtra(android.content.Intent.EXTRA_EMAIL, to);

                //set up the email subject
                emailActivity.putExtra(Intent.EXTRA_SUBJECT, subject);

                //you can specify cc addresses as well
                // email.putExtra(Intent.EXTRA_CC, new String[]{ ...});
                // email.putExtra(Intent.EXTRA_BCC, new String[]{ ... });

                //set up the message body
                emailActivity.putExtra(Intent.EXTRA_TEXT, message);

                emailActivity.setType("message/rfc822");


                startActivity(Intent.createChooser(emailActivity, "Select your Email Provider :"));
               // Toast.makeText(ReportBugActivity.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}