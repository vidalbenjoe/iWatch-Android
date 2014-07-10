package tipqc.cite.techproject.magnacarta.iwatch;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.BuildConfig;


import tipqc.cite.techproject.magnacarta.iwatch.Profile.LogNoCon;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Login_Activity;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Profile_Fragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.DefinitionOfTerms;
import tipqc.cite.techproject.magnacarta.iwatch.activity.HelpLinesFragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.ReportBugActivity;
import tipqc.cite.techproject.magnacarta.iwatch.antivaw.AntiVawActivity;
import tipqc.cite.techproject.magnacarta.iwatch.emergency.EmergencyResponder;
import tipqc.cite.techproject.magnacarta.iwatch.headlines.HeadLineReaderActivity;
import tipqc.cite.techproject.magnacarta.iwatch.helpdesk.HelpDeskNoConnection;

import tipqc.cite.techproject.magnacarta.iwatch.helplines.MenuActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.magnadatabase.ListViewCursorAdaptorActivity;
import tipqc.cite.techproject.magnacarta.iwatch.map.GPSLocationBasedActivity;

import tipqc.cite.techproject.magnacarta.iwatch.notification.NotifNoConnection;
import tipqc.cite.techproject.magnacarta.iwatch.notification.NotifificationActivity;
import tipqc.cite.techproject.magnacarta.iwatch.report.ReportFragment;

import tipqc.cite.techproject.magnacarta.iwatch.statistic.StatActivity;
import tipqc.cite.techproject.magnacarta.iwatch.statistic.StatNoCon;
import tipqc.cite.techproject.magnacarta.libraries.info.AppUsageUtils;


import tipqc.cite.techproject.magnacarta.iwatch.drawer.AbstractNavDrawerActivity;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.NavDrawerActivityConfiguration;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.NavDrawerAdapter;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.NavDrawerItem;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.NavMenuBuilder;
import tipqc.cite.techproject.magnacarta.libraries.security.SecurityUtils;
import tipqc.cite.techproject.magnacarta.libraries.ui.eula.EulaHelper;


import javax.inject.Inject;

public class MainActivity extends AbstractNavDrawerActivity {
    @Inject
    NavigationController navController;
    SharedPreferences sp;
    public static String file = "Files";
    AlertDialog.Builder alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // For debug
        if ( BuildConfig.DEBUG) {
            Log.d(ApplicationiWatch.LOG_TAG, "HashKey: " + SecurityUtils.logHashKey(this));
        }

        AppUsageUtils.updateLastUsedTimestamp(this);

        if ( savedInstanceState == null ) {
         this.navController.goLawFragment(this);
         //this.navController.showWhatsNew(this);
         sp = getSharedPreferences(file,0);
        }
    }



    @Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        NavDrawerItem[] menu = new NavMenuBuilder()
                .addSection(100, R.string.navdrawer_appname)
                .addSectionItem(101, R.string.navdrawer_home, R.drawable.navdrawer_home, true, true)
                .addSectionItem(102, R.string.navdrawer_magnacarta, R.drawable.navdrawer_law, true, true)
                .addSectionItem(103, R.string.navdrawer_antivaw, R.drawable.navdrawer_antivawlaw, true, true)
                .addSectionItem(104, R.string.navdrawer_helpdesk, R.drawable.navdrawer_helpdesk, true, true)
                .addSectionItem(105, R.string.navdrawer_emergency, R.drawable.navdrawer_sms, true, true)
                .addSectionItem(106, R.string.navdrawer_report, R.drawable.navdrawer_report, true, true)
                .addSectionItem(107, R.string.navdrawer_map, R.drawable.navdrawer_map, true, true)
                .addSectionItem(108, R.string.navdrawer_profile, R.drawable.navdrawer_profile, true, true)
                .addSectionItem(109, R.string.navdrawer_alerts, R.drawable.navdrawer_notif, true, true)
                .addSectionItem(110, R.string.navdrawer_feeds, R.drawable.navdrawer_headlines, true, true)
               // .addSectionItem(111, R.string.navdrawer_rewards, R.drawable.navdrawer_rewards, true, true)
                .addSectionItem(112, R.string.navdrawer_helplines, R.drawable.navdrawer_contact, true, true)
                .addSectionItem(113, R.string.navdrawer_stat, R.drawable.navdrawer_stat, true, true)
                .addSection(200, R.string.navdrawer_general)
              //  .addSectionItem(201, R.string.navdrawer_settings, R.drawable.navdrawer_settings, true, true)
                .addSectionItem(202, R.string.navdrawer_rating, R.drawable.navdrawer_rating, false, false)
                .addSectionItem(203, R.string.navdrawer_Definitionofterm, R.drawable.navdrawer_book, false, false)
                .addSectionItem(204, R.string.navdrawer_reportbug, R.drawable.navdrawer_bug, false, false)
                .addSectionItem(205, R.string.navdrawer_about, R.drawable.navdrawer_about, false, false)
                .addSectionItem(206, R.string.navdrawer_eula, R.drawable.navdrawer_eula, false, false)
                .addSectionItem(207, R.string.navdrawer_exit, R.drawable.navdrawer_exit, false, false)
                .build();




        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration.Builder()
                .mainLayout(R.layout.activity_main)
                .drawerLayoutId(R.id.drawer_layout)
                .leftDrawerId(R.id.left_drawer)
                .menu(menu)
                .drawerShadow(R.drawable.drawer_shadow)
                .drawerOpenDesc(R.string.drawer_open)
                .drawerCloseDesc(R.string.drawer_close)
                .adapter(new NavDrawerAdapter(this, R.layout.navdrawer_item, menu))
                .drawerIcon(R.drawable.ic_drawer)

                .build();

        return navDrawerActivityConfiguration;
    }

    @Override
    protected void onNavItemSelected(int id) {
        switch ((int)id) {
            case 101:
                this.navController.goLawFragment(this);
                break;
            case 102:
                Intent intent2 = new Intent(this, ListViewCursorAdaptorActivity.class);
                startActivity(intent2);
                break;

            case 103:
                Intent intent3 = new Intent(this, AntiVawActivity.class);
                startActivity(intent3);


              break;

            case 104:
                if (sp.getString("storeduid", "").contentEquals("")){
                    alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage("You must login first.");
                    alertDialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent i = new Intent(getApplicationContext(), LogNoCon.class);
                            startActivity(i);
                        }
                    });
                    alertDialog.show();
                }else{
                    Intent intent4 = new Intent(this, HelpDeskNoConnection.class);
                    startActivity(intent4);
                }

                break;

            case 105:
                    //SMS
                Intent intent5 = new Intent(this, EmergencyResponder.class);
                startActivity(intent5);



                break;

            case 106:

                if (sp.getString("storeduid", "").contentEquals("")){
                    alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage("You must login first to view your profile");
                    alertDialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent i = new Intent(getApplicationContext(), LogNoCon.class);
                            startActivity(i);
                        }
                    });
                    alertDialog.show();
                }else{
                    Intent intent6 = new Intent(this, ReportFragment.class);
                    startActivity(intent6);
                }


                break;


            case 107:
                this.navController.goMapFragment(this);

                break;

            case 108:
                if (sp.getString("storeduid", "").contentEquals("")){
                    alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage("You must login first to view your profile");
                    alertDialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent i = new Intent(getApplicationContext(), LogNoCon.class);
                            startActivity(i);
                        }
                    });
                    alertDialog.show();
                }else{
                    Intent intent8 = new Intent(this, Profile_Fragment.class);
                    startActivity(intent8);
                }

                break;

            case 109:

                Intent intent9 = new Intent(this, NotifNoConnection.class);
                startActivity(intent9);

                break;

            case 110:
                Intent intent10 = new Intent(this, HeadLineReaderActivity.class);
                startActivity(intent10);
                break;

            case 111:


                break;

            case 112:
              Intent intent12 = new Intent(this, MenuActivity.class);
                startActivity(intent12);

                break;

            case 113:
               Intent intent13 = new Intent(this, StatNoCon.class);
                startActivity(intent13);

                break;


            //   GENERAL

            case 201:
              //settings activity

                break;

            case 202:
                this.navController.startAppRating(this);

                break;
            case 203:
                Intent intent203 = new Intent(this, DefinitionOfTerms.class);
                startActivity(intent203);

                break;

            case 204:
                Intent intent204 = new Intent(this, ReportBugActivity.class);
                startActivity(intent204);
                break;

            case 205:
                this.navController.showAbout(this);
                break;
            case 206:
                showAlertDialog(MainActivity.this, "End User License Agreement",
                                "END USER LICENSE FOR ANDROID iWatch Mobile Application SOFTWARE\n" +
                                "PLEASE READ THIS DOCUMENT CAREFULLY BEFORE USING THIS SOFTWARE. THIS LICENSE PROVIDES IMPORTANT INFORMATION CONCERNING THE SOFTWARE, PROVIDES YOU WITH A LICENSE TO USE THE SOFTWARE AND CONTAINS WARRANTY AND LIABILITY INFORMATION.\n" +
                                "BY USING THE SOFTWARE, YOU ARE ACCEPTING THE SOFTWARE \"AS IS\" AND AGREEING TO BE BOUND BY THE TERMS OF THIS LICENSE AGREEMENT.\n" +
                                "IN NO ENVENT WILL THE AUTHORS BE HELD LIABLE FOR ANY DAMASES ARISING FROM THE USE OF THIS SOFTWARE.\n" +
                                "IF YOU DO NOT WISH TO DO SO, DO NOT USE THE SOFTWARE.\n" +
                                "\n" +
                                "1. Terms of License\n" +
                                "This license allows you to:\n" +
                                "(a) use the Software on a single device; and\n" +
                                "If you wish to use the Software on more than one device, you must license another copy of the\n" +
                                "Software.\n" +
                                "\n" +
                                "2. Restrictions on Use\n" +
                                "Unless Technological institute of the philippines has authorized you to distribute the Software, you shall not make or distribute copies of the Software or transfer the Software from one device to another. You shall not decompile, reverse engineer, disassemble, include in other software, or translate the Software, or use the Software for any commercial purposes. You shall not modify, alter, change or otherwise make any modification to the Software or create derivative works based upon the Software. You shall not rent, lease, resell, sub-license, assign, distribute or otherwise transfer the Software or this license. Any attempt to do so shall be void and of no effect.\n" +
                                "\n" +
                                "3. Ownership\n" +
                                "This license provides you with limited rights to use the Software. The Owner retains all ownership, right, title and interest in, to and of the Software and all copies of it. All rights not specifically granted in this license, including domestic and international copyrights, are reserved by the Owner.\n" +
                                "\n" +
                                "4. Proprietary Markings\n" +
                                "The Owner's logos, product names, manuals, documentation, and other support materials are either patented, copyrighted, trademarked, constitute valuable trade secrets (whether or not any portion of them may be copyrighted or patented) or are otherwise proprietary to the Owner. You shall not remove or obscure the Owner's copyright, trade mark or other proprietary notices from any of the materials contained in this package or downloaded together with the Software.\n" +
                                "\n" +
                                "5. Disclaimer of Warranties and Technical Support\n" +
                                "The Software is provided to you after paying a licence and on an \"AS IS\" basis, without any technical support or warranty of any kind including, without limitation, any warranty or condition of merchantability, fitness for a particular purpose and non-infringement.\n" +
                                "\n" +
                                "SOME JURISDICTIONS DO NOT ALLOW THE EXCLUSION OF IMPLIED WARRANTIES, SO THE ABOVE.", false);
                break;

            case 207:

             //   SharedPreferences.Editor ed = sp.edit();
				/*ed.putString("storedPword", "");
				ed.putString("storedFname", "");
				ed.putString("storedMname", "");
				ed.putString("storedLname", "");
				ed.putString("storedMobile", "");
				ed.putString("storedAddress", "");
				ed.putString("storedBdate", "");
				ed.putString("storedPoints", "");*/
                //ed.clear();
                //ed.commit();
               // this.navController.showExitDialog(this);
                System.exit(0);
                break;

        }
    }




    public void finish() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("do you really want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                quit();
              // System.exit(0);
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
        super.finish();
    }




    @Override
    public void onBackPressed() {

        // See bug: http://stackoverflow.com/questions/13418436/android-4-2-back-stack-behaviour-with-nested-fragments/14030872#14030872
        // If the fragment exists and has some back-stack entry
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_frame);
        if (currentFragment != null && currentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            // Get the fragment fragment manager - and pop the backstack
            currentFragment.getChildFragmentManager().popBackStack();
        }
        // Else, nothing in the direct fragment back stack
        else {
            if ( !NavigationController.HOME_FRAGMENT_TAG.equals(currentFragment.getTag())) {
               // this.navController.goLawFragment(this);
                finish();
            }
            else {
                super.onBackPressed();
            }
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




