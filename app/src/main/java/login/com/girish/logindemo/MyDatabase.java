package login.com.girish.logindemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;

    /////////////////////////////////////////////////////
    /*
       create table my_users (user_name primary key text, usr_password text);
     */
    public static final String TABLE_NAME = "my_users";
    public static final String COL_USR_NAME = "user_name";
    public static final String COL_USR_PASS = "user_password";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" " +
            "("+COL_USR_NAME+"  text primary key," +
            " "+COL_USR_PASS+" text)";


    /////////////////////////////////////////////////////


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i>i1){
            //do alter.....

        }
    }
}
