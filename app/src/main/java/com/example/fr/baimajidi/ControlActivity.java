package com.example.fr.baimajidi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.goatenvmonitor.R;
//import com.goatenvmonitor.util.GetPostUtil;

import android.app.Activity;
import android.app.TabActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.AppIndex;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

//import androidx.appcompat.widget.Toolbar;

//import com.google.android.material.snackbar.Snackbar;
//import com.jaredrummler.materialspinner.MaterialSpinner;

//import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static com.example.fr.baimajidi.Login.admin;
import static com.example.fr.baimajidi.R.id.tab01;


///**
// * Created by FR on 2017/5/27.
// */
public class ControlActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener{
//    private static final String[] ANDROID_VERSIONS = {
//            "Cupcake",
//            "Donut",
//            "Eclair",
//            "Froyo",
//            "Gingerbread",
//            "Honeycomb",
//            "Ice Cream Sandwich",
//            "Jelly Bean",
//            "KitKat",
//            "Lollipop",
//            "Marshmallow",
//            "Nougat",
//            "Oreo"
//    };
    private GestureDetector mGestureDetector;
    private SharedPreferences sp;
    boolean flag;
    //定义一个网络访问请求对象
    String path="http://121.196.198.106:7500/myApps";
    //    private GoogleApiClient client;
    //定义一个线程对象
    HandlerThread handlerThread1 = new HandlerThread("myHandlerThread1");
    //温室1

    //手自动指令
    //private String shoudongcommand01_on = "MCMD@03010001";
    //private String zidongcommand01_on = "MCMD@03010002";
    //手自动按钮
    // private RadioButton shoudongbutton_01_on = null;
    //private RadioButton zidongbutton_01_on = null;

    //风扇一指令
    private String fengjionecommand01_on = "MCMD@03010101";
    private String fengjionecommand01_off = "MCMD@03010102";

    private RadioButton fengshan_01_on = null;
    private RadioButton fengshan_01_off = null;
    //风扇二指令
    private String fengjitwocommand01_on = "MCMD@03010103";
    private String fengjitwocommand01_off = "MCMD@03010104";

    private RadioButton fengshan_02_on = null;
    private RadioButton fengshan_02_off = null;
    //湿帘开关指令
    private String shiliancommand_on = "MCMD@03010105";
    private String shiliancommand_off = "MCMD@03010106";

    private RadioButton shilian_on = null;
    private RadioButton shilian_off = null;
    //灯光开关指令
    private String dengguangcommand_on = "MCMD@03010107";
    private String dengguangcommand_off = "MCMD@03010108";

    private RadioButton dengguang_on = null;
    private RadioButton dengguang_off = null;
    //翅片管开关指令
    private String chipianguancommand_on = "MCMD@03010109";
    private String chipianguancommand_off = "MCMD@03010110";

    private RadioButton chipianguan_on = null;
    private RadioButton chipianguan_off = null;
    private static final String TAG = null;
    //        private Handler handler;
//    private MyHandler mHandler1;
    private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout,shopping_img_bn_layout, show_img_bn_layout;
    //定义一个服务器返回对象
    private String mResponse;
    private String fj1_on = "MCMD@03010001";//启动
    private String fj1_off = "MCMD@03010002";//停止
    private String fj2_on = "MCMD@03010003";//启动
    private String fj2_off = "MCMD@03010004";//停止
    private String sl_on = "MCMD@03010005";//启动
    private String sl_off = "MCMD@03010006";//停止
    private String dg_on = "MCMD@03010007";//启动
    private String dg_off = "MCMD@03010008";//停止
    private String cpg_on = "MCMD@03010009";//启动
    private String cpg_off = "MCMD@03010010";//停止
    //定义一个handle， 用于处理服务器得到请求之后返回的信息
    private Handler handler1;
//    private String mResponse;
//    public String receive_str="";
   private String Tab = "1";
//private LinearLayout home_img_bn_Layout,style_img_bn_layout,cam_img_bn_layout,shopping_img_bn_layout,show_img_bn_layout;
Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 0x456) {
            // Toast出服务器响应的字符串
            // Toast.makeText(HomePage_Control_Fragment.this, mResponse, Toast.LENGTH_SHORT).show();

        }
    }
};
    //创建一个looper对象，用于循环消息队列
    Looper looper = Looper.myLooper();
  @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.control_layout);

      home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
      home_img_bn_Layout.setOnClickListener(clickListener_home);

      style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
      style_img_bn_layout.setOnClickListener(clickListener_style);


      cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
      cam_img_bn_layout.setOnClickListener(clickListener_cam);

      shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
      shopping_img_bn_layout.setOnClickListener(clickListener_shopping);
shopping_img_bn_layout.setSelected(true);
      show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
      show_img_bn_layout.setOnClickListener(clickListener_show);
   //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //  setSupportActionBar(toolbar);

      fengshan_01_on= (RadioButton) findViewById(R.id.fengshan_01_on);
     fengshan_01_off= (RadioButton) findViewById(R.id.fengshan_01_off);
      fengshan_02_on= (RadioButton) findViewById(R.id.fengshan_02_on);
      fengshan_02_off= (RadioButton) findViewById(R.id.fengshan_02_off);
      shilian_on= (RadioButton) findViewById(R.id.shilian_01_on);
      shilian_off= (RadioButton) findViewById(R.id.shilian_01_off);
      dengguang_on= (RadioButton) findViewById(R.id.dengguang_01_on);
      dengguang_off= (RadioButton) findViewById(R.id.dengguang_01_off);
      chipianguan_on= (RadioButton) findViewById(R.id.chipianguan_01_on);
      chipianguan_off= (RadioButton) findViewById(R.id.chipianguan_01_off);
      HandlerThread handlerThread = new HandlerThread("myHandlerThread");//�̵߳��ú���
      handlerThread.start();
      handler = new Handler(handlerThread.getLooper());
      handler.post(new MyRunnable());
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

    class MyRunnable implements Runnable
    {
        @Override
        public void run() {
            fengshan_01_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, fengjitwocommand01_on);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            fengshan_01_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, fengjitwocommand01_off);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            fengshan_02_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, fengjitwocommand01_on);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            fengshan_02_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, "zc@11111");
                                Log.d("main", mResponse);

                            }
                        }.start();
                    }
                }
            });
            shilian_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, shiliancommand_on);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            shilian_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, shiliancommand_off);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            dengguang_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, dengguangcommand_on);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            dengguang_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, dengguangcommand_off);
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
            chipianguan_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {

                                mResponse = GetPostUtil.sendPost(
                                        path, chipianguancommand_on);
                                Log.d("1111", mResponse);
                               //Toast.makeText(ControlActivity.this,"翅片管已打开",Toast.LENGTH_SHORT).show();
                            }
                        }.start();
                    }
                }
            });
            chipianguan_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO Auto-generated method stub
                    if (buttonView.isChecked()) {
                        new Thread() {
                            @Override
                            public void run() {
                                Log.i("main","点击了翅片管");
                                //Toast.makeText(getActivity(),"Toast",Toast.LENGTH_LONG).show();
                                //显示toast信息

                                mResponse = GetPostUtil.sendPost(
                                        path, "zc@11111");
                                Log.d("1111", mResponse);
                            }
                        }.start();
                    }
                }
            });
//        rl_1 = (RelativeLayout)view.findViewById(R.id.rl_d1);
//        rl_2 = (RelativeLayout)view.findViewById(R.id.rl_d2);
//       // rl_3 = (RelativeLayout)view.findViewById(R.id.rl_d3);
//       // rl_4 = (RelativeLayout)view.findViewById(R.id.rl_d4);
//        btn_d1 = (Button)view.findViewById(R.id.btn_d1);
//        btn_d2 = (Button)view.findViewById(R.id.btn_d2);
//       // btn_d3 = (Button)view.findViewById(R.id.btn_d3);
//       // btn_d4 = (Button)view.findViewById(R.id.btn_d4);
//        btn_d1.setOnClickListener(this);
//        btn_d2.setOnClickListener(this);
//        //btn_d3.setOnClickListener(this);
//       // btn_d4.setOnClickListener(this);
        }}
    //当用户


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_d1:
//                Intent intent1 = new Intent(getContext(),ContactUSActivity.class);
//                startActivity(intent1);
//                break;
//            case R.id.btn_d2:
//                Intent intent2 = new Intent(getContext(),ContactUSActivity.class);
//                startActivity(intent2);
//                break;
    // case R.id.btn_d3:
    // Intent intent3 = new Intent(getContext(),AboutProjectActivity.class);
    // startActivity(intent3);
    // break;
    // case R.id.btn_d4:
    //  Intent intent4 = new Intent(getContext(),ContactUSActivity.class);
    //  startActivity(intent4);
    //  break;

//}



   public boolean onKeyDown(int keyCode, KeyEvent event)
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
                ControlActivity.this.finish();
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
                Intent intent = new Intent();
                intent.setClass(ControlActivity.this, Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                ControlActivity.this.finish();
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
            intent.setClass(ControlActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();
        }
    };
    private View.OnClickListener clickListener_style = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(true);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(ControlActivity.this, Outdoor.class);
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
            intent.setClass(ControlActivity.this, CurveActivity.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
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
            intent.setClass(ControlActivity.this, ContactActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
            finish();
        }
    };






}
