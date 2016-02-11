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
                .add("email", "email")
                .add("password", "pass")
                .add("remember", "1")
        .build();
        /* DEMO, just to test. DELETE soon
        */
        try {
            String siteGetAnswer = run("http://domik.ua/nedvizhimost/appleIndex.html");
            Log.d("siteAnswerGet", siteGetAnswer);
            Response sitePostAnswer = post("http://domik.ua/data/auth/", formBody);
            Log.d("siteAnswerPost.body", sitePostAnswer.body().string());
             //siteGetAnswer = run("http://domik.ua/cabinet/");
         //     Log.d("siteAnswerGet", siteGetAnswer);
            String checkValidString = "apple";
          // return siteGetAnswer;
            if(sitePostAnswer.body().string().contains(checkValidString)){
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

    Response post(String url, RequestBody formBody) throws IOException {

        String returnMe;

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

            Response response = client.newCall(request).execute();
           // returnMe = response.body().string();

        return response;
    }
}
