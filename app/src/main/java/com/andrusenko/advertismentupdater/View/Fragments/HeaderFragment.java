package com.andrusenko.advertismentupdater.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrusenko.advertismentupdater.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class HeaderFragment extends Fragment {

    public static final String TAG = "HeaderFragmentTag";
    private View view;
    @Bind(R.id.txtHeader) TextView txtHead;
    @Bind(R.id.txtHeaderSecondaryText) TextView txtHeadSecondary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_header, null);
        ButterKnife.bind(this, view);



        return view;
    }
}
