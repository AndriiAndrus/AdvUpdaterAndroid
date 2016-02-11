package com.andrusenko.advertismentupdater.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.andrusenko.advertismentupdater.Presenter.PresenterMain;
import com.andrusenko.advertismentupdater.R;

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
        txtDomain.setText(PresenterMain.getCurrentClickedVH().DOMAIN);
        String status;
        if (PresenterMain.getCurrentClickedVH().CONFIGURED) {
            status = "Успешно настроен | " + PresenterMain.getCurrentClickedVH().STATS;
        } else {
            status = "Введите учетные данные для начала работы.";
        }
        txtStatus.setText(status);
        return view;
    }
}