package tipqc.cite.techproject.magnacarta.iwatch.lawsearch.magnadatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MagnaCartaDbAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CHAPTER = "chapter";
    public static final String KEY_LAWDESC = "lawdescription";
    public static final String KEY_SECTION = "section";
    public static final String KEY_SUBSECTION = "subsection";
    public static final String KEY_WORDTAG = "keywordtag";
    public static final String KEY_WORDENG = "keywordeng";

    private static final String TAG = "MagnaCartaDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "MagnacartaDB";
    private static final String SQLITE_TABLE = "MagnaCartaTableDB";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_CHAPTER + "," +
                    KEY_LAWDESC + "," +
                    KEY_SECTION + "," +
                    KEY_SUBSECTION + "," +
                    KEY_WORDTAG + "," +
                    KEY_WORDENG + "," +
                    " UNIQUE (" + KEY_CHAPTER +"));";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public MagnaCartaDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public MagnaCartaDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createLaw(String chapter, String lawdescription,String section,
                              String subsection, String keywordtag, String keywordeng) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CHAPTER, chapter);
        initialValues.put(KEY_LAWDESC, lawdescription);
        initialValues.put(KEY_SECTION, section);
        initialValues.put(KEY_SUBSECTION, subsection);
        initialValues.put(KEY_WORDTAG, keywordtag);
        initialValues.put(KEY_WORDENG, keywordeng);

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllCountries() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchCountriesByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_CHAPTER, KEY_LAWDESC, KEY_SECTION, KEY_SUBSECTION, KEY_WORDTAG, KEY_WORDENG},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                    KEY_CHAPTER, KEY_LAWDESC, KEY_SECTION, KEY_SUBSECTION, KEY_WORDTAG, KEY_WORDENG},
                    KEY_WORDENG+ " like '%" + inputText + "%'", null,
                    //KEY_CHAPTER+ " like '%" + inputText+ "%' OR" + KEY_WORDENG + "like '%" + inputText + "%", null,

                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllCountries() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                KEY_CHAPTER, KEY_LAWDESC, KEY_SECTION, KEY_SUBSECTION, KEY_WORDTAG, KEY_WORDENG},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeCountries() {

        createLaw("CHAPTER I: General Provisions","The Magna Carta of Women","political","cultural condition Convention on Elimination of All Forms of Discrimination Against Women (CEDAW)"," marginalized women sociocultural","equality of women and men freedom of women discrimination on account of class discrimination on account of gender participate in the development of political, economic, social, cultural realms. opportunities to enhance and develop skills production dignity of every human being sexual orientation human rights standards participate in and access to information affect lives and well being opportunities to enhance and develop skills");
        createLaw("CHAPTER II: Definition of Terms","Women Empowerment","provision","Southern Europe","Southern and Central Asia","accessibility of opportunities contribute to political, economic, social, and cultural development of nation impairing or nullifying the recognition, enjoyment, or exercise by women, irrespective of theis marital status  directly or indirectly excludes or restricts women in the recognition and promotion of their rights discrimination compounded by or intersecting with other grounds, status, or condtion women engaged in fishing in municipal waters, coastal and marine areas, women workers in commercial fishing and aquaculture, vendors and processors of fish and coastal products, and subsistence producers income of the head of the family cannot afford in a sustained manner to provide for the family’s basic needs of food, health, education, housing, and other essentials in life under claims of ownership since time immemorial, occupied, possessed customs, tradition resistance to political, social, and cultural inroads of colonization, non-indigenous religions and culture mental, physical, or sensory impairment to perform an activity in the manner respectful of human rights strategy for making women’s as well as men’s concerns and experiences an integral dimension of the design, implementation, monitoring, and evaluation of policies and programs in all political, economic, and societal spheres sexual abuse of female children Women in the Military  labor market programs equality of women and men");
        createLaw("CHAPTER III: Duties Related to the Human Rights of Women","Algeria","Africa","Northern Africa","Southern and Central Asia", "discriminating against women private corporation discrimination Duties Related to the Human Rights of Women");
        createLaw("CHAPTER IV: Rights and Empowerment","American Samoa","Oceania","Polynesia", "Southern and Central Asia", "all forms of violence as provided for in existing laws Women have the right to protection other sexual and gender-based violence committed against them repeal existing laws that are discriminatory to women within three (3) years from the effectivity of this Act women in the military, police, and other similar services shall be entitled to leave benefits such as maternity leave, as provided for by existing laws the right of women to protection from hazardous drugs, devices, interventions, and substances rights to enter into and leave marriages or common law relationships referred to under the Family Code without prejudice to personal or religious belief special leave benefit of two (2) months with full pay based on her gross monthly compensation following surgery caused by gynecological disorders");
        createLaw("CHAPTER V: Rights and Empowerment of Marginalized Sectors","Andorra","Europe","Southern Europe", "Southern and Central Asia", "Rights and Empowerment of Marginalized Sectors health-giving food to satisfy the dietary needs of the population consult women and involve them in community planning and development decision-making bodies in the regional, national, and international levels indigenous women to practice, promote, protect, and preserve their own culture, traditions health and nutrition Transportation assistance");
        createLaw("CHAPTER VI: Institutional Mechanisms","Angola","Africa","Central Africa","Southern and Central Asia" ,"funding requirements and local government units for their outstanding performance in upholding the rights of women and effective implementation of gender-responsive programs gender mainstreaming program executive order take effect fifteen (15) days after its publication in at least two (2) newspapers of general circulation");
        createLaw("RULE I: General Provisions","Afghanistan","Asia","Southern and Central Asia","Southern and Central Asia","benjoevidal rivera mark allennopre josan");
        createLaw("RULE II: Definition of Terms","Albania","Europe","Southern Europe","Southern and Central Asia","Southern and Central Asia");
        createLaw("RULE III: Duties Related to the Human Rights of Women","Algeria","Africa","Northern Africa","Southern and Central Asia", "Southern and Central Asia");
        createLaw("RULE IV: Rights and Empowerment","American Samoa","Oceania","Polynesia", "Southern and Central Asia", "Southern and Central Asia");
        createLaw("RULE V: Rights and Empowerment of Marginalized Sectors","Andorra","Europe","Southern Europe", "Southern and Central Asia", "Southern and Central Asia");
        createLaw("RULE VI: Institutional Mechanisms","Angola","Africa","Central Africa","Southern and Central Asia" ,"Southern and Central Asia");
    }

}