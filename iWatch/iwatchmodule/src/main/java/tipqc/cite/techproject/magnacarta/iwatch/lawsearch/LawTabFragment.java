package tipqc.cite.techproject.magnacarta.iwatch.lawsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.NavigationController;
import tipqc.cite.techproject.magnacarta.iwatch.activity.AboutMagnaCartaFragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.AdvocacyMainFragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.DashboardAnimationActivity;
import java.io.File;

public class LawTabFragment extends Fragment {
    private FragmentTabHost mTabHost;
   private static long back_pressed;
    //Mandatory Constructor
    public LawTabFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lawtab_layout, container, false);



        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentGeneral").setIndicator("General"),
                AboutMagnaCartaFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentMagnaCarta").setIndicator("Magna Carta"),
                DashboardAnimationActivity.class, null);
      //  mTabHost.addTab(mTabHost.newTabSpec("fragmentAdvanceSearch").setIndicator("Advanced Search"),
             //   AdvocacyMainFragment.class, null);

        mTabHost.setCurrentTab(1);
        mTabHost.getTabWidget().setBackgroundColor(Color.parseColor("#250424"));

        //mTabHost.getTabWidget().getResources().getColor(R.color.divider_color);
      //  mTabHost.getTabWidget().setBackgroundColor(Color.ParseColor("#250424"));


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                // TODO Auto-generated method stub
                for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
                {
                   // mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parsecolor("#7392B5")); //unselected
                      mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#3f0a3e")); // selected
                }
                mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#250424")); // selected
            }
        });


        return rootView;


    }

    public void onBackPressed() {

       /* if(back_pressed + 2000 > System.currentTimeMillis()) super.getActivity().onBackPressed();
        else
            Toast.makeText(getActivity(),"Press once again to exit!",Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();*/


    }


    public void finish() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage("do you really want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                quit();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void quit() {
        super.getActivity().finish();
    };




    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}

