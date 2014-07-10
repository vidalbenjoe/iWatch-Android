package tipqc.cite.techproject.magnacarta.iwatch.lawsearch;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import tipqc.cite.techproject.magnacarta.R;

public class MagnaCartaSearchActivity extends Activity {

    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnacarta_fragmentsearch);

        // Listview Data
        String magnacartalaw[] = {"CHAPTER 1 GENERAL PROVISIONS", "CHAPTER II DEFENITION OF TERMS", "CHAPTER III DUTIES RELATED TO THE HUMAN RIGHTS OF WOMEN", "CHAPTER IV RIGHTS AND EMPOWERMENT", "CHAPTER V RIGHTS AND EMPOWERMENT OF MARGINALIZED SECTORS",
                "CHAPTER VI INSTITUTIONAL MECHANISMS"};




        lv = (ListView) findViewById(R.id.list_viewmc);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview

        adapter = new ArrayAdapter<String>(this, R.layout.list_magcarlawcode, R.id.magnacartacode, magnacartalaw);
        lv.setAdapter(adapter);






        /**
         * Enabling Search Filter
         * */


         inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MagnaCartaSearchActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
