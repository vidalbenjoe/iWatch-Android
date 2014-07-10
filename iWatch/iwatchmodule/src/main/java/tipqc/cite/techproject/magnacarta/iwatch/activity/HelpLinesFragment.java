package tipqc.cite.techproject.magnacarta.iwatch.activity;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;



import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.libraries.ui.accordion.utils.FontUtils;
import tipqc.cite.techproject.magnacarta.libraries.ui.accordion.widget.AccordionView;

public class HelpLinesFragment extends Activity {
    private static final String TAG = "HelpLinesFragment";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accordion_main);

        final AccordionView v = (AccordionView) findViewById(R.id.accordion_view);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.example_get_by_id);
        TextView tv = new TextView(this);
        tv.setText("Added in runtime...");
        FontUtils.setCustomFont(tv, getAssets());
        ll.addView(tv);
    }
}