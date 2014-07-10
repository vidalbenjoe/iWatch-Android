package tipqc.cite.techproject.magnacarta.iwatch;

/**
 * Created by Benjoe on 1/1/14.
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.activity.RegSignDahboardActivity;

public class SplashScreen extends Activity {

    AnimationDrawable frameAnimation;
    private static String TAG = SplashScreen.class.getName();
    private static long SLEEP_TIME = 5;    // Sleep for some time

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        setContentView(R.layout.activity_splash_screen);

        //THIS LINE WILL GENERATE A LOADING ANIMATION!GALING NOH!?
        ProgressBar loadingimg = (ProgressBar)findViewById(R.id.progressBar1);

        StartAnimations();

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }


    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        Animation animg = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        TextView txtmag = (TextView) findViewById(R.id.developby);
        txtmag.clearAnimation();
        txtmag.startAnimation(animg);




        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);



        iv.clearAnimation();
        iv.startAnimation(anim);

        ProgressBar loadingimg = (ProgressBar)findViewById(R.id.progressBar1);
        loadingimg.clearAnimation();
        loadingimg.startAnimation(anim);


    }


    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping

                Thread.sleep(SLEEP_TIME*1000);





            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(SplashScreen.this, RegSignDahboardActivity.class);
            SplashScreen.this.startActivity(intent);
            SplashScreen.this.finish();
        }



    }
}

