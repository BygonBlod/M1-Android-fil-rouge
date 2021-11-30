package com.example.master12021hunaultfil_rouge.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.master12021hunaultfil_rouge.TAskPrioriteItem;
import com.example.master12021hunaultfil_rouge.TasksAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TaskSQLHelper extends SQLiteOpenHelper {
    public static int db_version=2;
    public static String db_name="TaskTable.db";

    public TaskSQLHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(TaskContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int i, int i1) {
        onUpgrade(db,i1,i);
    }


    public void InsertTask(String prio,String tache){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(TaskContract.TaskEntry.COLUMN_PRIORITE,prio);
        value.put(TaskContract.TaskEntry.COLUMN_TASK,tache);
        db.insert(TaskContract.TaskEntry.TABLE_NAME,null,value);
    }

    public void DeleteTask(String prio,String tache){
        SQLiteDatabase db=this.getWritableDatabase();
        String selection= TaskContract.TaskEntry.COLUMN_TASK+" = ? and "+ TaskContract.TaskEntry.COLUMN_PRIORITE+" = ?";
        String[] selectionArgs={
                tache,
                prio
        };
        db.delete(TaskContract.TaskEntry.TABLE_NAME,selection,selectionArgs);
    }

    public Cursor getCursor(){
        ArrayList<TAskPrioriteItem> res=new ArrayList<TAskPrioriteItem>();
        String[] projection={
                TaskContract.TaskEntry.COLUMN_PRIORITE,
                TaskContract.TaskEntry.COLUMN_TASK
        };
        Cursor curseur=this.getReadableDatabase().query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return curseur;
    }
}
