package com.example.myapplication.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication.database.Database;
import com.example.myapplication.database.cache.CacheEntry;

import org.json.JSONException;
import org.json.JSONObject;

public class LocalRecv extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try
        {
            if(Database.whitelist == null) {
                Database.whitelist = Database.INSTANCE.whitelistDAO().getEntireWhitelist();
            }
            if(Database.whitelist.size() < 1) {
                Toast.makeText(
                        context, "Whitelist is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean invalid = true;
            JSONObject jsonObj = new JSONObject(intent.getStringExtra("data")); // Add recieved string to Json object
            for(int i = 0; i < Database.whitelist.size(); i++){
                if(Database.whitelist.get(i).getID().equals(jsonObj.getString("ID"))){
                    invalid = false;
                    break;
                }
            }
            if(invalid){
                Toast.makeText(context,
                        "Discarded Message from Blacklisted App", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(context,
                    "Message Received from Whitelisted App", Toast.LENGTH_SHORT).show();

            Database.INSTANCE.cacheDAO().addCacheEntry(
                    new CacheEntry(
                            jsonObj.get("ID").toString(),
                            jsonObj.get("Priority").toString(),
                            jsonObj.get("Tags").toString(),
                            jsonObj.get("Data").toString()
                    )
            );

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
