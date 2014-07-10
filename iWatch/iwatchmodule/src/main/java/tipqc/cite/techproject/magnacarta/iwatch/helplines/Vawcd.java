package tipqc.cite.techproject.magnacarta.iwatch.helplines;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import tipqc.cite.techproject.magnacarta.R;

public class Vawcd extends Activity {

	private String call;
	EditText editText1, editText2;
	Button ButtonCall, ButtonCall2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vawcd);
		
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		ButtonCall = (Button) findViewById(R.id.button1);
		ButtonCall2 = (Button) findViewById(R.id.button3);
		onButtonClick();
	}
	
	private void onButtonClick(){
		
		ButtonCall.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				call = editText1.getText().toString();
				Intent i = new Intent(Intent.ACTION_CALL);
		        i.setData(Uri.parse("tel:" + call));
		        startActivity(i);			
				
			}
		});
		
ButtonCall2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				call = editText2.getText().toString();
				Intent i = new Intent(Intent.ACTION_CALL);
		        i.setData(Uri.parse("tel:" + call));
		        startActivity(i);			
				
			}
		});
		editText1.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		editText2.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
   		updateButtonState();
	}
   		void updateButtonState() {
}
}