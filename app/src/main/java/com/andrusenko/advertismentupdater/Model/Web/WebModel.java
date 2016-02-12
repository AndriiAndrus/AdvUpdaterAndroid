package com.andrusenko.advertismentupdater.Model.Web;

import android.util.Log;

import com.andrusenko.advertismentupdater.Model.Web.Support.AsyncHttpCall;
import com.andrusenko.advertismentupdater.Model.Web.Support.Interface.IWebModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WebModel implements IWebModel {

    private RequestBody formBody;
    private Request request;
    private String domain;
    private List<String> LoginPass;

    public WebModel(String domain, List<String> LoginPass){
        this.domain = domain;
        this.LoginPass = LoginPass;
     switch(domain){
         case "domik.ua":
             break;
         case "meget.kiev.ua":
             formBody = new FormBody.Builder()
                     .add("user_login", LoginPass.get(0))
                     .add("user_password", LoginPass.get(1))
                     .build();
             request = new Request.Builder()
                     .url("http://meget.kiev.ua/member/login/")
                     .addHeader("Referer", "http://meget.kiev.ua/member/login/")
                     .post(formBody)
                     .build();
             break;
         case "100realty.ua":
             formBody = new FormBody.Builder()
                     .add("user_login", LoginPass.get(0))
                     .add("user_password", LoginPass.get(1))
                     .build();
             request = new Request.Builder()
                     .url("http://100realty.ua/user/login?destination=user/me/objects")
                     .addHeader("Referer", "http://100realty.ua/user/login?destination=user/me/objects")
                     .post(formBody)
                     .build();
             break;
         case "kanzas.ua":
             request = new Request.Builder()
                     .url("http://100realty.ua/user/login?destination=user/me/objects")
                     .addHeader("Referer", "http://100realty.ua/user/login?destination=user/me/objects")
                     .post(formBody)
                     .build();
             break;
         case "mesto.ua":
             break;
         case "kvartorg.com":
             break;
     }
    }

    @Override
    public boolean loginWebsite() {
        Log.d("WebModel", "domain:"+domain+", login="+LoginPass.get(0)+", pass="+LoginPass.get(1));

        AsyncHttpCall httpCall = new AsyncHttpCall();
        httpCall.execute(request);
        String answer = "null";
        try {
            answer = httpCall.get();
        } catch (InterruptedException|ExecutionException e) {
            Log.d("WebModel", "Error login to "+domain+" with LOGIN/PASS "+LoginPass.get(0)+" : "+LoginPass.get(1)+"", e);
        }
        switch(answer){
            case "domik.ua":
                break;
            case "meget.kiev.ua":
                if(answer.contains("member")){
                    return true;
                }
                break;
            case "100realty.ua":
                break;
            case "kanzas.ua":
                break;
            case "mesto.ua":
                break;
            case "kvartorg.com":
                break;
        }
        return false;
    }

    @Override
    public boolean updateAdvert() {
        return false;
    }

    public String getDomain() {
        return domain;
    }
}
