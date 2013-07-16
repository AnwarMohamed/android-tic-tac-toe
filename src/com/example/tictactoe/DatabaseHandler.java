package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db1e3";
    private static final String TABLE_NAME = "states";
 
    private static final String KEY_X_MARKER = "xmarker";
    private static final String KEY_O_MARKER = "omarker";
    private static final String KEY_BACK = "back";
    private static final String KEY_BORDER = "border";
    private static final String KEY_STATES = "records";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME + " ("
                + KEY_X_MARKER + " TEXT ," + KEY_O_MARKER + " TEXT,"
                + KEY_BACK + " TEXT ," + KEY_BORDER + " TEXT, " + KEY_STATES + " VARCHAR(255))";
        db.execSQL(CREATE_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
 
    void addValues(MyApp v) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_X_MARKER, OptionsActivity.CodetoColor(v.getxMarker()));
        values.put(KEY_O_MARKER, OptionsActivity.CodetoColor(v.getoMarker()));
        values.put(KEY_BACK, OptionsActivity.CodetoColor(v.getBack()));
        values.put(KEY_BORDER, OptionsActivity.CodetoColor(v.getBorder()));
        values.put(KEY_STATES, v.getStates());
        
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
      
    public List<MyApp> getAllValues() {
        List<MyApp> vList = new ArrayList<MyApp>();
 
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        if (cursor.moveToFirst()) {
            do {
                MyApp v = new MyApp();
                v.setxMarker(OptionsActivity.ColortoCode(cursor.getString(0), false));
                v.setoMarker(OptionsActivity.ColortoCode(cursor.getString(1), false));
                v.setBack(OptionsActivity.ColortoCode(cursor.getString(2), false));
                v.setBorder(OptionsActivity.ColortoCode(cursor.getString(3), false));
                v.setStates(cursor.getString(4));

                vList.add(v);
            } while (cursor.moveToNext());
        }
 
        return vList;
    }
 
    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
 
    public int getCount() {
    	
    	Cursor cursor = null;
    	int mcount;
    	try
    	{		
	        String countQuery = "SELECT * FROM " + TABLE_NAME;
	        SQLiteDatabase db = this.getReadableDatabase();
	        cursor = db.rawQuery(countQuery, null);
	        mcount  = cursor.getCount();
    	}
    	finally{
    	    if(cursor != null && !cursor.isClosed()){
    	    	cursor.close();
    	    }   
    	}
 
        return mcount;
    }
    
    public void check()
    {
    	String Query = "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_X_MARKER + " TEXT ," + KEY_O_MARKER + " TEXT,"
                + KEY_BACK + " TEXT ," + KEY_BORDER + " TEXT, " + KEY_STATES + " VARCHAR(255))";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(Query);
    }
 
}
