package tipqc.cite.techproject.magnacarta.iwatch;

/**
 * Created by Benjoe on 2/12/14.
 *
 *
 */
import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.AbstractNavDrawerActivity;
import tipqc.cite.techproject.magnacarta.iwatch.drawer.NavDrawerSelectItemRunnable;
import tipqc.cite.techproject.magnacarta.libraries.ui.animation.LiveButton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

public class MainFragment extends Fragment {

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
                .inflate(R.layout.main_fragment, container, false);

       // Button button0 = (Button) mainView.findViewById(R.id.mainmenu_button0);
       // liveButton.setupLiveAnimOnButton(button0, new NavDrawerSelectItemRunnable((AbstractNavDrawerActivity)this.getActivity(), 1));


        return mainView;
    }
}
