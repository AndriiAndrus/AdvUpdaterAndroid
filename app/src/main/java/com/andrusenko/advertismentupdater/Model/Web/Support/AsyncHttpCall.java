package com.andrusenko.advertismentupdater.Model.Web.Support;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncHttpCall extends AsyncTask<Request, String, String> {

    OkHttpClient client;

    @Override
    protected String doInBackground(Request... params) {
        OkHttpWithCookies clientWithCookies = new OkHttpWithCookies();
        client = clientWithCookies.getOkHttpClient();
        String http = "null";
        try {
            http = run(params[0]);
            Log.d("AsyncHttpCall", "http call result: "+http);
        } catch (IOException e) {
           Log.d("AsyncHttpCall", "error in http call", e);
        }
        return http;
    }

    String run(Request request) throws IOException {
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
