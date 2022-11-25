package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class IncomingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //This method is called when the BroadcastReceiver is receiving
        String CUSTOM_INTENT = "jason.wei.custom.intent.action.TEST";
        String dataInfo = intent.getStringExtra("data");
        if (intent.getAction().equals(CUSTOM_INTENT)){
            Log.i("custom intent" , CUSTOM_INTENT);
            Toast.makeText(context, dataInfo, Toast.LENGTH_LONG).show();
        } else {
            throw new UnsupportedOperationException("Intent not yet implemented");
        }
    }
}