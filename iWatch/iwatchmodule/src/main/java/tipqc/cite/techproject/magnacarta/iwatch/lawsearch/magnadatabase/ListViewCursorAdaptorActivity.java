package tipqc.cite.techproject.magnacarta.iwatch.lawsearch.magnadatabase;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.downloadcontent.DownloadPDFActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterFiveActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterFourActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterOneActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterSixActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterThreeActivity;
import tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent.ChapterTwoActivity;


public class ListViewCursorAdaptorActivity extends Activity {

    private MagnaCartaDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

   TextView textview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_cursor_adapter);
        textview = (TextView) findViewById(R.id.name);

        dbHelper = new MagnaCartaDbAdapter(this);
        dbHelper.open();

        //Clean all data
        dbHelper.deleteAllCountries();
        //Add some data
        dbHelper.insertSomeCountries();

        //Generate ListView from SQLite Database
        displayListView();



    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[] {
                MagnaCartaDbAdapter.KEY_CHAPTER,
                MagnaCartaDbAdapter.KEY_LAWDESC,
                MagnaCartaDbAdapter.KEY_SECTION,
                MagnaCartaDbAdapter.KEY_SUBSECTION,
                MagnaCartaDbAdapter.KEY_WORDTAG,
                MagnaCartaDbAdapter.KEY_WORDENG
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.code,
                R.id.name,
                R.id.continent,
                R.id.region,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.magnacarta_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                switch(position){
                    case 0:
                        Intent caseZero = new Intent(view.getContext(), ChapterOneActivity.class);
                        startActivityForResult(caseZero, 0);

                        break;

                    case 1:
                        Intent caseOne = new Intent(view.getContext(), ChapterTwoActivity.class);
                        startActivityForResult(caseOne, 0);
                        break;

                    case 2:
                        Intent caseTwo = new Intent(view.getContext(), ChapterThreeActivity.class);
                        startActivityForResult(caseTwo, 0);
                        break;


                    case 3:
                        Intent caseThree = new Intent(view.getContext(), ChapterFourActivity.class);
                        startActivityForResult(caseThree, 0);
                        break;

                    case 4:
                        Intent caseFour = new Intent(view.getContext(), ChapterFiveActivity.class);
                        startActivityForResult(caseFour, 0);
                        break;

                    case 5:
                        Intent caseFive = new Intent(view.getContext(), ChapterSixActivity.class);
                        startActivityForResult(caseFive, 0);
                        break;


                    case 6:
                        Intent caseSix = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseSix, 0);
                        break;


                    case 7:
                        Intent caseSeven = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseSeven, 0);
                        break;


                    case 8:
                        Intent caseEight = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseEight, 0);
                        break;

                    case 9:
                        Intent caseNine = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseNine, 0);
                        break;


                    case 10:
                        Intent caseTen = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseTen, 0);
                        break;


                    case 11:
                        Intent caseElev = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseElev, 0);
                        break;


                    case 12:
                        Intent caseTwel = new Intent(view.getContext(), DownloadPDFActivity.class);
                        startActivityForResult(caseTwel, 0);
                        break;





                    }











                // Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow("chapter"));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

            }
        });

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchCountriesByName(constraint.toString());
            }
        });

    }
}