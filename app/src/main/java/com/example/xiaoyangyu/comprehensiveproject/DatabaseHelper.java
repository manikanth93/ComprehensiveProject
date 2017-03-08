package com.example.xiaoyangyu.comprehensiveproject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DBNAME= "Database.db";
    public static final String DBLOCATION="/data/data/com.example.xiaoyangyu.comprehensiveproject/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public DatabaseHelper(Context context)
    {
        super(context,DBNAME,null,1);
        this.mContext=context;
    }
    public void onCreate(SQLiteDatabase db)
    {
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public void openDatabase()
    {
        String path=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null&&mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase()
    {
        if(mDatabase!=null){
            mDatabase.close();
        }
    }

}
