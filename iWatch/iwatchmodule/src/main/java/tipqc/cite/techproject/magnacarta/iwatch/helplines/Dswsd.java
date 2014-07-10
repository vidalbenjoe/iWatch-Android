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

public class Dswsd extends Activity {

	private String call;
	EditText editText1;
	Button ButtonCall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dswd);
		
		editText1 = (EditText) findViewById(R.id.editText1);
		ButtonCall = (Button) findViewById(R.id.button1);
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
		editText1.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
   		updateButtonState();
	}
   		void updateButtonState() {
}
}