package com.example.myapplication.scheduler;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.communication.NetReq;
import com.example.myapplication.database.Database;

import org.json.JSONException;

import java.io.IOException;

public class SendToCloudWorker extends Worker {
    public SendToCloudWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams)
    {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int code = -1;
        try {
            code = NetReq.sendAnalyticsToCloud();

        }
        catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        if(code == -200) return Result.success();
        if(200 >= code && code < 300 ){
            Database.INSTANCE.cacheDAO().resetCache();
            return Result.success();
        }
        return Result.failure();
    }
}

