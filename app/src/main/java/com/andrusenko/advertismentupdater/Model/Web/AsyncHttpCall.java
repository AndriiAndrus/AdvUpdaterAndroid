package com.andrusenko.advertismentupdater.Model.Web;

import android.os.AsyncTask;
import android.util.Log;

import com.andrusenko.advertismentupdater.Model.Web.Support.OkHttpWithCookies;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncHttpCall extends AsyncTask<String, String, String> {

    OkHttpClient client;

    @Override
    protected String doInBackground(String... params) {
        OkHttpWithCookies clientWithCookies = new OkHttpWithCookies();
        client = clientWithCookies.getOkHttpClient();

        // This will be personal for each site
        RequestBody formBody = new FormBody.Builder()
                .add("authentification", "1")
                .add("email", "Paveldom14@gmail.com")
                .add("password", "14dom")
                .add("remember", "1")
        .build();

        /* DEMO, just to test. DELETE soon
        */
        try {
            String siteGetAnswer = run("http://domik.ua/cabinet/");
            Log.d("siteAnswerGet", siteGetAnswer);
            String sitePostAnswer = post("http://domik.ua/data/auth/", formBody);
            Log.d("siteAnswerPost", sitePostAnswer);
            siteGetAnswer = run("http://domik.ua/cabinet/");
            Log.d("siteAnswerGet", siteGetAnswer);
            String checkValidString = "a href=\"/data/exit/\" title=";
          // return siteGetAnswer;
            if(sitePostAnswer.contains(checkValidString)){
                return "domik.ua/cabinet contain data/exit/!";
           }
        } catch (IOException e) {
            return e.getMessage();
        }
        /* TODO delete soon (demo)!
         */

        return "bad";
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String post(String url, RequestBody formBody) throws IOException {

        String returnMe;

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

            Response response = client.newCall(request).execute();
            returnMe = response.body().string();

        return returnMe;
    }
}