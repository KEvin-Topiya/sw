package com.example.sweet_wave.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firestore.v1.Value;

import java.util.ArrayList;
import java.util.HashMap;


public class sqlLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="SW_DB";
    private static final String TABLE_CART="CART_TBL";
    private static final String KEY_ID="ID";
    private static final String KEY_PID="PID";
    private static final String KEY_CAT="CATEGORY";
    private static final String KEY_NAME="NAME";
    private static final String KEY_IMG="IMG";

    private static final String KEY_QTY="QTY";
    private static final String KEY_PRICE="PRICE";

    public sqlLiteHelper(Context context)
    {
        super(context, DB_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CART+
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_PID + " INTEGER, "+ KEY_NAME + " TEXT, "+KEY_CAT + " Text, " + KEY_IMG +" TEXT, " +KEY_QTY + " INTEGER, "  +KEY_PRICE + " INTEGER " +")");
    }



    public void addtoCart(int id,String name,String img,String cat,int qty,int price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_PID,id);
        values.put(KEY_NAME,name);
        values.put(KEY_CAT,cat);
        values.put(KEY_QTY,qty);
        values.put(KEY_IMG,img);
        values.put(KEY_PRICE,price);
        db.insert(TABLE_CART,null, values);

        db.close();
    }

    public ArrayList<cart> selectAll(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+ TABLE_CART,null);

        ArrayList<cart> arr=new ArrayList<>();
            while (cs.moveToNext()){
                cart data=new cart();
                int x=0;
                data.Id=cs.getInt(x++);
                data.Pid=cs.getInt(x++);
                data.Name=cs.getString(x++);
                data.Category=cs.getString(x++);
                data.Img=cs.getString(x++);
                data.Qty=cs.getInt(x++);
                data.Price=cs.getInt(x++);
                arr.add(data);
            };
        cs.close();
        return arr;
    }
    public HashMap<String, String> selectPid(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT *  FROM "+ TABLE_CART,null);

        String arr="";
        int total=0;
        while (cs.moveToNext()){
            arr+= cs.getString(2)+":"+cs.getInt(5)+",";
            total+=cs.getInt(6);
        };
        cs.close();
        HashMap<String,String> d=new HashMap<>();
        d.put("ord",arr);
        d.put("total", String.valueOf(total));
        return d;
    }

    public boolean deleteRow(int id){
        SQLiteDatabase db=this.getWritableDatabase();

        return  db.delete(TABLE_CART,KEY_ID+"="+id,null )>0;

    }

    public void trunc(String table){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

}
