package com.andrusenko.advertismentupdater.Presenter.Support;

import android.content.Context;
import android.content.SharedPreferences;

import com.andrusenko.advertismentupdater.Model.db.RxDatabase;

public class onFirstLaunch {

    public onFirstLaunch(Context cntx){
        /*
        Here we need to add some default values to DB.
        Then we will write a key to preferences, to show that we already set up this.
         */
        RxDatabase db = new RxDatabase(cntx);

        db.addNew("domik.ua");
        db.addNew("meget.kiev.ua");
        db.addNew("100realty.ua");
        db.addNew("kanzas.ua");
        db.addNew("mesto.ua");
        db.addNew("kvartorg.com");

        SharedPreferences.Editor editor = cntx.getSharedPreferences("com.andrusenko.advertismentupdater", Context.MODE_PRIVATE).edit();
        editor.putBoolean("firstRun", false);
        editor.apply();
    }

}
