package tipqc.cite.techproject.magnacarta.iwatch;

import dagger.Module;

import tipqc.cite.techproject.magnacarta.iwatch.Profile.LogNoCon;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Login_Activity;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.ProfileEdit;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Profile_Fragment;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.RegNoCon;
import tipqc.cite.techproject.magnacarta.iwatch.Profile.Registration_Activity;
import tipqc.cite.techproject.magnacarta.iwatch.activity.AboutMagnaCartaFragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.AdvocacyMainFragment;

import tipqc.cite.techproject.magnacarta.iwatch.activity.DashboardAnimationActivity;
import tipqc.cite.techproject.magnacarta.iwatch.activity.HelpLinesFragment;
import tipqc.cite.techproject.magnacarta.iwatch.activity.RegSignDahboardActivity;
import tipqc.cite.techproject.magnacarta.iwatch.activity.ReportBugActivity;
import tipqc.cite.techproject.magnacarta.iwatch.activity.Subpages;
import tipqc.cite.techproject.magnacarta.iwatch.antivaw.AntiVawActivity;
import tipqc.cite.techproject.magnacarta.iwatch.emergency.EmergencyResponder;
import tipqc.cite.techproject.magnacarta.iwatch.headlines.HeadLineReaderActivity;
import tipqc.cite.techproject.magnacarta.iwatch.helpdesk.AddHelpDeskQuestions;
import tipqc.cite.techproject.magnacarta.iwatch.helpdesk.HelpDeskNoConnection;
import tipqc.cite.techproject.magnacarta.iwatch.helpdesk.ViewHelpDeskQuestions;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.LawTabFragment;

import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterFiveActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterFourActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterOneActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterSixActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterThreeActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterTwoActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaSearchActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MainLawTabFragmentActivity;

import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.magnadatabase.ListViewCursorAdaptorActivity;
import tipqc.cite.techproject.magnacarta.iwatch.map.MobileSDKExampleApplication;
import tipqc.cite.techproject.magnacarta.iwatch.map.PoliceMapFragment;

import tipqc.cite.techproject.magnacarta.iwatch.report.ReportFragment;

import tipqc.cite.techproject.magnacarta.iwatch.statistic.StatActivity;
import tipqc.cite.techproject.magnacarta.libraries.MCXModule;

@Module(
        injects = {
                ApplicationiWatch.class,
                AdvocacyMainFragment.class,
                MainActivity.class,
                DashboardAnimationActivity.class,
                MainFragment.class,

                //helpdesk
                HelpDeskNoConnection.class,
                AddHelpDeskQuestions.class,
                ViewHelpDeskQuestions.class,
                ViewHelpDeskQuestions.class,

                //map
                PoliceMapFragment.class,


                //profile
                RegNoCon.class,
                LogNoCon.class,
                RegSignDahboardActivity.class,
                Registration_Activity.class,
                Login_Activity.class,
                Profile_Fragment.class,
                ProfileEdit.class,

                //Law search Activity
                MagnaCartaSearchActivity.class,
                LawTabFragment.class,
                MainLawTabFragmentActivity.class,
                ListViewCursorAdaptorActivity.class,

                //MAGNA CARTA CONTENT
              //  GeneralProvisionsActivity.class,
                ChapterOneActivity.class,
                ChapterTwoActivity.class,
                ChapterThreeActivity.class,
                ChapterFourActivity.class,
                ChapterFiveActivity.class,
                ChapterSixActivity.class,

                //ANTI VAW
                AntiVawActivity.class,


                //Report
                ReportFragment.class,
                //headlines
                HeadLineReaderActivity.class,

                //abouts and tips
                AboutMagnaCartaFragment.class,
                HelpLinesFragment.class,


                ReportBugActivity.class,


                //SMS

                EmergencyResponder.class,
                Subpages.class,
                StatActivity.class,


                MobileSDKExampleApplication.class,


        },
        includes = {
                MCXModule.class
        },

        overrides=true
)
public class iWatchModule {

}
