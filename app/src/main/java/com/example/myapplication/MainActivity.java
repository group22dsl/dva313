package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.communication.LocalRecv;
import com.example.myapplication.communication.NetReq;
import com.example.myapplication.database.Database;
import com.example.myapplication.database.settings.SettingsTableViewActivity;
import com.example.myapplication.database.whitelist.WhitelistTableViewActivity;
import com.example.myapplication.scheduler.SendToCloudWorker;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Database.getDatabase(this);
        NetReq.initialize();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.pruneWork();
        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(
                        SendToCloudWorker.class,
                        15, TimeUnit.MINUTES).build();
        workManager.enqueueUniquePeriodicWork(
                "send_to_cloud",
                ExistingPeriodicWorkPolicy.KEEP,
                request
        );


        registerReceiver(
                new LocalRecv(),
                new IntentFilter("testData")
        );

        //  TODO CREATE THREAD, TEST TIMER 5SECONDS DONE !
        //  TODO PARSE JSON FILE INTO DBOBJECT
        //  TODO CHANGE SETTING WITH FUNCTION

        Thread threadReceiver = new Thread(() -> {
            try
            {
                NetReq.getConfFromCloud();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        threadReceiver.start();
    }

    public void onSelectWhitelist(View view){
        Intent intent = new Intent(
                MainActivity.this,
                WhitelistTableViewActivity.class
        );
        startActivity(intent);
    }

    public void onSelectSettings(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                SettingsTableViewActivity.class
        );
        startActivity(intent);
    }

    public void onSendToCloud(View view){
        int code = -1;
        try {
            code = NetReq.sendAnalyticsToCloud();

        }
        catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        if(code == -200){
            Toast.makeText(this, "Cache was Empty, nothing uploaded to cloud storage", Toast.LENGTH_SHORT).show();
        }
        if(200 >= code && code < 300 ){
            Database.INSTANCE.cacheDAO().resetCache();
            Toast.makeText(this, "Cache entries successfully sent to cloud storage", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Failed to send cache entries to cloud storage", Toast.LENGTH_SHORT).show();
        }
    }
}