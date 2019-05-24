package com.example.fr.baimajidi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ContactActivity extends Activity  {
	 private GestureDetector mGestureDetector;
	 private static final int FLING_MIN_DISTANCE = 50;
	 private static final int FLING_MIN_VELOCITY = 0;
	private SharedPreferences sp;
	Button ringagain1,ringagain2,ringagain3,ringagain4,ringagain5,ringagain6;
	int i=0;
	private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout,shopping_img_bn_layout, show_img_bn_layout;
	private RelativeLayout con;
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_layout);
//		sp = this.getSharedPreferences("setting", Context.MODE_WORLD_READABLE);
//		mGestureDetector = new GestureDetector(this);
//        con= (RelativeLayout)findViewById(R.id.con);
//        con.setOnTouchListener(this);
//        con.setLongClickable(true);
		//功能简介的工具
		ringagain1=(Button)findViewById(R.id.ringagain1);
		ringagain1.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
					// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ContactActivity.this, FunctionActivity.class);
				startActivity(intent);
			}
		});
		//更新当前版本的工具
			ringagain2=(Button)findViewById(R.id.ringagain2);
			ringagain2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(ContactActivity.this,"当前已是最新版本",Toast.LENGTH_SHORT).show();
				}
			});
			//帮助与反馈的工具
			ringagain3=(Button)findViewById(R.id.ringagain3);
			ringagain3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(ContactActivity.this, HelpActivity.class);
					startActivity(intent);
				}
			});
			//关于鸡舍终端的介绍工具
			ringagain4=(Button)findViewById(R.id.ringagain4);
			ringagain4.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(ContactActivity.this, AboutActivity.class);
					startActivity(intent);
				}
			});
			//关于
//			ringagain5=(Button)findViewById(R.id.ringagain5);
//			ringagain5.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Toast.makeText(ContactActivity.this,"联系我们",Toast.LENGTH_SHORT).show();
//				}
//			});
     // ringagain6 = (Button)findViewById(R.id.ringagain6);
     //  ringagain6.setOnClickListener(new OnClickListener() {
         //  @Override
         //  public void onClick(View v) {
               //Intent intent = new Intent();

				//Toast.makeText(ContactActivity.this,"联系我们",Toast.LENGTH_SHORT).show();
             //   startActivity(intent);
          // }
      // });
			
	      	home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
	        home_img_bn_Layout.setOnClickListener(clickListener_home);

	        shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
	        shopping_img_bn_layout.setOnClickListener(clickListener_shopping);

		    style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
		    style_img_bn_layout.setOnClickListener(clickListener_style);

		    cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
		    cam_img_bn_layout.setOnClickListener(clickListener_cam);
	        
	        show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
	        show_img_bn_layout.setOnClickListener(clickListener_show);
	        show_img_bn_layout.setSelected(true);
	}
	public boolean onTouch(View v, MotionEvent event) {
	    return mGestureDetector.onTouchEvent(event);   
	}
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//						   float velocityY) {
//		// TODO Auto-generated method stub
//		Log.e("view", "onFling");
//		if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
//                && Math.abs(velocityX) > FLING_MIN_VELOCITY){
//			Log.e("fling", "right");
//			if(sp.getBoolean("IFHUADONG", true))
//			{
//				Intent intent = new Intent();
//				intent.setClass(ContactActivity.this, Login.class);
//				startActivity(intent);
//				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//				finish();
//			}
//		}
//		return false;
//	}
 public boolean onKeyDown(int keyCode,KeyEvent event)
 {
 	if(keyCode== KeyEvent.KEYCODE_BACK)
 	{
			dialog();
 		return true;
 	}
     else
     {
         return super.onKeyDown(keyCode, event);
     }
 }
	protected void dialog() 
 {
 	Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
 		     "确认退出应用程序？").setPositiveButton("退出",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					    ContactActivity.this.finish();
					}
				}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).create();
 			dialog.show();
 }
	protected void dialog1()
    {
    	Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
    		     "确认注销当前帐号？").setPositiveButton("确定",new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
//						Intent intent = new Intent();
//						intent.setClass(ContactActivity.this, Login.class);
//						startActivity(intent);
//						overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
						ContactActivity.this.finish();
					}
				}).setNegativeButton("取消",new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).create();
    			dialog.show();
    }
	private OnClickListener clickListener_home = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(true);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(ContactActivity.this, MainActivity.class);
			startActivity(intent);
			//overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_style = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(true);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(ContactActivity.this, Outdoor.class);
			startActivity(intent);
			//overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_cam = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(true);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(ContactActivity.this, CurveActivity.class);
			startActivity(intent);
			//overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_shopping = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(true);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(ContactActivity.this, ControlActivity.class);
			startActivity(intent);
			//overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_show = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(true);
		}
	};

}

