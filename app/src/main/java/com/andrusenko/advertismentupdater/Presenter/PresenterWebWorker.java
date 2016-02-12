package com.andrusenko.advertismentupdater.Presenter;

import android.util.Log;

import com.andrusenko.advertismentupdater.Model.Adapters.ViewHolder;
import com.andrusenko.advertismentupdater.Model.Web.WebModel;
import com.andrusenko.advertismentupdater.Model.db.RxDatabase;

import java.util.ArrayList;
import java.util.List;

public class PresenterWebWorker {

    List<WebModel> webModels = new ArrayList<>();
    private RxDatabase dbModel;

    public PresenterWebWorker(RxDatabase dbModel){
        this.dbModel = dbModel;
        setWebModels();
    }

    public boolean checkValid(String domain){
        boolean isValid = false;
        for (WebModel web : webModels) {
            if(web.getDomain().equals(domain)){
                isValid = web.loginWebsite();
            }
        }
        return isValid;
    }

    public void updateAll(){
        for (WebModel web : webModels) {
            web.updateAdvert();
        }
    }

    private void setWebModels(){
        for (ViewHolder vh : dbModel.getAllData()) {
            String domain = vh.DOMAIN;
            List<String> list = dbModel.getUserPassByDomain(domain);
            WebModel webModel = new WebModel(domain, list);
            webModels.add(webModel);
            Log.d("PresenterWeb", "Created WebModel for DOMAIN:"+domain);
        }
    }
}
