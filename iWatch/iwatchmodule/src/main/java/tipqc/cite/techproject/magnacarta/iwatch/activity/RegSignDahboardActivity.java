package tipqc.cite.techproject.magnacarta.iwatch.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.MainActivity;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.LogNoCon;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Login_Activity;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.RegNoCon;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Registration_Activity;


/**
 * Created by Nelvie on 2/22/14.
 */
public class RegSignDahboardActivity extends Activity {

private TextView fbfooterlogintext;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.regsigndashboard_layout);



//make a facebook class activity
      /*  Button btnFbLogin = (Button) findViewById(R.id.fblogin);
        btnFbLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(RegSignDahboardActivity.this, RegSignDahboardActivity.class);
                startActivity(intent);
            }
        });
*/
        fbfooterlogintext = (TextView) findViewById(R.id.fbfooterid);

        fbfooterlogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(view.getContext(), Registration_Activity.class);
                startActivity(intent11);
            }
        });


        Button regSignup = (Button) findViewById(R.id.regbtn);
        regSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), RegNoCon.class);
                startActivityForResult(myIntent, 0);


            }

        });




        Button reglogin = (Button) findViewById(R.id.buttonreglogin);
        reglogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(),LogNoCon.class);
                startActivityForResult(myIntent, 0);


            }

        });


        Button dec = (Button) findViewById(R.id.decline);
        dec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

               Intent myIntent = new Intent(view.getContext(), MainActivity.class);
               startActivityForResult(myIntent, 0);

            }

        });

    }


    public void onBackPressed() {
 finish();
    }


}