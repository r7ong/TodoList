package com.rtong.listit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "items.db";
    public static final String TABLE_ITEMS = "items";
    public static final String COLUM_ID = "_id";
    public static final String COLUM_CONTENT = "content";
    public static final String COLUM_PRIORITY = "priority";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" +
                COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUM_CONTENT + " TEXT, " +
                COLUM_PRIORITY + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_ITEMS;
        db.execSQL(query);

    }

    public void addItem(Item item){
        ContentValues values = new ContentValues();
        values.put(COLUM_CONTENT, item.getContent());
        values.put(COLUM_PRIORITY, item.getPriority());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public ArrayList<Item> readItems(){
        ArrayList<Item> items = new ArrayList<Item>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS +" ;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while( !c.isAfterLast()){
            if(c.getString(c.getColumnIndex("content")) != null){
                String content = c.getString(c.getColumnIndex("content"));
                String priority = c.getString(c.getColumnIndex("priority"));
                items.add(new Item(content, priority));

            }
            c.moveToNext();
        }
        db.close();
        return items;
    }

    public void writeItems(ArrayList<Item> items){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ITEMS;
        db.execSQL(query);

        for(Item item : items) {
            ContentValues values = new ContentValues();
            values.put(COLUM_CONTENT, item.getContent());
            values.put(COLUM_PRIORITY, item.getPriority());
            db.insert(TABLE_ITEMS, null, values);
        }
        db.close();
    }
}
