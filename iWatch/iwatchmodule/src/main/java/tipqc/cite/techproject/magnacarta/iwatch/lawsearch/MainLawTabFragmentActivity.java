package tipqc.cite.techproject.magnacarta.iwatch.lawsearch;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import tipqc.cite.techproject.magnacarta.R;

/**
 * Created by Benjoe on 2/21/14.
 */
public class MainLawTabFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlawtab_fragment);

        LawTabFragment fragmenttab = new LawTabFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragmenttab).commit();


    }


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

