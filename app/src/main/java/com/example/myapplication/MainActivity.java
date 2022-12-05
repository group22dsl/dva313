package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView databaseListView;
    public static final String TASK_ID = "";
    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myFilter = new IntentFilter("testData");
        registerReceiver(myBroadcastReceiver, myFilter);

        rootDatabaseref = FirebaseDatabase.getInstance("https://dva313-5397d-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("logs");

        databaseListView = (ListView) findViewById(R.id.database_list);

        List<TODOTask> tasks = Database.getDatabase(this).dao().getAllTasks();

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, tasks);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TODOTask task = (TODOTask) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(TASK_ID, task.getId());

                startActivity(intent);
            }
        });
    }

    public void writeNewLog(View view){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();

        rootDatabaseref.child(String.valueOf(sb)).child("origin").setValue("test");
        rootDatabaseref.child(String.valueOf(sb)).child("date").setValue("randomdate");
        rootDatabaseref.child(String.valueOf(sb)).child("message").setValue("something something");
        rootDatabaseref.child(String.valueOf(sb)).child("category").setValue("randomstatus");
    }

    public void onAddClick(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}