package com.example.fr.baimajidi;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		TextView jieshao=(TextView)findViewById(R.id.jieshao);
		jieshao.setMovementMethod(ScrollingMovementMethod.getInstance());
		Button title_set_bn=(Button)findViewById(R.id.title_set_bn);
        title_set_bn.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        });
	}
}
