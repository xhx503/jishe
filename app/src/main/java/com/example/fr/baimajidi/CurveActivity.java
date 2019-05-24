package com.example.fr.baimajidi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CurveActivity extends TabActivity implements OnTouchListener, OnGestureListener {
	private GestureDetector mGestureDetector;
	private String mResponse;
	private String[] arr;
	private String[] arr2;
	private String[] arr3;
	private String[] arr4;
	private Button chaxun;
	private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout,shopping_img_bn_layout, show_img_bn_layout;
	private  String Tab="1";
	private String path = "http://121.196.198.106:8888/myApps";
//	String path="http://172.20.10.2:8888/myApps";
//	String path="http://192.168.43.232:8888/myApps";
//	String path="http://192.168.43.222:8888/myApps";
//	String path="http://192.168.127.200:8888/myApps";
	//private String[] room = new String[] {"连栋东区","连栋西区","单栋温室1", "单栋温室2","单栋温室3"};
	private String[] room = new String[] {"一号鸡舍","二号鸡舍","三号鸡舍", "四号鸡舍","五号鸡舍","六号鸡舍","七号鸡舍","八号鸡舍","鸡舍外部环境",};
	private String[] node = new String[]{"节点一"};
	//private String[][] pandc = new String[][]{{"节点一"},{"节点一"},{"1号房间","2号房间","3号房间","4号房间","5号房间","6号房间","7号房间","8号房间","9号房间","10号房间","11号房间","12号房间","13号房间","14号房间","15号房间","16号房间","17号房间","18号房间"},
	//		//	{"1号房间","2号房间","3号房间","4号房间","5号房间","6号房间","7号房间","8号房间","9号房间","10号房间","11号房间","12号房间"},
	//		//	{"1号房间","2号房间","3号房间","4号房间","5号房间","6号房间","7号房间","8号房间","9号房间","10号房间","11号房间","12号房间","13号房间","14号房间","15号房间","16号房间","17号房间","18号房间"}
	//		//	};
	private String[][] pandc = new String[][]{{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},{"温度","湿度","PM2.5含量","二氧化碳浓度","NH3浓度"},
			{"温度","湿度","降雨量","太阳辐射","风向","风力"},


		};
	private LineChart mWeekLineChart;
	private String[] mWeekItems = new String[]{"1", "2", "3","4","5","6","7","8","9","10","11","12"};
	private float[] mWeekPoints1 = new float[]{0, 0, 0,0, 0, 0,0,0,0,0,0,0};
	private float[] mWeekPoints2 = new float[]{0, 0, 0,0, 0, 0,0,0,0,0,0,0};
	private float[] mWeekPoints3 = new float[]{0, 0, 0,0, 0, 0,0,0,0,0,0,0};
	private float[] mWeekPoints4 = new float[]{0, 0, 0,0, 0, 0,0,0,0,0,0,0};
	private List<LineChartData> dataList1 = new ArrayList<>();
	private List<LineChartData> dataList2 = new ArrayList<>();
	private List<LineChartData> dataList3 = new ArrayList<>();
	private List<LineChartData> dataList4 = new ArrayList<>();
	private Spinner sp1_1,sp1_2,sp2_1,sp2_2,sp3_1,sp3_2,sp4_1,sp4_2;
	private int pos1_1=1,pos1_2=1,pos2_1=1,pos2_2=1,pos3_1=1,pos3_2=1,pos4_1=1,pos4_2=1;
	private Context context;
	ArrayAdapter<String> adapter ;
	ArrayAdapter<String> adapter2;
	TabWidget tabWidget ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.curve_layout);

		TabHost tabHost = getTabHost();
	//tabHost.getTabWidget().setDividerDrawable(null);
		//tabHost.getCurrentTab().tabIndicatorFullWidth=false;
		tabWidget = tabHost.getTabWidget();
		TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1").setIndicator("历").setContent(R.id.tab01);
		tabHost.addTab(tab1);
		TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2").setIndicator("史").setContent(R.id.tab02);
		tabHost.addTab(tab2);
		TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3").setIndicator("曲").setContent(R.id.tab03);
		tabHost.addTab(tab3);
		TabHost.TabSpec tab4 = tabHost.newTabSpec("tab4").setIndicator("线").setContent(R.id.tab04);
		tabHost.addTab(tab4);
		for (int i =0; i < tabWidget.getChildCount(); i++) {
			//修改Tabhost高度和宽度
			tabWidget.getChildAt(i).getLayoutParams().height = 130;
			tabWidget.getChildAt(i).getLayoutParams().width =105;
			//修改显示字体大小
			TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
			tv.setTextSize(20);
		}
		tabHost.setOnTabChangedListener(changeLis);
		home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
		home_img_bn_Layout.setOnClickListener(clickListener_home);


		style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
		style_img_bn_layout.setOnClickListener(clickListener_style);

		cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
		cam_img_bn_layout.setOnClickListener(clickListener_cam);
		cam_img_bn_layout.setSelected(true);

		shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
		shopping_img_bn_layout.setOnClickListener(clickListener_shopping);

		show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
		show_img_bn_layout.setOnClickListener(clickListener_show);

		chaxun = (Button) findViewById(R.id.chaxun);
		chaxun.setOnClickListener(clickListener_chaxun);

		mGestureDetector = new GestureDetector(this);
		mWeekLineChart = (LineChart) findViewById(R.id.line_chart_week);
		context = this;
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, room);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1_1 = (Spinner) findViewById(R.id.room1);
		sp1_1.setAdapter(adapter);
		sp1_1.setOnItemSelectedListener(selectListener1);
		adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, node);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1_2 = (Spinner) findViewById(R.id.node1);
		sp1_2.setAdapter(adapter2);
		sp1_2.setOnItemSelectedListener(selectListener5);

		sp2_1 = (Spinner) findViewById(R.id.room2);
		sp2_1.setAdapter(adapter);
	sp2_1.setOnItemSelectedListener(selectListener2);
		adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, node);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2_2 = (Spinner) findViewById(R.id.node2);
		sp2_2.setAdapter(adapter2);
		sp2_2.setOnItemSelectedListener(selectListener6);

		sp3_1 = (Spinner) findViewById(R.id.room3);
		sp3_1.setAdapter(adapter);
		sp3_1.setOnItemSelectedListener(selectListener3);
		adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, node);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp3_2 = (Spinner) findViewById(R.id.node3);
		sp3_2.setAdapter(adapter2);
		sp3_2.setOnItemSelectedListener(selectListener7);

		sp4_1 = (Spinner) findViewById(R.id.room4);
		sp4_1.setAdapter(adapter);
		sp4_1.setOnItemSelectedListener(selectListener4);
		adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, node);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp4_2 = (Spinner) findViewById(R.id.node4);
		sp4_2.setAdapter(adapter2);
		sp4_2.setOnItemSelectedListener(selectListener8);
	}


	private TabHost.OnTabChangeListener changeLis= new TabHost.OnTabChangeListener() {
		public void onTabChanged(String tabId) {
			if(tabId.equals("tab1"))
			{
				Tab = "1";
			}
			else if(tabId.equals("tab2"))
			{
				Tab = "2";
			}
			else if(tabId.equals("tab3"))
			{
				Tab = "3";
			}
			else if(tabId.equals("tab4"))
			{
				Tab = "4";
			}
		}
	};

	private AdapterView.OnItemSelectedListener selectListener1 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			int pos = sp1_1.getSelectedItemPosition();
			pos1_1 = position+1;
			adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, pandc[pos]);
			sp1_2.setAdapter(adapter2);
			}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener5 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			pos1_2 = position+1;
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener2 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			int pos = sp2_1.getSelectedItemPosition();
			pos2_1 = position+1;
			adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, pandc[pos]);
			sp2_2.setAdapter(adapter2);
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener6 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			pos2_2 = position+1;
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener3 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			int pos = sp3_1.getSelectedItemPosition();
			pos3_1 = position+1;
			adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, pandc[pos]);
			sp3_2.setAdapter(adapter2);
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener7 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			pos3_2 = position+1;
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener4 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			int pos = sp4_1.getSelectedItemPosition();
			pos4_1 = position+1;
			adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, pandc[pos]);
			sp4_2.setAdapter(adapter2);
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};
	private AdapterView.OnItemSelectedListener selectListener8 = new AdapterView.OnItemSelectedListener(){
		public void onItemSelected(AdapterView parent, View v, int position, long id){
			pos4_2 = position+1;
		}
		public void onNothingSelected(AdapterView arg0){
		}
	};

	public boolean onTouch(View v, MotionEvent event) {
	    return mGestureDetector.onTouchEvent(event);   
	}

	private OnClickListener clickListener_chaxun = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (Tab=="1"){
				new Thread() {
					@Override
					public void run() {
						mResponse = GetPostUtil.sendPost(path, "data@temp"+"@"+pos1_1+"@"+pos1_2);
						Log.d("1111",mResponse);
						// 发送消息通知UI线程更新UI组件
						handler.sendEmptyMessage(0x123);
					}
				}.start();
			}else if (Tab=="2"){
				new Thread() {
					@Override
					public void run() {
						mResponse = GetPostUtil.sendPost(path, "data@humi"+"@"+pos2_1+"@"+pos2_2);
						Log.d("1111",mResponse);
						// 发送消息通知UI线程更新UI组件
						handler.sendEmptyMessage(0x456);
					}
				}.start();
			}else if (Tab=="3"){
				new Thread() {
					@Override
					public void run() {
						mResponse = GetPostUtil.sendPost(path, "data@sun"+"@"+pos3_1+"@"+pos3_2);
						Log.d("1111",mResponse);
						// 发送消息通知UI线程更新UI组件
						handler.sendEmptyMessage(0x789);
					}
				}.start();
			}else {
				new Thread() {
					@Override
					public void run() {
						mResponse = GetPostUtil.sendPost(path, "data@co2"+"@"+pos4_1+"@"+pos4_2);
						Log.d("1111",mResponse);
						// 发送消息通知UI线程更新UI组件
						handler.sendEmptyMessage(0x101);
					}
				}.start();
			}
		}
	};
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dataList1.clear();
			dataList2.clear();
			dataList3.clear();
			dataList4.clear();
			if (msg.what == 0x123) {
				// Toast出服务器响应的字符串
				//Toast.makeText(MainActivity.this, mResponse, Toast.LENGTH_SHORT).show();
				arr=mResponse.split(",");
				dataList1.clear();
				dataList2.clear();
				dataList3.clear();
				dataList4.clear();
				try {
					if (arr[0]!=null){
						mWeekPoints1[0] = Float.parseFloat(arr[0]);
					}
				} catch (Exception e) {
					mWeekPoints1[0]=0;
				}
				try {
					if (arr[1]!=null){
						mWeekPoints1[1] = Float.parseFloat(arr[1]);
					}
				} catch (Exception e) {
					mWeekPoints1[1]=0;
				}
				try {
					if (arr[2]!=null){
						mWeekPoints1[2] = Float.parseFloat(arr[2]);
					}
				} catch (Exception e) {
					mWeekPoints1[2]=0;
				}
				try {
					if (arr[3]!=null){
						mWeekPoints1[3] = Float.parseFloat(arr[3]);
					}
				} catch (Exception e) {
					mWeekPoints1[3]=0;
				}
				try {
					if (arr[4]!=null){
						mWeekPoints1[4] = Float.parseFloat(arr[4]);
					}
				} catch (Exception e) {
					mWeekPoints1[4]=0;
				}
				try {
					if (arr[5]!=null){
						mWeekPoints1[5] = Float.parseFloat(arr[5]);
					}
				} catch (Exception e) {
					mWeekPoints1[5]=0;
				}
				try {
					if (arr[6]!=null){
						mWeekPoints1[6] = Float.parseFloat(arr[6]);
					}
				} catch (Exception e) {
					mWeekPoints1[6]=0;
				}
				try {
					if (arr[7]!=null){
						mWeekPoints1[7] = Float.parseFloat(arr[7]);
					}
				} catch (Exception e) {
					mWeekPoints1[7]=0;
				}
				try {
					if (arr[8]!=null){
						mWeekPoints1[8] = Float.parseFloat(arr[8]);
					}
				} catch (Exception e) {
					mWeekPoints1[8]=0;
				}
				try {
					if (arr[9]!=null){
						mWeekPoints1[9] = Float.parseFloat(arr[9]);
					}
				} catch (Exception e) {
					mWeekPoints1[9]=0;
				}
				try {
					if (arr[10]!=null){
						mWeekPoints1[10] = Float.parseFloat(arr[10]);
					}
				} catch (Exception e) {
					mWeekPoints1[10]=0;
				}
				try {
					if (arr[11]!=null){
						mWeekPoints1[11] = Float.parseFloat(arr[11]);
					}
				} catch (Exception e) {
					mWeekPoints1[11]=0;
				}
				for (int i = 0; i < mWeekItems.length; i++) {
					LineChartData data = new LineChartData();
					data.setItem(mWeekItems[i]);
					data.setPoint(mWeekPoints1[i]);
					dataList1.add(data);
				}
				mWeekLineChart.setData(dataList1);
			}else if (msg.what == 0x456){
				arr2=mResponse.split(",");
				dataList1.clear();
				dataList2.clear();
				dataList3.clear();
				dataList4.clear();
				try {
					if (arr2[0]!=null){
						mWeekPoints2[0] = Float.parseFloat(arr2[0]);
					}
				} catch (Exception e) {
					mWeekPoints2[0]=0;
				}
				try {
					if (arr2[1]!=null){
						mWeekPoints2[1] = Float.parseFloat(arr2[1]);
					}
				} catch (Exception e) {
					mWeekPoints2[1]=0;
				}
				try {
					if (arr2[2]!=null){
						mWeekPoints2[2] = Float.parseFloat(arr2[2]);
					}
				} catch (Exception e) {
					mWeekPoints2[2]=0;
				}
				try {
					if (arr2[3]!=null){
						mWeekPoints2[3] = Float.parseFloat(arr2[3]);
					}
				} catch (Exception e) {
					mWeekPoints2[3]=0;
				}
				try {
					if (arr2[4]!=null){
						mWeekPoints2[4] = Float.parseFloat(arr2[4]);
					}
				} catch (Exception e) {
					mWeekPoints2[4]=0;
				}
				try {
					if (arr2[5]!=null){
						mWeekPoints2[5] = Float.parseFloat(arr2[5]);
					}
				} catch (Exception e) {
					mWeekPoints2[5]=0;
				}
				try {
					if (arr2[6]!=null){
						mWeekPoints2[6] = Float.parseFloat(arr2[6]);
					}
				} catch (Exception e) {
					mWeekPoints2[6]=0;
				}
				try {
					if (arr2[7]!=null){
						mWeekPoints2[7] = Float.parseFloat(arr2[7]);
					}
				} catch (Exception e) {
					mWeekPoints2[7]=0;
				}
				try {
					if (arr2[8]!=null){
						mWeekPoints2[8] = Float.parseFloat(arr2[8]);
					}
				} catch (Exception e) {
					mWeekPoints2[8]=0;
				}
				try {
					if (arr2[9]!=null){
						mWeekPoints2[9] = Float.parseFloat(arr2[9]);
					}
				} catch (Exception e) {
					mWeekPoints2[9]=0;
				}
				try {
					if (arr2[10]!=null){
						mWeekPoints2[10] = Float.parseFloat(arr2[10]);
					}
				} catch (Exception e) {
					mWeekPoints2[10]=0;
				}
				try {
					if (arr2[11]!=null){
						mWeekPoints2[11] = Float.parseFloat(arr2[11]);
					}
				} catch (Exception e) {
					mWeekPoints2[11]=0;
				}
				for (int i = 0; i < mWeekItems.length; i++) {
					LineChartData data2 = new LineChartData();
					data2.setItem(mWeekItems[i]);
					data2.setPoint(mWeekPoints2[i]);
					dataList2.add(data2);
				}
				mWeekLineChart.setData(dataList2);
			}else if (msg.what == 0x789){
				arr3=mResponse.split(",");
				dataList1.clear();
				dataList2.clear();
				dataList3.clear();
				dataList4.clear();
				try {
					if (arr3[0]!=null){
						mWeekPoints3[0] = Float.parseFloat(arr3[0]);
					}
				} catch (Exception e) {
					mWeekPoints3[0]=0;
				}
				try {
					if (arr3[1]!=null){
						mWeekPoints3[1] = Float.parseFloat(arr3[1]);
					}
				} catch (Exception e) {
					mWeekPoints3[1]=0;
				}
				try {
					if (arr3[2]!=null){
						mWeekPoints3[2] = Float.parseFloat(arr3[2]);
					}
				} catch (Exception e) {
					mWeekPoints3[2]=0;
				}
				try {
					if (arr3[3]!=null){
						mWeekPoints3[3] = Float.parseFloat(arr3[3]);
					}
				} catch (Exception e) {
					mWeekPoints3[3]=0;
				}
				try {
					if (arr3[4]!=null){
						mWeekPoints3[4] = Float.parseFloat(arr3[4]);
					}
				} catch (Exception e) {
					mWeekPoints3[4]=0;
				}
				try {
					if (arr3[5]!=null){
						mWeekPoints3[5] = Float.parseFloat(arr3[5]);
					}
				} catch (Exception e) {
					mWeekPoints3[5]=0;
				}
				try {
					if (arr3[6]!=null){
						mWeekPoints3[6] = Float.parseFloat(arr3[6]);
					}
				} catch (Exception e) {
					mWeekPoints3[6]=0;
				}
				try {
					if (arr3[7]!=null){
						mWeekPoints3[7] = Float.parseFloat(arr3[7]);
					}
				} catch (Exception e) {
					mWeekPoints3[7]=0;
				}
				try {
					if (arr3[8]!=null){
						mWeekPoints3[8] = Float.parseFloat(arr3[8]);
					}
				} catch (Exception e) {
					mWeekPoints3[8]=0;
				}
				try {
					if (arr3[9]!=null){
						mWeekPoints3[9] = Float.parseFloat(arr3[9]);
					}
				} catch (Exception e) {
					mWeekPoints3[9]=0;
				}
				try {
					if (arr3[10]!=null){
						mWeekPoints3[10] = Float.parseFloat(arr3[10]);
					}
				} catch (Exception e) {
					mWeekPoints3[10]=0;
				}
				try {
					if (arr3[11]!=null){
						mWeekPoints3[11] = Float.parseFloat(arr3[11]);
					}
				} catch (Exception e) {
					mWeekPoints3[11]=0;
				}
				for (int i = 0; i < mWeekItems.length; i++) {
					LineChartData data3 = new LineChartData();
					data3.setItem(mWeekItems[i]);
					data3.setPoint(mWeekPoints3[i]);
					dataList3.add(data3);
				}
				mWeekLineChart.setData(dataList3);
			}else if (msg.what == 0x101){
				arr4=mResponse.split(",");
				dataList1.clear();
				dataList2.clear();
				dataList3.clear();
				dataList4.clear();
				try {
					if (arr4[0]!=null){
						mWeekPoints4[0] = Float.parseFloat(arr4[0]);
					}
				} catch (Exception e) {
					mWeekPoints4[0]=0;
				}
				try {
					if (arr4[1]!=null){
						mWeekPoints4[1] = Float.parseFloat(arr4[1]);
					}
				} catch (Exception e) {
					mWeekPoints4[1]=0;
				}
				try {
					if (arr4[2]!=null){
						mWeekPoints4[2] = Float.parseFloat(arr4[2]);
					}
				} catch (Exception e) {
					mWeekPoints4[2]=0;
				}
				try {
					if (arr4[3]!=null){
						mWeekPoints4[3] = Float.parseFloat(arr4[3]);
					}
				} catch (Exception e) {
					mWeekPoints4[3]=0;
				}
				try {
					if (arr4[4]!=null){
						mWeekPoints4[4] = Float.parseFloat(arr4[4]);
					}
				} catch (Exception e) {
					mWeekPoints4[4]=0;
				}
				try {
					if (arr4[5]!=null){
						mWeekPoints4[5] = Float.parseFloat(arr4[5]);
					}
				} catch (Exception e) {
					mWeekPoints4[5]=0;
				}
				try {
					if (arr4[6]!=null){
						mWeekPoints4[6] = Float.parseFloat(arr4[6]);
					}
				} catch (Exception e) {
					mWeekPoints4[6]=0;
				}
				try {
					if (arr4[7]!=null){
						mWeekPoints4[7] = Float.parseFloat(arr4[7]);
					}
				} catch (Exception e) {
					mWeekPoints4[7]=0;
				}
				try {
					if (arr4[8]!=null){
						mWeekPoints4[8] = Float.parseFloat(arr4[8]);
					}
				} catch (Exception e) {
					mWeekPoints4[8]=0;
				}
				try {
					if (arr4[9]!=null){
						mWeekPoints4[9] = Float.parseFloat(arr4[9]);
					}
				} catch (Exception e) {
					mWeekPoints4[9]=0;
				}
				try {
					if (arr4[10]!=null){
						mWeekPoints4[10] = Float.parseFloat(arr4[10]);
					}
				} catch (Exception e) {
					mWeekPoints4[10]=0;
				}
				try {
					if (arr4[11]!=null){
						mWeekPoints4[11] = Float.parseFloat(arr4[11]);
					}
				} catch (Exception e) {
					mWeekPoints4[11]=0;
				}
				for (int i = 0; i < mWeekItems.length; i++) {
					LineChartData data = new LineChartData();
					data.setItem(mWeekItems[i]);
					data.setPoint(mWeekPoints4[i]);
					dataList4.add(data);
				}
				mWeekLineChart.setData(dataList4);
			}
		}
	};

 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
						float velocityY) {
		// TODO Auto-generated method stub
		Log.e("view", "onFling");
		return false;
	}
	protected void dialog() 
    {
    	Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
    		     "确认退出应用程序？").setPositiveButton("退出",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					    CurveActivity.this.finish();
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
				intent.setClass(CurveActivity.this,MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
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
			intent.setClass(CurveActivity.this, Outdoor.class);
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
			intent.setClass(CurveActivity.this,   ControlActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
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
			Intent intent = new Intent();
			intent.setClass(CurveActivity.this, ContactActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};

	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        // TODO Auto-generated method stub
	        if(item.getItemId()==1){
	        		Intent intent = new Intent();
	        		intent.setClass(CurveActivity.this,Login.class);
	        		startActivity(intent);
	        		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	                finish();
	              }
	        if(item.getItemId()==2){
              finish();
            }
	        return super.onMenuItemSelected(featureId, item);
	    }
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
							float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}

