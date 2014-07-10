package tipqc.cite.techproject.magnacarta.iwatch.activity;


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

public class DefinitionOfTerms extends Activity{

    int width, height;
    EditText search;
    ListView lvItems;
    StableArrayAdapter adapter;
    AlertDialog.Builder alertDialog;

    String[] meaning = new String[] {
            "refers to those who are below eighteen (18) years of age or over but are unable to fully take care of themselves or protect themselves from abuse, neglect, cruelty, exploitation, or discrimination because of a physical or mental disability or condition",
            "refers to any gender-based distinction, exclusion, or restriction which has the effect or purpose of impairing or nullifying the recognition, enjoyment, or exercise by women, irrespective of their marital status, on a basis of equality of men and women, of human rights and fundamental freedoms in the political, economic, social, cultural, civil, or any other field. \n It includes any act or omission, including by law, policy, administrative measure, or practice, that directly or indirectly excludes or restricts women in the recognition and promotion of their rights and their access to and enjoyment of opportunities, benefits, or privileges. \nA measure or practice of general application is discrimination against women if it fails to provide for mechanisms to offset or address sex or gender-based disadvantages or limitations of women, as a result of which women are denied or restricted in the recognition and protection of their rights and in their access to and enjoyment of opportunities, benefits, or privileges; or women, more than men, are shown to have suffered the greater adverse effects of those measures or practices. \nProvided, finally, That discrimination compounded by or intersecting with other grounds, status, or condition, such as ethnicity, age, poverty, or religion shall be considered discrimination against women under this Act.",
            "refers to those directly or indirectly engaged in taking, culturing, or processing fishery or aquatic resources. These include, but are not to be limited to, women engaged in fishing in municipal waters, coastal and marine areas, women workers in commercial fishing and aquaculture, vendors and processors of fish and coastal products, and subsistence producers such as shell-gatherers, managers, and producers of mangrove resources, and other related producers",
            "refers to the development perspective and process that are participatory and empowering, equitable, sustainable, free from violence, respectful of human rights, supportive of self-determination and actualization of human potentials. It seeks to achieve gender equality as a fundamental value that should be reflected in development choices; seeks to transform society’s social, economic, and political structures and questions they validity of the gender roles they ascribed to women and men; contends that women are active agents of development and not just passive recipients of development assistance; and stresses the need of women to organize themselves and participate in political processes to strengthen their legal rights.",
            "refers to the principle asserting the equality of men and women and their right to enjoy equal conditions realizing their full human potentials to contribute to and benefit from the results of development, and with the State recognizing that all human beings are free and equal in dignity and rights.",
            "refers to the policies, instruments, programs, services, and actions that address the disadvantaged position of women in society by providing preferential treatment and affirmative action. Such temporary special measures aimed at accelerating de facto equality between men and women shall not be considered discriminatory but shall in no way entail as a consequence the maintenance of unequal or separate standards. These measures shall be discontinued when the objectives of equality of opportunity and treatment have been achieved.",
            "refers to the strategy for making women’s as well as men’s concerns and experiences an integral dimension of the design, implementation, monitoring, and evaluation of policies and programs in all political, economic, and societal spheres so that women and men benefit equally and inequality is not perpetuated. It is the process of assessing the implications for women and men of any planned action, including legislation, policies, or programs in all areas and at all levels.",
            "refers to a group of people or homogenous societies identified by self-ascription and ascription by other, who have continuously lived as organized community on communally bounded and defined territory, and who have, under claims of ownership since time immemorial, occupied, possessed customs, tradition, and other distinctive cultural traits, or who have, through resistance to political, social, and cultural inroads of colonization, non-indigenous religions and culture, became historically differentiated from the majority of Filipinos. They shall likewise include peoples who are regarded as indigenous on account of their descent from the populations which inhabited the country, at the time of conquest or colonization, or at the time of inroads of non-indigenous religions and cultures, or the establishment of present state boundaries, who retain some or all of their own social, economic, cultural, and political institutions, but who may have been displaced from their traditional domains as defined under Section 3(h), Chapter II of Republic Act No. 8371, otherwise known as “The Indigenous Peoples Rights Act of 1997” (IPRA of 1997)",
            "refers to a condition where a whole category of people is excluded from useful and meaningful participation in political, economic, social, and cultural life.",
            "refers to the basic, disadvantaged, or vulnerable persons or groups who are mostly living in poverty and have little or no access to land and other resources, basic social and economic services such as health care, education, water and sanitation, employment and livelihood opportunities, housing, social security, physical infrastructure, and the justice system.",
            "refers to Filipinos who are to be engaged, are engaged, or have been engaged in a remunerated activity in a State of which they are not legal residents, whether documented or undocumented",
            "refers to native peoples who have historically inhabited Mindanao, Palawan, and Sulu, and who are largely of the Islamic faith",
            "refers to those who are suffering from restriction or different abilities, as a result of a mental, physical, or sensory impairment to perform an activity in the manner or within the range considered normal for a human being",
            "refers to those sixty (60) years of age and above",
            "refers to those who are engaged directly or indirectly in small farms and forests areas, workers in commercial farms and plantations, whether paid or unpaid, regular or season-bound. These shall include, but are not limited to, (a) small farmers who own or are still amortizing for lands that is not more than three (3) hectares, tenants, leaseholders, and stewards; and (b) rural workers who are either wage earners, self-employed, unpaid family workers directly and personally engaged in agriculture, small-scale mining, handicrafts, and other related farm and off-farm activities",
            "refers to policies and programs that seek to reduce poverty and vulnerability to risks and enhance the social status and rights of all women, especially the marginalized by promoting and protecting livelihood and employment, protecting against hazards and sudden loss of income, and improving people’s capacity to manage risk. Its components are labor market programs, social insurance, social welfare, and social safety nets.",
            "refers to those who fall under the category of a solo parent defined under Republic Act No. 8972, otherwise known as the \"Solo Parents Welfare Act of 2000\".",
            "refers to the full and equal enjoyment of rights and freedoms contemplated under this Act. It encompasses de jure and de facto equality and also equality in outcomes.",
            "refers to a variety of legislative, executive, administrative, and regulatory instruments, policies, and practices aimed at accelerating this de facto equality of women in specific areas. These measures shall not be considered discriminatory but shall in no way entail as a consequence the maintenance of unequal or separate standards. They shall be discontinued when their objectives have been achieved.",
            "refers to those residing in urban and urbanizable slum or blighted areas, with or without the benefit of security of abode, where the income of the head of the family cannot afford in a sustained manner to provide for the family’s basic needs of food, health, education, housing, and other essentials in life",
            "refers to any act of gender-based violence that results in, or is likely to result in, physical, sexual, or psychological harm or suffering to women, including threats of such acts, coercion, or arbitrary deprivation of liberty, whether occurring in public or in private life. It shall be understood to encompass, but not limited to, the following: (1) Physical, sexual, psychological, and economic violence occurring in the family, including battering, sexual abuse of female children in the household, dowry-related violence, marital rape, and other traditional practices harmful to women, non-spousal violence, and violence related to exploitation",
            "refers to the provision, availability, and accessibility of opportunities, services, and observance of human rights which enable women to actively participate and contribute to the political, economic, social, and cultural development of the nation as well as those which shall provide them equal access to ownership, management, and control of production, and of material and informational resources and benefits in the family, community, and society.",
            "refers to women employed in the military, both in the major and technical services, who are performing combat and/or noncombat functions, providing security to the State, and protecting the people from various forms of threat. It also includes women trainees in all military training institutions.",
            "refers to those who are employed by any person acting directly or indirectly in the interest of an employer in relation to an employee and shall include the government and all its branches, subdivisions, and instrumentalities, all government-owned and controlled corporations and institutions, as well as nonprofit private institutions or organizations",
            "refers to self-employed, occasionally or personally hired, subcontracted, paid and unpaid family workers in household incorporated and unincorporated enterprises, including home workers, micro-entrepreneurs and producers, and operators of sari-sari stores and all other categories who suffer from violation of workers’ rights",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        setContentView(R.layout.definitionofterms);

        search = (EditText) findViewById(R.id.search);
        lvItems = (ListView) findViewById(R.id.listview);

        search.getLayoutParams().height = (int) (height * 0.10);
        lvItems.getLayoutParams().height = (int) (height * 0.85);

        search.getLayoutParams().width = (int) (width * 0.90);
        lvItems.getLayoutParams().width = (int) (width * 0.90);

        String[] values = new String[] {
                "Children",
                "Discrimination Against Women",
                "Fisherfolk",
                "Gender and Development (GAD)",
                "Gender Equality",
                "Gender Equity",
                "Gender Mainstreaming",
                "Indigenous Peoples",
                "Marginalization",
                "Marginalized",
                "Migrant Workers",
                "Moro",
                "Persons with Disabilities",
                "Senior Citizens",
                "Small Farmers and Rural Workers",
                "Social Protection",
                "Solo Parents",
                "Substantive Equality",
                "Temporary Special Measures",
                "Urban Poor",
                "Violence Against Women",
                "Women Empowerment",
                "Women in the Military",
                "Workers in the Formal Economy",
                "Workers in the Informal Economy",
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
               // search.setText("");

                alertDialog = new AlertDialog.Builder(DefinitionOfTerms.this);
                //alertDialog.setIcon(R.drawable.alert_dictionary);
                alertDialog.setTitle(item);
                alertDialog.setMessage(meaning[position]);
                //alertDialog.setTitle("Covered by: "+meaning[position]);
                //alertDialog.setMessage(item);
                alertDialog.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                    search.setText("");
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
                DefinitionOfTerms.this.adapter.getFilter().filter(cs);
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
            Intent i = new Intent(DefinitionOfTerms.this, c);
            startActivity(i);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finish();
    }


}
