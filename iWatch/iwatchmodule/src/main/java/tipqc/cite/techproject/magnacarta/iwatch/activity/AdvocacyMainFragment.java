package tipqc.cite.techproject.magnacarta.iwatch.activity;

import tipqc.cite.techproject.magnacarta.iwatch.ApplicationiWatch;
import tipqc.cite.techproject.magnacarta.libraries.ui.animation.LiveButton;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import tipqc.cite.techproject.magnacarta.R;

import javax.inject.Inject;


public class AdvocacyMainFragment extends Fragment {



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

        View mainView = inflater.inflate(R.layout.advocacy_main, container, false);

        return mainView;
    }
}