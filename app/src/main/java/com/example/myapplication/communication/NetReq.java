package com.example.myapplication.communication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.database.Database;
import com.example.myapplication.database.cache.CacheEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetReq {
    private static OkHttpClient client;                                         // HTTP Client
    private static final String user = "DVA313";                                // Cloud User
    private static final String pass = "DVA313 Pass For Cloud 712!";            // Cloud Pass
    private static final String credential = Credentials.basic(user, pass);     // Credentials Object

    public static void initialize(){
        client = new OkHttpClient
                .Builder()
                .authenticator((Route, Response) -> {
                    if (Response.request().header("Authorization") != null) {
                        Log.v("HTTPS", "Failed to Authenticate");
                        return null; // Give up, we've already attempted to authenticate.
                    }
                    return Response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public static int sendAnalyticsToCloud() throws IOException, JSONException, InterruptedException {
        JSONArray toSend = new JSONArray();   // Json to send
        final int[] return_code = new int[1];   // Return code from sender, so it can be returned
        String date = LocalDateTime.now().toString().substring(0, 19).replace(':', '_');
        List<CacheEntry> cache = Database.INSTANCE.cacheDAO().getEntireCache();
        if(cache.size() == 0) return -200;
        for(int i = 0; i < cache.size(); i++){
            toSend.put(
                    new JSONObject()
                            .put("ID", cache.get(i).getID())
                            .put("Priority", cache.get(i).getPRIORITY())
                            .put("Tags", cache.get(i).getTAGS())
                            .put("Data", cache.get(i).getDATA())
            );
        }

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(toSend.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://nextcloud.thepotatoservices.com/" +           // URL
                        "remote.php/dav/files/" +                           // WebDAV interface
                        "DVA313" +                                          // User
                        "/DVA313%20-%20Software%20Engineering%202/" +       // Target
                        "Project%20Files/Recv%20Folder/" +
                        date +                                              // Filename
                        ".json"                                             // File Format
                )
                .put(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("HTTPS", "request failed, Error: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.v("HTTPS", "PUT request sent, return code: " + response.code());
                return_code[0] = response.code();
            }
        });
        return return_code[0];
    }
    public static void getConfFromCloud() throws IOException {
        Request request;
        request = new Request.Builder()
                .url("https://nextcloud.thepotatoservices.com/" +
                        "s/mGaCFbGynG7cLq7/" +
                        "download/test.json"
                )
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("HTTPS",
                        "request failed, Error: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonStr = response.body().string();
                try {
                    JSONObject data = new JSONObject(jsonStr);
                    Log.d("HTTPS",
                            "Config received, contents: " + data);
                }
                catch (JSONException e) {
                    Log.d("HTTPS",
                            "request failed, code:" + response.code() + "Error: " + e);
                }
            }
        });
    }
}
