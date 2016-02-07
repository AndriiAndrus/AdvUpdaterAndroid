package com.andrusenko.advertismentupdater.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrusenko.advertismentupdater.Model.ViewHolder;
import com.andrusenko.advertismentupdater.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SitesStatsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<ViewHolder> elements;

    @Bind(R.id.txtLIDomain) TextView txtDomain;
    @Bind(R.id.txtLIDescription) TextView txtDescription;
    @Bind(R.id.imgLIicon) ImageView imgIcon;

    public SitesStatsAdapter(Context context, List<ViewHolder> elements){
        this.elements = elements;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_site, parent, false);
        }
        ButterKnife.bind(this, view);
        ViewHolder hold = (ViewHolder) getItem(position);

        txtDomain.setText(hold.DOMAIN);
        txtDescription.setText(hold.STATS);

        if(hold.CONFIGURED){
            // DEFAULT ICON IS OK! Site is configured.
        } else {
            // CHANGE ICON TO Unconfirmed website!
            // DO LATER!
        }

        return view;
    }
}