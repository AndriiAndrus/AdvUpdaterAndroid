package com.andrusenko.advertismentupdater.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andrusenko.advertismentupdater.Model.Adapters.SitesStatsAdapter;
import com.andrusenko.advertismentupdater.Presenter.PresenterMain;
import com.andrusenko.advertismentupdater.R;
import com.andrusenko.advertismentupdater.View.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BodyContentFragment extends Fragment {

    public static final String TAG = "BodyContentFragmentTag";
    @Bind(R.id.listView) ListView myList;
    private View view;
    private MainActivity main;
    private PresenterMain presenter;
    public SitesStatsAdapter _adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_body_content, null);
        ButterKnife.bind(this, view);
        presenter = new PresenterMain(getContext());
        setListView();
        return view;
    }

    private void setListView(){
       _adapter = presenter.generateListAdapter();
        myList.setAdapter(_adapter);
        Log.d("MainActivity", "called setListView() in BodyContentFragment, result: "+_adapter.isEmpty());
    }

    public void setMain(MainActivity _main){
        this.main = _main;
    }
}
