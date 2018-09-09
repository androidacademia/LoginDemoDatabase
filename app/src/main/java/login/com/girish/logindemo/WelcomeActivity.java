package login.com.girish.logindemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> userNameList;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        myDatabase = new MyDatabase(this);
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();

        listView = findViewById(R.id.listView);
        userNameList = new ArrayList<>();

        //this should perform SignIn...
        String query = "select * from "+MyDatabase.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while (cursor.moveToNext()){
            String username = cursor.getString(0);
            userNameList.add(username);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ArrayAdapter(this,R.layout.list_item,userNameList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        //User onItemLongClickListener
        listView.setOnItemLongClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String name = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        return false;
    }
}
