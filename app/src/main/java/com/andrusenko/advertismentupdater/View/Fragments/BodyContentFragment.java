package com.andrusenko.advertismentupdater.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andrusenko.advertismentupdater.Model.Adapters.SitesStatsAdapter;
import com.andrusenko.advertismentupdater.Model.Adapters.ViewHolder;
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
        SitesStatsAdapter _adapter = presenter.generateListAdapter();
        myList.setAdapter(_adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    main.setListItemClick((ViewHolder) _adapter.getItem(position));
                } catch(Exception ex){
                    Toast.makeText(getContext(), "Ошибка в работе программы...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setMain(MainActivity _main){
        this.main = _main;
    }
}
