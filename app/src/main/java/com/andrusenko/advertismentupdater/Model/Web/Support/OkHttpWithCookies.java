package com.andrusenko.advertismentupdater.Model.Web.Support;

import android.util.Log;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class OkHttpWithCookies {

    protected OkHttpClient client;
    protected static final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

    public OkHttpClient getOkHttpClient(){
        /*
        We will need cookie to update adv, took it from here:
        http://stackoverflow.com/questions/24263921/how-to-implement-cookie-handling-on-android-using-okhttp
         */
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        Log.d("COOKIE_LOAD", "cookie was loaded for url: "+url.toString());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        return client;
    }
}
