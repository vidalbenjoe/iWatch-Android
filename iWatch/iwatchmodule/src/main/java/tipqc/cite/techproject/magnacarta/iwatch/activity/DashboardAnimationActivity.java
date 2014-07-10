package tipqc.cite.techproject.magnacarta.iwatch.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.ApplicationiWatch;

public class DashboardAnimationActivity extends Fragment implements AnimationListener {


    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    int btnClicked = 0;
    SharedPreferences sp;
    public static String file = "Files";
    Animation left, right, leftout, rightout;
    Button btnImg;
    int count=0;
    Animation  animFadein, animFadeout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ApplicationiWatch) getActivity().getApplication()).inject(this);





    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View mainView = inflater
                .inflate(R.layout.dashboard_animation_layout, container, false);

        btnImg = (Button) mainView.findViewById(R.id.btnImg);
        sp = getActivity().getSharedPreferences(file,0);

        b1 = (Button) mainView.findViewById(R.id.b1);
        b2 = (Button) mainView.findViewById(R.id.b2);
        b3 = (Button) mainView.findViewById(R.id.b3);
        b4 = (Button) mainView.findViewById(R.id.b4);
        b5 = (Button) mainView.findViewById(R.id.b5);
        b6 = (Button) mainView.findViewById(R.id.b6);
        b7 = (Button) mainView.findViewById(R.id.b7);
        b8 = (Button) mainView.findViewById(R.id.b8);
        b9 = (Button) mainView.findViewById(R.id.b9);
        b10 = (Button)mainView.findViewById(R.id.b10);

        left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.left);
        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.right);
        leftout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.leftout);
        rightout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.rightout);


        animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.fade_out);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 1;
                jumpIntent();
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 2;
                jumpIntent();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 3;
                jumpIntent();
            }
        });

        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 4;
                jumpIntent();
            }
        });


        b5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 5;
                jumpIntent();
            }
        });


        b6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 6;
                jumpIntent();
            }
        });


        b7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 7;
                jumpIntent();
            }
        });


        b8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 8;
                jumpIntent();
            }
        });

        b9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 9;
                jumpIntent();
            }
        });


        b10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                btnClicked = 10;
                jumpIntent();

            }
        });









        animFadein.setAnimationListener(this);
        animFadeout.setAnimationListener(this);
        btnImg.startAnimation(animFadeout);


        left.setAnimationListener(this);
        right.setAnimationListener(this);

        b1.setText("What is the Magna Carta of Women?");
        b2.setText("What are the penalties of violators?");
        b3.setText("What you should know about violence against women (VAW)");
        b4.setText("18 Things you can do to help end violence against women now");
        b5.setText("Anti-VAW laws");
        b6.setText("Statistics on VAW");
        b7.setText("When you are in abusive relationship");
        b8.setText("If you’re planning to leave an abusive relationship");
        b9.setText("Safety tips for young women");
        b10.setText("What is the discrimination against women?");
        in();
        return mainView;

    }
    public void in(){
        b1.startAnimation(left);
        b3.startAnimation(left);
        b5.startAnimation(left);
        b7.startAnimation(left);
        b9.startAnimation(left);

        b2.startAnimation(right);
        b4.startAnimation(right);
        b6.startAnimation(right);
        b8.startAnimation(right);
        b10.startAnimation(right);
    }
    public void out(){
        b2.startAnimation(leftout);
        b4.startAnimation(leftout);
        b6.startAnimation(leftout);
        b8.startAnimation(leftout);
        b10.startAnimation(leftout);

        b1.startAnimation(rightout);
        b3.startAnimation(rightout);
        b5.startAnimation(rightout);
        b7.startAnimation(rightout);
        b9.startAnimation(rightout);
        b1.postDelayed(new Runnable() {
            @Override
            public void run() {
                in();
            }
        }, 1000);

    }


    public void jumpIntent(){

        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("storedbtnclicked", btnClicked);
        ed.commit();

        b1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getActivity().getApplicationContext(), Subpages.class);
                startActivity(i);
            }
        }, 800);
    }






    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub
        if (animation == animFadeout) {
            count++;

            btnImg.startAnimation(animFadein);
        }
    }
    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
        if (animation == animFadein) {
            switch (count){
                case 1:

                    btnImg.setBackgroundResource(R.drawable.banner1);
                    break;
                case 2:

                    btnImg.setBackgroundResource(R.drawable.banner2);
                    break;
                case 3:

                    btnImg.setBackgroundResource(R.drawable.banner3);
                    break;

                case 4:

                    btnImg.setBackgroundResource(R.drawable.banner5);
                    break;
                case 5:

                    btnImg.setBackgroundResource(R.drawable.banner7);

                    break;


                case 6:

                    btnImg.setBackgroundResource(R.drawable.banner6);
                    count = 0;
                    break;

                default:

            }
            btnImg.postDelayed(new Runnable() {
                @Override
                public void run() {

                    btnImg.startAnimation(animFadeout);
                }
            }, 8000);

        }
    }
}