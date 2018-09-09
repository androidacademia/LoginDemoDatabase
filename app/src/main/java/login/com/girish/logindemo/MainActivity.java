package login.com.girish.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        myDatabase = new MyDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void signIn(View view) {
        String u = editTextUsername.getText().toString();
        String p = editTextPassword.getText().toString();

        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        //this should perform SignIn...
        String query = "select * from "+MyDatabase.TABLE_NAME+" where " +
                ""+MyDatabase.COL_USR_NAME+" ='"+u+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToNext()){
            //success....
            query = "select * from "+MyDatabase.TABLE_NAME+" where " +
                    ""+MyDatabase.COL_USR_NAME+" ='"+u+"' " +
                    "and "+MyDatabase.COL_USR_PASS+"='"+p+"'";
            cursor = sqLiteDatabase.rawQuery(query,null);
            if (cursor.moveToNext()) {
                Snackbar.make(view, "Login Success", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(this,WelcomeActivity.class);
                startActivity(intent);
            }else {
                //login fail..
                sqLiteDatabase = myDatabase.getWritableDatabase();

                Snackbar.make(view, "Invalid Login",Snackbar.LENGTH_LONG).show();
            }
        }else {
            //Actually performing SignUP
            //create an object of ContentValues
            sqLiteDatabase = myDatabase.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(MyDatabase.COL_USR_NAME, u);
            cv.put(MyDatabase.COL_USR_PASS, p);
            sqLiteDatabase.insert(MyDatabase.TABLE_NAME, null, cv);
            //sqLiteDatabase.up
            Snackbar.make(view, "User Inserted",Snackbar.LENGTH_LONG).show();
            ///////////////////////////////It's done///////////////////////////////
        }





    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
