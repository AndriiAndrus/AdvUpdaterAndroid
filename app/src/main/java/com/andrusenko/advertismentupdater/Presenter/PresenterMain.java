package com.andrusenko.advertismentupdater.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.andrusenko.advertismentupdater.Model.Adapters.SitesStatsAdapter;
import com.andrusenko.advertismentupdater.Model.Adapters.ViewHolder;
import com.andrusenko.advertismentupdater.Model.db.RxDatabase;
import com.andrusenko.advertismentupdater.Presenter.Support.onFirstLaunch;

import java.util.ArrayList;
import java.util.List;

public class PresenterMain {

    private static ViewHolder currentClickedVH;
    private Context context;
    private RxDatabase dbModel;
    private static List<ViewHolder> elements;

    public PresenterMain(Context context){
        this.context = context;
        dbModel = new RxDatabase(context);
        SharedPreferences prefs = context.getSharedPreferences("com.andrusenko.advertismentupdater", Context.MODE_PRIVATE);
        boolean firstRun = prefs.getBoolean("firstRun", true);
        if (firstRun) {
            onFirstLaunch firstLaunch = new onFirstLaunch(context);
        }
    }

    public boolean configuredWebsite(String domain, String login, String passwd){
        boolean isOkay = true;
        try {
            dbModel.addNew(domain, login, passwd);
            // We will need to check login-pass valid later
        } catch(Exception ex){
            Log.d("dbModel", "Error addNew user/pass:"+ex.getMessage());
            isOkay = false;
        }
        return isOkay;
    }

    //This will return Adapter for List in UI
    public SitesStatsAdapter generateListAdapter(){
        List<ViewHolder> _tempList = this.generateListCollection();
        SitesStatsAdapter _adapter = new SitesStatsAdapter(context, _tempList);
        return _adapter;
    }
    //This will generate collection for Adapter
    private List<ViewHolder> generateListCollection(){
        List<ViewHolder> temp;
        try{
            temp = dbModel.getAllData();
            elements = temp;
        }catch(Exception ex) {
        /* Lets return empty list */
            temp = new ArrayList<>();
        }
        return temp;
    }

    /*
    Getter and setter for ViewHolder, that should be shoun in WebDataFragment
     */
    public static ViewHolder getCurrentClickedVH() {
        return currentClickedVH;
    }

    public static void setCurrentClickedVH(String domain) {
        for (ViewHolder viewHold : elements) {
            if (viewHold.DOMAIN.equals(domain))
                currentClickedVH = viewHold;
        }
    }
}
