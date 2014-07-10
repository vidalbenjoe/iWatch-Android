package tipqc.cite.techproject.magnacarta.iwatch.report;




import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;


import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
//import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.iwatch.MainActivity;
import tipqc.cite.techproject.magnacarta.iwatch.headlines.HeadLineReaderActivity;


public class ReportFragment extends Activity {
    ReportNoConnection cd;
    Boolean isInternetPresent = false;
    InputStream is;
    BitmapFactory.Options bfo;
    Bitmap bitmapOrg;
    ByteArrayOutputStream bao ;
    static final int TIME_DIALOG_ID = 1111;
    static final int DATE_DIALOG_ID = 0;
    private EditText  output;
    public Button btnClick;
    private Button pickDate;
    private int hour;
    private int minute;
    private Calendar cal;
    private int start_day;
    private int start_month;
    private int start_year;
    RadioButton rb1, rb2;
    String alevel = "0";
    SharedPreferences sp;
    public static String file = "Files";

    private ProgressDialog pDialog;
    String lname, mname, fname, name;

    JSONParser jsonParser = new JSONParser();
    TextView textView5;
    EditText ReportDesc;
    EditText editText1;
    EditText editText2;
    Spinner category;
    Spinner relation;
    EditText offendername;
    ImageView viewImage;
    Button b;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;

    private String call;
    private Bitmap bitmap;
    private ProgressDialog dialog;
    Uri imageUri;
    // url to create new product
    private static String url_add_database = "http://iwatchpcw.hostoi.com/upload_image/create_report.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    start_year = year;
                    start_month = monthOfYear;
                    start_day = dayOfMonth;
                    updateDisplay();

                }
            };



    /** Updates the date in the TextView */
    private void updateDisplay() {
        editText1.setText(

                // Month is 0 based so add 1
                start_day + "-" + (start_month + 1) + "-"
                        + start_year);




        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        addButtonClickListener();

    }

    public void addButtonClickListener() {

        btnClick = (Button) findViewById(R.id.pickTime);

        btnClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_fragment_layout);

        sp = getSharedPreferences(file,0);
        SharedPreferences.Editor ed = sp.edit();
        fname = sp.getString("storedfname", "");
        mname = sp.getString("storedmname", "");
        lname = sp.getString("storedlname", "");

        name = lname+", "+fname+" "+mname;
        ed.putString("storedname", name);


        //showAlertDialog(ReportFragment.this, "",
        //        ""+name, false);



        viewImage = (ImageView) findViewById(R.id.viewImage);
        textView5 = (TextView) findViewById(R.id.textView5);
        cd = new ReportNoConnection(getApplicationContext());
        ReportDesc = (EditText) findViewById(R.id.ReportDesc);
        offendername = (EditText) findViewById(R.id.offendername);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText1 = (EditText) findViewById(R.id.editText1);
        pickDate = (Button) findViewById(R.id.pickDate);
        category = (Spinner) findViewById(R.id.choose_category);
        relation = (Spinner) findViewById(R.id.relation_to);
        editText1 = (EditText) findViewById(R.id.editText1);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        final Calendar cal = Calendar.getInstance();

        start_day = cal.get(Calendar.DAY_OF_MONTH);
        start_month = cal.get(Calendar.MONTH);
        start_year = cal.get(Calendar.YEAR);
        // Current Hour
        hour = cal.get(Calendar.HOUR_OF_DAY);
        // Current Minute
        minute = cal.get(Calendar.MINUTE);

        // set current time into output textview
        updateTime(hour, minute);
        updateDisplay();
        // Create button

        // button click event
        rb1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                textView5.setVisibility(View.GONE);
                b.setVisibility(View.GONE);
                viewImage.setVisibility(View.GONE);
            }
        });
        // button click event
        rb2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                textView5.setVisibility(View.VISIBLE);
                b.setVisibility(View.VISIBLE);
                viewImage.setVisibility(View.VISIBLE);
            }
        });
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        // button click event
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // adding data in background thread
                isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {
                    if (rb1.isChecked()){
                        new AddingData().execute();
                    }
                    else if (rb2.isChecked()){
                        if (bitmap == null){
                            showAlertDialog(ReportFragment.this, "No Image",
                                    "Please Upload Image", false);
                        }

                    else{
                            new AddingDataImage().execute();
                        }
                    }



                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(ReportFragment.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                }
            }
        });

        b=(Button)findViewById(R.id.btnSelectPhoto);
        b.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);
        viewImage.setVisibility(View.GONE);
        viewImage=(ImageView)findViewById(R.id.viewImage);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ReportFragment.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    String fileName = "new-photo-name.jpg";
                    //create parameters for Intent with filename
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION,"Image captured by camera");
                    //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                    imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    //create new Intent
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, PICK_Camera_IMAGE);

                }
                else if (options[item].equals("Choose from Gallery"))
                {
//
                    try {
                        Intent gintent = new Intent();
                        gintent.setType("image/*");
                        gintent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(
                                Intent.createChooser(gintent, "Select Picture"),
                                PICK_IMAGE);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e(e.getClass().getName(), e.getMessage(), e);
                    }





                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        String filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    //use imageUri here to access the image
                    selectedImageUri = imageUri;
  		 		    	/*Bitmap mPic = (Bitmap) data.getExtras().get("data");
  						selectedImageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mPic, getResources().getString(R.string.app_name), Long.toString(System.currentTimeMillis())));*/
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if(selectedImageUri != null){
            try {
                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(getApplicationContext(), "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {
                    decodeFile(filePath);
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }





    }


//	}
    /**
     * Background Async Task to add text
     * */
    class AddingData extends AsyncTask<Void, Void, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReportFragment.this);
            pDialog.setMessage("Sending Report..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Adding do database
         * */
        protected String doInBackground(Void... args) {

            String rname = name;
            String rcontent = ReportDesc.getText().toString();
            String offender = offendername.getText().toString();
            String rcategory = category.getSelectedItem().toString();
            String relation_to_offender = relation.getSelectedItem().toString();
            String date_incident = editText1.getText().toString();
            alevel = "1";
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("rname", rname));
            params.add(new BasicNameValuePair("rcontent", rcontent));
            params.add(new BasicNameValuePair("offender", offender));
            params.add(new BasicNameValuePair("rcategory", rcategory));
            params.add(new BasicNameValuePair("relation_to_offender", relation_to_offender));
            params.add(new BasicNameValuePair("date_incident", date_incident));
            params.add(new BasicNameValuePair("alert_level", alevel));
            JSONObject json = jsonParser.makeHttpRequest(url_add_database,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(ReportFragment.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent09 = new Intent(ReportFragment.this, MainActivity.class);
                            startActivity(intent09);
                            finish();
                        }
                    });
                    //Intent i = new Intent(getApplicationContext(), Success.class);
                    // Toast.makeText(getBaseContext(),"Sent",Toast.LENGTH_SHORT).show();
                    //startActivity(i);

                    // closing this screen
                    //	finish();
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(ReportFragment.this, "Message Not Sent, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

    class AddingDataImage extends AsyncTask<Void, Void, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReportFragment.this);
            pDialog.setMessage("Sending Report..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Adding do database
         * */
        protected String doInBackground(Void... args) {
            bfo = new BitmapFactory.Options();
            bfo.inSampleSize = 1;
            //bitmapOrg = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + customImage, bfo);

            bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
            byte [] ba = bao.toByteArray();
            String ba1 = Base64.encodeBytes(ba);

            alevel = "2";
            String rname = name;
            String rcontent = ReportDesc.getText().toString();
            String offender = offendername.getText().toString();
            String rcategory = category.getSelectedItem().toString();
            String relation_to_offender = relation.getSelectedItem().toString();
            String date_incident = editText1.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("rname", rname));
            params.add(new BasicNameValuePair("rcontent", rcontent));
            params.add(new BasicNameValuePair("offender", offender));
            params.add(new BasicNameValuePair("rcategory", rcategory));
            params.add(new BasicNameValuePair("relation_to_offender", relation_to_offender));
            params.add(new BasicNameValuePair("date_incident", date_incident));
            params.add(new BasicNameValuePair("image", ba1));
            params.add(new BasicNameValuePair("alert_level", alevel));
            Log.v("log_tag", System.currentTimeMillis()+".jpg");
            JSONObject json = jsonParser.makeHttpRequest(url_add_database,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(ReportFragment.this, "Message Sent Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent09 = new Intent(ReportFragment.this, MainActivity.class);
                            startActivity(intent09);
                        }
                    });
                    //Intent i = new Intent(getApplicationContext(), Success.class);
                    // Toast.makeText(getBaseContext(),"Sent",Toast.LENGTH_SHORT).show();
                    //startActivity(i);

                    // closing this screen
                    //	finish();
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(ReportFragment.this, "Message Not Sent, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }



    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);

        viewImage.setImageBitmap(bitmap);

    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        start_year, start_month, start_day);
            case TIME_DIALOG_ID:

                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);

        }


        return null;


    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;

            updateTime(hour,minute);

        }

    };

    private static String utilTime(int value) {

        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }

    // Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        editText2.setText(aTime);
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

