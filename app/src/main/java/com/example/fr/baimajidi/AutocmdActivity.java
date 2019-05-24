package com.example.fr.baimajidi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AutocmdActivity extends AppCompatActivity {

    //    String path="http://192.168.1.200:8888/myApps";
//    String path="http://192.168.43.222:8888/myApps";
//    String path="http://192.168.127.200:8888/myApps";
    String path="http://121.196.198.106:8888/myApps";
//    String path="http://172.20.10.2:8888/myApps";
//    String path="http://192.168.43.232:8888/myApps";
//    String path="http://192.168.43.222:8888/myApps";
    private Spinner area = null;
    private Spinner house = null;
    private EditText wendu,shidumin,shidumax,guangzhaomin,guangzhaomax,co2min,co2max;
    private Button set,reset;
    private String wendunum,shidunummin,shidunummax,guangzhaonummin,guangzhaonummax,co2nummin,co2nummax;
    private String[][] houseData = new String[][]{
            {"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号","13号","14号","15号","16号","17号","18号"},
            {"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号"},
            {"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号","13号","14号","15号","16号","17号","18号"},
            {"1组", "2组", "3组", "4组", "5组","6组"},
            {"1组", "2组", "3组", "4组", "5组","6组"}
    };
    private String[] houseData1 = new String[]{"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号","13号","14号","15号","16号","17号","18号"};
    private String[] houseData2 = new String[]{"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号"};
    private String[] houseData3 = new String[]{"1组", "2组", "3组", "4组", "5组","6组"};
    private ArrayAdapter<CharSequence> adapterHouse = null;
    private URL url;
    HttpURLConnection urlConn=null;
    private String areanum = "0";
    private String housenum = "0";
    private String firstnum;
    private String lastnum;
    private int spinnernum1;
    private String value;
    private int num2,num3,num4,num5,num6,num7;
    private String user;
    private String privilege;
    private String[] strs;
    private String[] kekongfangjian;
    private PrivilegeHelper ph;
    private SQLiteDatabase db;
    private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocmd_layout);
        Button title_set_bn=(Button)findViewById(R.id.title_set_bn);
        title_set_bn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        area = (Spinner)findViewById(R.id.spinnerquyu);
        house = (Spinner)findViewById(R.id.spinnerfangjian);
        wendu = (EditText)findViewById(R.id.etwendu);
        shidumin = (EditText)findViewById(R.id.etshidumin);
        guangzhaomin = (EditText)findViewById(R.id.etguangzhaodumin);
        co2min  = (EditText)findViewById(R.id.etco2nongdumin);
        shidumax = (EditText)findViewById(R.id.etshidumax);
        guangzhaomax = (EditText)findViewById(R.id.etguangzhaodumax);
        co2max = (EditText)findViewById(R.id.etco2nongdumax) ;
        set = (Button)findViewById(R.id.btnset);
        reset = (Button)findViewById(R.id.btnreset);

        wendu.setText("");
        shidumin.setText("");
        guangzhaomin.setText("");
        co2min.setText("");
        shidumax.setText("");
        guangzhaomax.setText("");
        co2max.setText("");

        area.setOnItemSelectedListener(new OnItemSelectedListener1());
        house.setOnItemSelectedListener(new OnItemSelectedListener2());

        setfocused();
        set.setOnClickListener(new OnClickListenerSet());
        reset.setOnClickListener(new OnClickListenerReset());

        ph = new PrivilegeHelper(AutocmdActivity.this,"Privilege.db",null,1);
        db = ph.getWritableDatabase();
        Cursor cursor = db.query("privilege_table",null,null,null,null,null,null);
        if(cursor.moveToLast()){
            user = cursor.getString(cursor.getColumnIndex("user"));
            privilege = cursor.getString(cursor.getColumnIndex("privilege"));

        }
        strs=privilege.split("@");
//        Toast.makeText(this,privilege,Toast.LENGTH_LONG).show();
//        Toast.makeText(this,user+strs[6],Toast.LENGTH_LONG).show();
        try{
            System.arraycopy(strs,4,kekongfangjian,0,strs.length-4);
        }catch(Exception e){
            e.printStackTrace();
        }
//        Toast.makeText(this,kekongfangjian[0],Toast.LENGTH_SHORT).show();

        map = new HashMap<>();
        map.put("0101","06010101");
        map.put("0102","06010102");
        map.put("0103","06010103");
        map.put("0104","06010104");
        map.put("0105","06010105");
        map.put("0106","06010106");
        map.put("0107","06010107");
        map.put("0108","06010108");
        map.put("0109","06010109");
        map.put("0110","06010110");
        map.put("0111","06010111");
        map.put("0112","06010112");
        map.put("0113","06010113");
        map.put("0114","06010114");
        map.put("0115","06010115");
        map.put("0116","06010116");
        map.put("0117","06010117");
        map.put("0118","06010118");
        map.put("0201","06010201");
        map.put("0202","06010202");
        map.put("0203","06010203");
        map.put("0204","06010204");
        map.put("0205","06010205");
        map.put("0206","06010206");
        map.put("0207","06010207");
        map.put("0208","06010208");
        map.put("0209","06010209");
        map.put("0210","06010210");
        map.put("0211","06010211");
        map.put("0212","06010212");
        map.put("0301","06010301");
        map.put("0302","06010302");
        map.put("0303","06010303");
        map.put("0304","06010304");
        map.put("0305","06010305");
        map.put("0306","06010306");
        map.put("0307","06010307");
        map.put("0308","06010308");
        map.put("0309","06010309");
        map.put("0310","06010310");
        map.put("0311","06010311");
        map.put("0312","06010312");
        map.put("0313","06010313");
        map.put("0314","06010314");
        map.put("0315","06010315");
        map.put("0316","06010316");
        map.put("0317","06010317");
        map.put("0318","06010318");
        map.put("0401","06010401");
        map.put("0402","06010402");
        map.put("0403","06010403");
        map.put("0404","06010404");
        map.put("0405","06010405");
        map.put("0406","06010406");
        map.put("0501","06010501");
        map.put("0502","06010502");
        map.put("0503","06010503");
        map.put("0504","06010504");
        map.put("0505","06010505");
        map.put("0506","06010506");

    }
    

    private void setfocused() {
        wendu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                   return;
                }else{
                    try {
                        wendunum = wendu.getText().toString().trim();
                        int num1 = Integer.parseInt(wendunum);
                        if(num1<10||num1>29){
                            wendu.setText("");
                            Toast.makeText(AutocmdActivity.this,"设置错误，请输入10-29",Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        shidumin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        shidunummin = shidumin.getText().toString().trim();
                        num2 = Integer.parseInt(shidunummin);
                        if (num2 < 0 || num2 > 100) {
                            shidumin.setText("");
                            Toast.makeText(AutocmdActivity.this, "设置错误，请输入0-100", Toast.LENGTH_LONG).show();
                        }
                        else if ((!shidunummax.equals(""))&&num2>num3) {
                                shidumin.setText("");
                                Toast.makeText(AutocmdActivity.this, "最小值应小于最大值", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        shidumax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        shidunummax = shidumax.getText().toString().trim();
                        num3 = Integer.parseInt(shidunummax);
                        if (num3 < 0 || num3 > 100) {
                            shidumax.setText("");
                            Toast.makeText(AutocmdActivity.this, "设置错误，请输入0-100", Toast.LENGTH_LONG).show();
                        }
                        else if((!shidunummin.equals(""))&&num3<num2){
                            shidumax.setText("");
                            Toast.makeText(AutocmdActivity.this, "最大值应大于最小值", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        guangzhaomin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        guangzhaonummin = guangzhaomin.getText().toString().trim();
                        num4 = Integer.parseInt(guangzhaonummin);
                        if(num4<0||num4>30000){
                            guangzhaomin.setText("");
                            Toast.makeText(AutocmdActivity.this,"设置错误，请输入0-30000",Toast.LENGTH_LONG).show();
                        }
                        else if((!guangzhaonummax.equals(""))&&num4>num5){
                            guangzhaomin.setText("");
                            Toast.makeText(AutocmdActivity.this, "最小值应小于最大值", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        guangzhaomax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        guangzhaonummax = guangzhaomax.getText().toString().trim();
                        num5 = Integer.parseInt(guangzhaonummax);
                        if(num5<0||num5>30000){
                            guangzhaomax.setText("");
                            Toast.makeText(AutocmdActivity.this,"设置错误，请输入0-30000",Toast.LENGTH_LONG).show();
                        }
                        else if ((!guangzhaonummin.equals(""))&&num5<num4){
                            guangzhaomax.setText("");
                            Toast.makeText(AutocmdActivity.this, "最大值应大于最小值", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        co2min.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        co2nummin = co2min.getText().toString().trim();
                        num6 = Integer.parseInt(co2nummin);
                        if(num6<0||num6>2000){
                            co2min.setText("");
                            Toast.makeText(AutocmdActivity.this,"设置错误，请输入0-2000",Toast.LENGTH_LONG).show();
                        }
                        else if ((!co2nummax.equals(""))&&num6>num7){
                            co2min.setText("");
                            Toast.makeText(AutocmdActivity.this, "最小值应小于最大值", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        co2max.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    return;
                }else{
                    try {
                        co2nummax = co2max.getText().toString().trim();
                        num7 = Integer.parseInt(co2nummax);
                        if(num7<0||num7>2000){
                            co2max.setText("");
                            Toast.makeText(AutocmdActivity.this,"设置错误，请输入0-2000",Toast.LENGTH_LONG).show();
                        }
                        else if ((!co2nummin.equals(""))&&num6>num7){
                            co2max.setText("");
                            Toast.makeText(AutocmdActivity.this, "最小值应小于最大值", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public class OnItemSelectedListener1 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            adapterHouse = new ArrayAdapter<CharSequence>(
//                    AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData[position]
//            );
            try {
                spinnernum1 = position;
            }catch (Exception e){
                e.printStackTrace();
            }

            if(position == 0){
                adapterHouse = new ArrayAdapter<CharSequence>(AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData1);
            }else if (position == 1){
                adapterHouse = new ArrayAdapter<CharSequence>(AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData2);
            }else if (position == 2){
                adapterHouse = new ArrayAdapter<CharSequence>(AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData1);
            }else if (position == 3){
                adapterHouse = new ArrayAdapter<CharSequence>(AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData3);
            }else if (position == 4){
                adapterHouse = new ArrayAdapter<CharSequence>(AutocmdActivity.this,android.R.layout.simple_spinner_item,houseData3);
            }
            adapterHouse .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            house.setAdapter(adapterHouse);
            areanum = AutocmdActivity.this.getResources().getStringArray(R.array.area_labels)[position];
            int position1 = position+1;
            try{
                if(position<10){
                    firstnum = "0"+Integer.toString(position1);
                }else {
                    firstnum = ""+Integer.toString(position1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
//            Toast.makeText(AutocmdActivity.this,firstnum,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int position2 = position+1;
            try {
                if(spinnernum1==0||spinnernum1==2){
                    housenum = houseData1[position];
                    if(position2<10){
                        lastnum = "0"+Integer.toString(position2);
                    }else {
                        lastnum = ""+Integer.toString(position2);
                    }
                }else if(spinnernum1 == 1){
                    housenum = houseData2[position];
                    if(position2<10){
                        lastnum = "0"+Integer.toString(position2);
                    }else {
                        lastnum = ""+Integer.toString(position2);
                    }
                }else if(spinnernum1 == 3||spinnernum1 == 4){
                    housenum = houseData3[position];
                    if(position2<10){
                        lastnum = "0"+Integer.toString(position2);
                    }else {
                        lastnum = ""+Integer.toString(position2);
                    }
//                    Toast.makeText(AutocmdActivity.this,lastnum,Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }


//            Toast.makeText(AutocmdActivity.this,areanum+"-"+housenum,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



    public String readInputStream(InputStream is) throws IOException
    {
        String temp = null;

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int len=0;
        byte []buffer =new byte[1024];
        if((len=is.read(buffer))!=-1)
        {
            baos.write(buffer,0,len);
        }
        is.close();
        baos.close();
        byte[]result=baos.toByteArray();
        temp=new String(result);
        return temp;
    }

    public void  sendMessage_toServer(String str){
        final String command_String =str;
        Thread myThread=new Thread(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try{
                    url = new URL(path);

                    urlConn=(HttpURLConnection) url.openConnection();
                    urlConn.setConnectTimeout(5000);
                    urlConn.setDoOutput(true);
                    urlConn.setDoInput(true);
                    urlConn.setRequestMethod("GET");
                    // TODO Auto-generated method stub
                    OutputStream out =urlConn.getOutputStream();
                    out.write(command_String.getBytes());
                    //count--;
                    out.flush();
                    while(urlConn.getContentLength()!=-1){
                        int code=urlConn.getResponseCode();
                        if(code==200)
                        {
                            Toast.makeText(AutocmdActivity.this, "控制指令已发送",Toast.LENGTH_LONG ).show();
                            urlConn.disconnect();
                            break;
                        }
                    }
                }catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }catch(Exception e2)
                {
                    e2.printStackTrace();
                }

            }
        });
        myThread.start();

    }

    private void delay(int ms){
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class OnClickListenerSet implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            set.setFocusable(true);
            set.setFocusableInTouchMode(true);
            set.requestFocus();
            set.requestFocusFromTouch();
            try{
                value = map.get(firstnum+lastnum);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(!user.equals("admin")) {
                if (!Arrays.asList(strs).contains(firstnum+lastnum)){
                    Toast.makeText(AutocmdActivity.this, "你无权控制", Toast.LENGTH_LONG).show();
                } else {
                    if ((!"".equals(wendunum)) && (!"".equals(shidunummin)) && (!"".equals(guangzhaomin)) && (!"".equals(co2nummin))&&(!"".equals(shidunummax))&&(!"".equals(guangzhaonummax))&&(!"".equals(co2nummax))) {
                        sendMessage_toServer("Settemprange@"+wendunum+"@"+value+"@");
                        delay(750);
                        sendMessage_toServer("Settemprange@"+wendunum+"@"+value+"@");
                        delay(750);
                        sendMessage_toServer("OPEN@"+firstnum+lastnum+"@"+shidunummin+"@"+shidunummax+"@"+guangzhaonummin+"@"+guangzhaonummax+"@"+co2nummin+"@"+co2nummax+"@");
                        delay(750);
                        sendMessage_toServer("OPEN@"+firstnum+lastnum+"@"+shidunummin+"@"+shidunummax+"@"+guangzhaonummin+"@"+guangzhaonummax+"@"+co2nummin+"@"+co2nummax+"@");
                        Toast.makeText(AutocmdActivity.this,"已发送",Toast.LENGTH_SHORT).show();
                    }
                }
            }else {

                if ((!"".equals(wendunum)) && (!"".equals(shidunummin)) && (!"".equals(guangzhaomin)) && (!"".equals(co2nummin))&&(!"".equals(shidunummax))&&(!"".equals(guangzhaonummax))&&(!"".equals(co2nummax))) {
                    sendMessage_toServer("Settemprange@"+wendunum+"@"+value+"@");
                    delay(750);
                    sendMessage_toServer("Settemprange@"+wendunum+"@"+value+"@");
                    delay(750);
                    sendMessage_toServer("OPEN@"+firstnum+lastnum+"@"+shidunummin+"@"+shidunummax+"@"+guangzhaonummin+"@"+guangzhaonummax+"@"+co2nummin+"@"+co2nummax+"@");
                    delay(750);
                    sendMessage_toServer("OPEN@"+firstnum+lastnum+"@"+shidunummin+"@"+shidunummax+"@"+guangzhaonummin+"@"+guangzhaonummax+"@"+co2nummin+"@"+co2nummax+"@");
                    Toast.makeText(AutocmdActivity.this,"已发送",Toast.LENGTH_SHORT).show();
                }

            }

        }

    }

    private class OnClickListenerReset implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            set.setFocusable(true);
            set.setFocusableInTouchMode(true);
            set.requestFocus();
            set.requestFocusFromTouch();
            sendMessage_toServer("closecontrolauto@"+firstnum+lastnum);
            wendu.setText("");
            shidumin.setText("");
            guangzhaomin.setText("");
            co2min.setText("");
            shidumax.setText("");
            guangzhaomax.setText("");
            co2max.setText("");
            
        }
    }
//    public boolean onKeyDown(int keyCode,KeyEvent event)
//    {
//        if(keyCode== KeyEvent.KEYCODE_BACK)
//        {
//            dialog();
//            return true;
//        }
//        else
//        {
//            return super.onKeyDown(keyCode, event);
//        }
//    }
//    protected void dialog()
//    {
//        Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
//                "确认退出应用程序？").setPositiveButton("退出",new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // TODO Auto-generated method stub
//                dialog.dismiss();
//                AutocmdActivity.this.finish();
//            }
//        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // TODO Auto-generated method stub
//                dialog.dismiss();
//            }
//        }).create();
//        dialog.show();
//    }



}
