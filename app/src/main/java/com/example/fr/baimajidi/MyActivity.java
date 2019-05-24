package com.example.fr.baimajidi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 4000; // �ӳ�4��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                 WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.acitvity_layout);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = new Intent(MyActivity.this,Login.class);
				MyActivity.this.startActivity(mainIntent);
				overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
				MyActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);

	}
}




