package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import java.util.Date;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;
    public static List<whitelistEntry> whitelist;

    @Override
    public void onReceive(Context context, Intent intent) {
        collectedData = intent.getStringExtra("data");

        try {
            JSONObject jsonObj = new JSONObject(collectedData);

            SharedPreferences sharedpreferences = context.getSharedPreferences("AppWhiteList", Context.MODE_PRIVATE);
            Set<String> whiteListAppsNew = new HashSet<String>();

            whiteListAppsNew = sharedpreferences.getStringSet("whiteListedApps", null);
            if(whiteListAppsNew == null || whiteListAppsNew.size() == 0 ){
                whitelist = Database.getDatabase(context).whitelistDAO().getEntireWhitelist();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Set<String> whiteListApps = new HashSet<String>();
                whitelist = Database.getDatabase(context).whitelistDAO().getEntireWhitelist();
                for(whitelistEntry list: whitelist){
                    whiteListApps.add(list.getID());
                }
                editor.putStringSet("whiteListedApps", whiteListApps);
                editor.apply();
                whiteListAppsNew = sharedpreferences.getStringSet("whiteListedApps", null);
            }
            if(whiteListAppsNew != null && whiteListAppsNew.size() > 0 ) {
                boolean contains = whiteListAppsNew.contains(jsonObj.getString("ID"));
                if (contains == true) {
                    Toast.makeText(context, "You got new message from whitelisted app", Toast.LENGTH_SHORT).show();

                    Thread threadSender = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sendAnalytics(jsonObj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    threadSender.start();

                } else {
                    Toast.makeText(context, "You got new message from blacklisted app", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Please check whitelisted apps in settings",  Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendAnalytics(JSONObject jsonObj) throws IOException, JSONException {
        Log.v("HTTP_SEND", "Starting send Analytics Func");
        String user = "DVA313";
        String pass = "DVA313 Pass For Cloud 712!";
        String credential = Credentials.basic(user, pass);
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient
                .Builder()
                .authenticator((Route, Response) -> {
                    if (Response.request().header("Authorization") != null) {
                        Log.v("HTTP_SEND", "Failed to Authenticate");
                        return null; // Give up, we've already attempted to authenticate.
                    }
                    return Response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                })
                .build();

        // Currently only used for debugging
        // Delete when fetch from CacheDAO works
//        JSONObject json = new JSONObject();
//        json.put("title","test");
//        json.put("name","plplpl22");

        Date date = new Date();
        Number currentDateTime = date.getTime();
        String fileName = jsonObj.getString("ID");
        String objectFileName = currentDateTime.toString() +"_"+fileName;

        RequestBody body = RequestBody.create(jsonObj.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://nextcloud.thepotatoservices.com/" +       // URL
                        "remote.php/dav/files/" +                       // WebDAV interface
                        "DVA313" +                                     // User
                        "/DVA313%20-%20Software%20Engineering%202/" +    // Target
                        "Project%20Files/Recv%20Folder/" +objectFileName+
                        ".json"                                     // Filename
                )
                .put(body)
                .build();

        Log.v("HTTP_SEND", "Sending to Cloud");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("HTTP_SEND", "HTTP request failed, Error: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.v("HTTP_SEND", "HTTPS POST sent, code: " + response.code());
            }
        });
    }
}
