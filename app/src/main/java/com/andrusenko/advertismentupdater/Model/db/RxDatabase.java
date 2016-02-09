package com.andrusenko.advertismentupdater.Model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andrusenko.advertismentupdater.Model.Adapters.ViewHolder;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class RxDatabase {

    private SqlBrite sqlBrite;
    private BriteDatabase db;
    private DbOpenHelper openHelper;

    public RxDatabase(Context context){
        sqlBrite = SqlBrite.create();
        openHelper = new DbOpenHelper(context);
        db = sqlBrite.wrapDatabaseHelper(openHelper);
    }

    public List<ViewHolder> getAllData(){
        List<ViewHolder> tempList = new ArrayList<ViewHolder>();
        Observable<SqlBrite.Query> users = db.createQuery(DbOpenHelper.TBL_NAME, "SELECT * FROM "+DbOpenHelper.TBL_NAME);
        users.subscribe(new Action1<SqlBrite.Query>() {
            @Override public void call(SqlBrite.Query query) {
                Cursor cursor = query.run();
                // TODO parse data...
                if(cursor.moveToFirst()) {
                    do {
                        String domain = cursor.getString(1);
                        int stats = cursor.getInt(3);
                        String active = cursor.getString(2);
                        boolean temp = false;
                        if (active.equals("true"))
                            temp = true;
                        ViewHolder holder = new ViewHolder(domain, "Всего: " + stats, temp);
                        tempList.add(holder);
                    }
                    while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        return tempList;
    }

    public void addNew(String domain){
        ContentValues values = new ContentValues();
       // values.put(DbOpenHelper.COL_ID, 0);
        values.put(DbOpenHelper.COL_DOMAIN, domain);
        values.put(DbOpenHelper.COL_ACTIVE, "false");
        values.put(DbOpenHelper.COL_STATS, 0);
        values.put(DbOpenHelper.COL_LOGIN, "");
        values.put(DbOpenHelper.COL_PASSWORD, "");
        db.insert(DbOpenHelper.TBL_NAME, values);
    }

    public void updateStats(String domain, int stats){
        ContentValues values = new ContentValues();
       // values.put(DbOpenHelper.COL_DOMAIN, domain);
        values.put(DbOpenHelper.COL_STATS, stats);

        String selection = DbOpenHelper.COL_DOMAIN+" = ?";
        String[] selectionArgs = { "" + domain };

        db.update(DbOpenHelper.TBL_NAME, values, selection, selectionArgs);
    }

    public void addNew(String domain, String login, String passwd){
        ContentValues values = new ContentValues();
       // values.put(DbOpenHelper.COL_ID, 0);
       // values.put(DbOpenHelper.COL_DOMAIN, domain);
        values.put(DbOpenHelper.COL_ACTIVE, "true");
       // values.put(DbOpenHelper.COL_STATS, 0);
        values.put(DbOpenHelper.COL_LOGIN, login);
        values.put(DbOpenHelper.COL_PASSWORD, passwd);

        String selection = DbOpenHelper.COL_DOMAIN+" = ?";
        String[] selectionArgs = { "" + domain };

        db.update(DbOpenHelper.TBL_NAME, values, selection, selectionArgs);
      //  db.insert(DbOpenHelper.TBL_NAME, values);
    }
}
