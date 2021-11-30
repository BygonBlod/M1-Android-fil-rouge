package com.example.master12021hunaultfil_rouge.BD;

import android.provider.BaseColumns;

public class TaskContract {
    private TaskContract(){ }

    public static String SQL_CREATE_ENTRIES="create table "+TaskEntry.TABLE_NAME+"("+TaskEntry.COLUMN_PRIORITE+" TEXT NOT NULL," +TaskEntry.COLUMN_TASK+" TEXT NOT NULL );";
    public static String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+TaskEntry.TABLE_NAME;

    public static class TaskEntry implements BaseColumns{
        public static String TABLE_NAME="task";
        public static String COLUMN_PRIORITE="priorite";
        public static String COLUMN_TASK="task";
    }
}
