package tipqc.cite.techproject.magnacarta.iwatch;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;


import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Profile_Fragment;
import tipqc.cite.techproject.magnacarta.iwatch.Settings.SettingsFragment;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.AbstractNavDrawerActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.LawTabFragment;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaSearchActivity;
import tipqc.cite.techproject.magnacarta.iwatch.map.PoliceMapFragment;
import tipqc.cite.techproject.magnacarta.libraries.ui.changelog.ChangeLogHelper;
import tipqc.cite.techproject.magnacarta.libraries.ui.changelog.EulaChangeLogChainHelper;
import tipqc.cite.techproject.magnacarta.libraries.ui.eula.EulaHelper;
import tipqc.cite.techproject.magnacarta.libraries.ui.fragment.dialog.ConfirmDialog;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class NavigationController {

    public static final String HOME_FRAGMENT_TAG = "home";
    public static final String LAWTAB_FRAGMENT_TAG = "law";
    public static final String MAP_FRAGMENT_TAG = "map";
    @Inject public NavigationController() {

    }

    public void startAppRating(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                .parse("market://details?id=" + context.getPackageName())));

    }




    public void goHomeFragment( AbstractNavDrawerActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new MainFragment(), HOME_FRAGMENT_TAG).commit();

        activity.setTitleWithDrawerTitle();
    }

    public void goLawFragment( AbstractNavDrawerActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new LawTabFragment(), LAWTAB_FRAGMENT_TAG).commit();

        activity.setTitleWithDrawerTitle();
    }



    public void goMapFragment( AbstractNavDrawerActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new PoliceMapFragment(), MAP_FRAGMENT_TAG).commit();

        activity.setTitleWithDrawerTitle();
    }







    public void confirmEulaAndShowChangeLog(FragmentActivity activity) {
        EulaChangeLogChainHelper chain = new EulaChangeLogChainHelper(R.string.eula_title, R.string.eula_accept,
                R.string.eula_refuse, R.string.changelog_whatsnew_title, R.string.changelog_close, R.xml.aboutiwatch, activity);
        chain.show();
    }

    public void confirmEula(FragmentActivity activity) {
        EulaHelper eulaHelper = new EulaHelper(activity);
        eulaHelper.showAcceptRefuse(R.string.eula_title, R.string.eula_accept,
                R.string.eula_refuse);

    }

    public void showEula(FragmentActivity activity) {
        EulaHelper eulaHelper = new EulaHelper(activity);
        eulaHelper.show(R.string.eula_title, R.string.eula_close);
    }

    public void showWhatsNew( FragmentActivity activity ) {
        ChangeLogHelper changeLogHelper = new ChangeLogHelper();
        changeLogHelper.showWhatsNew(R.string.changelog_title, R.string.changelog_close, R.xml.aboutiwatch, activity);
    }

    public void showChangeLog( FragmentActivity activity ) {
        ChangeLogHelper changeLogHelper = new ChangeLogHelper();
        changeLogHelper.showFullChangeLog(R.string.changelog_title, R.string.changelog_close, R.xml.aboutiwatch, activity);
    }

    public void showAbout( FragmentActivity activity ) {
        ChangeLogHelper changeLogHelper = new ChangeLogHelper();
        changeLogHelper.showFullChangeLog(R.string.iwatch_about, R.string.changelog_close, R.xml.aboutiwatch, activity);
    }

    public void showExitDialog(final FragmentActivity activity) {
        ConfirmDialog newFragment = ConfirmDialog.newInstance(
                R.string.confirm_quit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        activity.finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }
        );
        newFragment.show(activity.getSupportFragmentManager(), "dialog");
    }

    public void showSettings(FragmentActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment(), null).commit();
    }
}