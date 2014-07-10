package tipqc.cite.techproject.magnacarta.iwatch.antivaw;



        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.Display;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListView;

        import tipqc.cite.techproject.magnacarta.R;

public class AntiVawActivity extends Activity{

    int width, height;
    EditText search;
    ListView lvItems;
    StableArrayAdapter adapter;
    AlertDialog.Builder alertDialog;

    String[] meaning = new String[] {
            "RA 8535: Anti-Rape Law of 1997",
            "RA 8535: Anti-Rape Law of 1997",
            "RA 8535: Anti-Rape Law of 1997",
            "RA 8535: Anti-Rape Law of 1997",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 8535: Anti-Rape Law of 1997",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 9208: Anti-Trafficking in Persons Act of 2003",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
            "RA 7877: Anti Sexual Harassment Act of 1995",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        setContentView(R.layout.antivaw_law_layout);

        search = (EditText) findViewById(R.id.search);
        lvItems = (ListView) findViewById(R.id.listview);

        search.getLayoutParams().height = (int) (height * 0.10);
        lvItems.getLayoutParams().height = (int) (height * 0.85);

        search.getLayoutParams().width = (int) (width * 0.90);
        lvItems.getLayoutParams().width = (int) (width * 0.90);

        String[] values = new String[] {
                "A man has sexual intercourse with a woman through force, threat or intimidation",
                "A man has sexual intercourse with a woman through fraudulent machination or grave abuse of authority",
                "A man has sexual intercourse with a woman when the victim is deprived of reason or is unconscious",
                "A man has sexual intercourse with a woman when the victim is under 12 years of age or is demented, even if none of the above conditions are present",
                "A neck / shoulder massage",
                "Any person who commits an act of sexual assault through oral or anal sex or by inserting an instrument or object into the anal or genital orifice of another person",
                "Attempted or actual sexual assault",
                "Direct or indirect threats or bribes for unwanted sexual activity ",
                "Frequent jokes about sex or males/females",
                "Intrusive sexually explicit questions",
                "Letters, notes, telephone calls, or material of a sexual nature",
                "Name-calling, such as bitch, whore, or slut",
                "Ogling or leering, staring at a woman's breast or a man's derriere",
                "Pervasive displays of pictures, calendars, cartoons, or other materials with sexually explicit or graphic content",
                "Rating a person's sexuality",
                "Recruitment, transportation, transfer or harbouring, or receipt of persons with or without the victim’s consent or knowledge, within or across national borders by means of threat or use of force, or other forms of coercion, abduction, fraud, deception, abuse of power or of position, taking advantage of the vulnerability of the person, or the giving or receiving of payments or benefits to achieve the consent of a person having control over another person for the purpose of exploitation which includes at a minimum, the exploitation or the prostitution of others or other forms of sexual exploitation, forced labour or services, slavery, servitude or the removal or sale of organs",
                "Repeatedly asking a person out for dates, or to have sex",
                "Sexual innuendos and comments",
                "Sexual Ridicule",
                "Sexually suggestive sounds or gestures such as sucking noises, winks, or pelvic thrusts",
                "Spreading rumors about a person's sexuality",
                "Stalking a person",
                "Touching, patting, punching, stroking, squeezing, tickling, or brushing against a person",
        };



        final ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i<values.length; i++){
            list.add(values[i]);
        }

        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        lvItems.setAdapter(adapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position,
                                    long id) {
                // TODO Auto-generated method stub

                final String item = (String) parent.getItemAtPosition(position);
                //search.setText(item);
                search.setText("");

                alertDialog = new AlertDialog.Builder(AntiVawActivity.this);
                //alertDialog.setIcon(R.drawable.alert_dictionary);
                //alertDialog.setTitle(item);
                //alertDialog.setMessage(meaning[position]);
                alertDialog.setTitle("Covered by:"+meaning[position]);
                alertDialog.setMessage(item);
                alertDialog.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                    }
                });
                alertDialog.show();

            }
        });

        search.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
                AntiVawActivity.this.adapter.getFilter().filter(cs);
            }

        });

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    @Override
    public void onBackPressed(){
        try{
            Class c = Class.forName("capstone.osome3.Home");
            Intent i = new Intent(AntiVawActivity.this, c);
            startActivity(i);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finish();
    }


}
