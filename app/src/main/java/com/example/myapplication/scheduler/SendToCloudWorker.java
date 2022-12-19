package com.example.myapplication.scheduler;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.communication.NetReq;

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
        try {
            if(200 >= NetReq.sendAnalyticsToCloud()
                    && NetReq.sendAnalyticsToCloud() < 300 )
                return Result.success();
        }
        catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        return Result.failure();
    }
}

