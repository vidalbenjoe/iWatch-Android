package tipqc.cite.techproject.magnacarta.iwatch.helplines;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tipqc.cite.techproject.magnacarta.R;

public class MenuActivity extends Activity implements AnimationListener {

	
	TextView dswd, cic, pnp, wcpc, vawcd;
	
	// Animation
	Animation animFadein, animFadeOut, animBlink, animRotate;
	Animation left, right, leftout, rightout;
	int packageNum = 1;
	String packageName ="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature (Window.FEATURE_NO_TITLE);
  		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
  		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.helplinelist);

		
		   
		
		dswd = (TextView) findViewById(R.id.dswd);
		cic = (TextView) findViewById(R.id.cic);
		pnp = (TextView) findViewById(R.id.pnp);
		wcpc = (TextView) findViewById(R.id.wcpc);
		vawcd = (TextView) findViewById(R.id.vawcd);
		
		// load the animation
		left = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.left);
		right = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.right);
		leftout = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.leftout);
		rightout = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.rightout);
		
		
		left.setAnimationListener(this);
		right.setAnimationListener(this);
			
						
		in();		
		
		
		dswd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packageNum = 1;
				clicked();
			}
		});

		cic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packageNum = 2;
				clicked();
			}
		});
		
		pnp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packageNum = 3;
				clicked();
			}
		});

		wcpc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packageNum = 4;
				clicked();
			}
		});
		
		vawcd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packageNum = 5;
				clicked();
			}
		});
		
		
	}

	public void clicked(){
		dswd.startAnimation(leftout);
		pnp.startAnimation(rightout);
		vawcd.startAnimation(leftout);
		cic.startAnimation(rightout);
		wcpc.startAnimation(leftout);
		
		switch (packageNum){
		case 1:
			Intent i = new Intent(getApplicationContext(),
					Dswsd.class);
			// starting new activity and expecting some response back
			startActivityForResult(i, 100);
			 break;
		case 2:
			Intent o = new Intent(getApplicationContext(),
					Cic.class);
			// starting new activity and expecting some response back
			startActivityForResult(o, 100);
			 break;
		case 3:
			Intent p = new Intent(getApplicationContext(),
					Pnp.class);
			// starting new activity and expecting some response back
			startActivityForResult(p, 100);
			 break;
			
			
		case 4:
			Intent q = new Intent(getApplicationContext(),
					Wcpc.class);
			// starting new activity and expecting some response back
			startActivityForResult(q, 100);
			 break;
		case 5:

			Intent r = new Intent(getApplicationContext(),
					Vawcd.class);
			// starting new activity and expecting some response back
			startActivityForResult(r, 100);
			 break;
		
		}
		
		dswd.postDelayed(new Runnable() {
            @Override
            public void run() {
            	try{
        		Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
        		startActivity(LaunchIntent);
            	}
            	catch(Exception e){
            		
            		dswd.startAnimation(left);
            		pnp.startAnimation(right);
            		vawcd.startAnimation(left);
            		cic.startAnimation(right);
            		wcpc.startAnimation(left);	
            	}
            		
            }
        }, 900);
		
	}
    
	public void in(){
		dswd.startAnimation(left);
		pnp.startAnimation(right);
		vawcd.startAnimation(left);
		cic.startAnimation(right);
		wcpc.startAnimation(left);
		
	}
	
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
}