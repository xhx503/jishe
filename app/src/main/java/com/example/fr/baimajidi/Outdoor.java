package com.example.fr.baimajidi;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Calendar;
import java.util.Date;

public class Outdoor extends AppCompatActivity implements GestureDetector.OnGestureListener, View.OnTouchListener{
    private LinearLayout home_img_bn_Layout, shopping_img_bn_layout, show_img_bn_layout, style_img_bn_layout, cam_img_bn_layout;
    private Handler handler1;
    private String[] arr ;
    private TextView tempnum1;
    private TextView sun1num1;
    private TextView shidunum1;
    private TextView jiangyunum1;
    private TextView fengxiangnum1;
    private TextView fengsunum1;
    private ImageButton title_logo_img;
    private RelativeLayout relativesearch;
    private ViewFlipper viewFlipper;
    private GestureDetector mGestureDetector;
    private int currentPage = 0;
    private String mResponse;
    private boolean showNext = true;
    private boolean isRun = true;
    private final int SHOW_NEXT = 0011;
    private TextView date_TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);


        date_TextView = (TextView) findViewById(R.id.home_date_tv);
        date_TextView.setText(getDate());

        title_logo_img = (ImageButton) findViewById(R.id.title_logo_img);

        title_logo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setData(Uri.parse("http://www.njau.edu.cn/"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
//
//                relativesearch = (RelativeLayout) findViewById(R.id.relativesearch);
//
//                relativesearch.setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View v)
//                    {
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this, LineActivity.class);
//                        startActivity(intent);
//                    }
//                });

        home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
        home_img_bn_Layout.setOnClickListener(clickListener_home);


        style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
        style_img_bn_layout.setOnClickListener(clickListener);
style_img_bn_layout.setSelected(true);
        cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
        cam_img_bn_layout.setOnClickListener(clickListener_cam);

        shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
        shopping_img_bn_layout.setOnClickListener(clickListener_shopping);

        show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
        show_img_bn_layout.setOnClickListener(clickListener_show);

        Looper looper = Looper.myLooper();//ȡ�õ�ǰ�߳����looper,�������̵߳�looper
//        mHandler1 = new MyHandler(looper);//����һ��handlerʹ֮����looperͨ��

        HandlerThread handlerThread1 = new HandlerThread("myHandlerThread1");//����scoket�����߳�
        handlerThread1.start();
        handler1 = new Handler(handlerThread1.getLooper());
        handler1.post(new MyRunnable());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        viewFlipper = (ViewFlipper) findViewById(R.id.mViewFliper_vf);
        mGestureDetector = new GestureDetector(this);
        viewFlipper.setOnTouchListener(this);
        viewFlipper.setLongClickable(true);
        viewFlipper.setOnClickListener(clickListener);
        displayRatio_selelct(currentPage);
        style_img_bn_layout.setSelected(true);
        thread.start();

    }

    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            isRun = false;
            dialog();
            return true;
        }
        else
        {
            return super.onKeyDown(keyCode, event);
        }
    }

    class MyRunnable implements Runnable
    {
        @Override
        public void run() {
            mResponse = GetPostUtil.sendPost(
                    "http://121.196.198.106:8888/myApps" ,
//                    "http://172.20.10.2:8888/myApps",
//                    "http://192.168.43.232:8888/myApps",
//                    "http://192.168.127.200:8888/myApps",
                    "qxzdata@");
            Log.d("1111",mResponse);
            // 发送消息通知UI线程更新UI组件
            handler.sendEmptyMessage(0x456);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0x456) {
                // Toast出服务器响应的字符串
                //Toast.makeText(MainActivity.this, mResponse, Toast.LENGTH_SHORT).show();
                tempnum1 = (TextView) findViewById(R.id.temp1);
                shidunum1 = (TextView) findViewById(R.id.shidu1);
                jiangyunum1 = (TextView) findViewById(R.id.jiangyu_tv);
                sun1num1 = (TextView) findViewById(R.id.sun1);
                fengxiangnum1 = (TextView) findViewById(R.id.winddirection_tv);
                fengsunum1 = (TextView) findViewById(R.id.windspeed_tv);
                arr=mResponse.split(",");
                if (arr.length>=6){
                    String temp=arr[0];
                    String shidu=arr[1];
                    String jiangyu=arr[3];
                    String light=arr[2];
                    int  fengxiang=Integer.parseInt(arr[4]);
                    String fengsu= arr[5];
                    if (fengxiang > 348.76 || fengxiang <= 11.25)
                        fengxiangnum1.setText("北");
                    else if (fengxiang > 11.15 && fengxiang <= 33.75)
                        fengxiangnum1.setText("北东北");
                    else if (fengxiang > 33.76 && fengxiang <= 56.25)
                        fengxiangnum1.setText("东北");
                    else if (fengxiang > 56.26 && fengxiang <= 78.75)
                        fengxiangnum1.setText("东东北");
                    else if (fengxiang > 78.76 && fengxiang <= 101.25)
                        fengxiangnum1.setText("东");
                    else if (fengxiang > 101.26 && fengxiang <= 123.75)
                        fengxiangnum1.setText("东东南");
                    else if (fengxiang > 123.76 && fengxiang <= 146.25)
                        fengxiangnum1.setText("东南");
                    else if (fengxiang > 146.26 && fengxiang <= 168.75)
                        fengxiangnum1.setText("南东南");
                    else if (fengxiang > 168.76 && fengxiang <= 191.25)
                        fengxiangnum1.setText("南");
                    else if (fengxiang > 191.26 && fengxiang <= 213.75)
                        fengxiangnum1.setText("南西南");
                    else if (fengxiang > 213.76 && fengxiang <= 236.25)
                        fengxiangnum1.setText("西南");
                    else if (fengxiang > 236.26 && fengxiang <= 258.75)
                        fengxiangnum1.setText("西西南");
                    else if (fengxiang > 258.76 && fengxiang <= 281.25)
                        fengxiangnum1.setText("西");
                    else if (fengxiang > 281.26 && fengxiang <= 303.75)
                        fengxiangnum1.setText("西西北");
                    else if (fengxiang > 303.76 && fengxiang <= 326.25)
                        fengxiangnum1.setText("西北");
                    else if (fengxiang> 326.26 && fengxiang <= 348.75)
                        fengxiangnum1.setText("北西北");
                    tempnum1.setText(temp+"℃");
                    shidunum1.setText(shidu+"%");
                    jiangyunum1.setText(jiangyu+"cm/h");
                    sun1num1.setText(light+"W/m2");
                    fengsunum1.setText(fengsu+"km/h");
                } else {
                    Toast.makeText(Outdoor.this, "网络出现问题，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    protected void dialog() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
                "确认退出应用程序？").setPositiveButton("退出", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Outdoor.this.finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).create();
        dialog.show();
    }

    private View.OnClickListener ClickListener_style = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(true);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
        }
    };
    private View.OnClickListener clickListener_home = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(true);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(Outdoor.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();

        }
    };
    private View.OnClickListener clickListener_cam = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(true);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(Outdoor.this, CurveActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();
        }
    };
    private View.OnClickListener clickListener_shopping = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(true);
            show_img_bn_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(Outdoor.this, ControlActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();
        }
    };

    private View.OnClickListener clickListener_show = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(true);
            Intent intent = new Intent();
            intent.setClass(Outdoor.this, ContactActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }
    };

    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case SHOW_NEXT:
                    if (showNext) {
                        showNextView();
                    } else {
                        showPreviousView();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    Thread thread = new Thread(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(isRun){
                try {
                    Thread.sleep(60 * 60);
                    Message msg = new Message();
                    msg.what = SHOW_NEXT;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    };

    private void showNextView(){

        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
        viewFlipper.showNext();
        currentPage ++;
        if (currentPage == viewFlipper.getChildCount()) {
            displayRatio_normal(currentPage - 1);
            currentPage = 0;
            displayRatio_selelct(currentPage);
        } else {
            displayRatio_selelct(currentPage);
            displayRatio_normal(currentPage - 1);
        }
        Log.e("currentPage", currentPage + "");

    }
    private void showPreviousView(){
        displayRatio_selelct(currentPage);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
        viewFlipper.showPrevious();
        currentPage --;
        if (currentPage == -1) {
            displayRatio_normal(currentPage + 1);
            currentPage = viewFlipper.getChildCount() - 1;
            displayRatio_selelct(currentPage);
        } else {
            displayRatio_selelct(currentPage);
            displayRatio_normal(currentPage + 1);
        }
        Log.e("currentPage", currentPage + "");
    }
    private void displayRatio_selelct(int id){
        int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
        ImageView img = (ImageView)findViewById(ratioId[id]);
        img.setSelected(true);
    }
    private void displayRatio_normal(int id){
        int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
        ImageView img = (ImageView)findViewById(ratioId[id]);
        img.setSelected(false);

    }

    private String getDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int w = c.get(Calendar.DAY_OF_WEEK) - 1 ;
        if (w < 0) {
            w = 0;
        }
        int month=(int)c.get(Calendar.MONTH)+1;
        String mDate = c.get(Calendar.YEAR)+"年" + month + "月" + c.get(Calendar.DATE) + "日  " + weekDays[w];
        return mDate;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
