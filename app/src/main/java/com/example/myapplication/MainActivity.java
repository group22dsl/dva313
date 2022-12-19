package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.communication.LocalRecv;
import com.example.myapplication.communication.NetReq;
import com.example.myapplication.database.Database;
import com.example.myapplication.database.settings.SettingsTableViewActivity;
import com.example.myapplication.database.whitelist.WhitelistTableViewActivity;
import com.example.myapplication.scheduler.SendToCloudWorker;

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
}