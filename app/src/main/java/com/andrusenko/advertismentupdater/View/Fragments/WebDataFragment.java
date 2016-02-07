package com.andrusenko.advertismentupdater.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.andrusenko.advertismentupdater.R;
import com.andrusenko.advertismentupdater.StaticDataContainer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebDataFragment extends Fragment {

    public static final String TAG = "WebDataFragmentTag";
    private View view;

    @Bind(R.id.txtDataDomain) TextView txtDomain;
    @Bind(R.id.txtDataStatus) TextView txtStatus;
    @Bind(R.id.editTextEmail) public EditText editEmail;
    @Bind(R.id.editTextPassword) public EditText editPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_website_data, null);
        ButterKnife.bind(this, view);
        txtDomain.setText(StaticDataContainer.CurrentViewHolder.DOMAIN);
        String status;
        if(StaticDataContainer.CurrentViewHolder.CONFIGURED) {
            status = "Работает | "+StaticDataContainer.CurrentViewHolder.STATS;
        } else {
            status = "Введите учетные данные для начала работы.";
        }
        txtStatus.setText(status);
        return view;
    }
}