package com.example.fr.baimajidi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int VERSION=1;
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context,name,factory,version);
	}

	public DatabaseHelper(Context context, String name)
	{
		this(context,name,VERSION);
	}
	public DatabaseHelper(Context context, String name, int version)
	{
		this(context,name,null,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("cteate a Database");
		db.execSQL("create table temp(id int,Temp varchar(20),time varchar(20))");
		db.execSQL("create table humi(id int,Humi varchar(20),time varchar(20))");
		db.execSQL("create table lumi(id int,Lumi varchar(20),time varchar(20))");
		db.execSQL("create table co2(id int,Co2 varchar(20),time varchar(20))");
		db.execSQL("create table NH3(id int,NH3 varchar(20),time varchar(20))");
		db.execSQL("create table H2S(id int,H2S varchar(20),time varchar(20))");
		db.execSQL("create table AirRate(id int,AirRate varchar(20),time varchar(20))");
//		db.execSQL("create table co23(id int,Co22 varchar(20),time varchar(20))");
//		db.execSQL("create table temp3(id int,Temp3 varchar(20),time varchar(20))");
//		db.execSQL("create table humi3(id int,Humi3 varchar(20),time varchar(20))");
//		db.execSQL("create table lumi3(id int,Lumi3 varchar(20),time varchar(20))");
//		db.execSQL("create table co23(id int,Co23 varchar(20),time varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a Database");
	}

}
