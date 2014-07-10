package tipqc.cite.techproject.magnacarta.iwatch.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import javax.inject.Inject;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.ApplicationiWatch;
import tipqc.cite.techproject.magnacarta.libraries.ui.animation.LiveButton;
import tipqc.cite.techproject.magnacarta.libraries.ui.text.TextJustifyUtils;

/**
 * Created by Benjoe on 2/21/14.
 */
public class AboutMagnaCartaFragment extends Fragment {

    @Inject
    LiveButton liveButton ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ApplicationiWatch) getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater
                .inflate(R.layout.aboutmagnacarta_layout, container, false);

        // Button button0 = (Button) mainView.findViewById(R.id.mainmenu_button0);
        // liveButton.setupLiveAnimOnButton(button0, new NavDrawerSelectItemRunnable((AbstractNavDrawerActivity)this.getActivity(), 1));



        return mainView;
    }


}
