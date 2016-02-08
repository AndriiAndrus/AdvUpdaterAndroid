package com.andrusenko.advertismentupdater.Model.Adapters;

public class ViewHolder {
    public String DOMAIN;
    public boolean CONFIGURED;
    public String STATS;

    public ViewHolder(String DOMAIN, String STATS, boolean CONFIGURED){
        this.DOMAIN = DOMAIN;
        this.CONFIGURED = CONFIGURED;
        this.STATS = STATS;
    }
}
