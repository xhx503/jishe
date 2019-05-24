package com.example.fr.baimajidi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class PrivilegeHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    private Context mContext;

    public PrivilegeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table privilege_table(id integer primary key autoincrement,user varchar(40),privilege text)";
        db.execSQL(sql);
//        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
