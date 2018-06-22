package com.starklabs.studentdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by vjstark on 19/06/18.
 */

public class MeraDBHandler extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    MeraDBHandler(Context context){
        super(context, "studentdb",null,1);
        this.context = context;
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table student(rno integer primary key,"+"name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addStudent(int rno, String name){
        ContentValues cv = new ContentValues();
        cv.put("rno",rno);
        cv.put("name",name);
        long rid = db.insert("student",null,cv);
        if(rid < 0){
            Toast.makeText(context,"issue",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"inserted",Toast.LENGTH_SHORT).show();
        }
    }

    public String viewStudent(){
        Cursor cursor = db.query("student",null,null,null,null,null,null);
        StringBuffer sb = new StringBuffer();
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do{
                sb.append("Rno: "+cursor.getString(0) +" Name: " +cursor.getString(1)+"\n");
            }while(cursor.moveToNext());
        }
        return  sb.toString();
    }

    public void updateStudent( int rno, String name){
        ContentValues cv = new ContentValues();
        cv.put("rno",rno);
        cv.put("name",name);
        long nor = db.update("student",cv,"rno = "+rno,null);
        Toast.makeText(context, nor + " records updated ", Toast.LENGTH_SHORT).show();
    }

    public void deleteStudent(int rno){
        long nor = db.delete("student","rno = "+rno,null);
        Toast.makeText(context, nor + "records deleted",Toast.LENGTH_SHORT).show();
    }
}
