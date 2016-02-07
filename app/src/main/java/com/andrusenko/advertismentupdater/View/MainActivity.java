package com.andrusenko.advertismentupdater.View;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andrusenko.advertismentupdater.Model.ViewHolder;
import com.andrusenko.advertismentupdater.R;
import com.andrusenko.advertismentupdater.StaticDataContainer;
import com.andrusenko.advertismentupdater.View.Fragments.BodyContentFragment;
import com.andrusenko.advertismentupdater.View.Fragments.BodyFragment;
import com.andrusenko.advertismentupdater.View.Fragments.HeaderFragment;
import com.andrusenko.advertismentupdater.View.Fragments.WebDataFragment;

import rx.functions.Action1;

public class MainActivity extends FragmentActivity {

    private HeaderFragment headerFragment;
    private BodyFragment bodyFragment;
    private BodyContentFragment bodyContentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private WebDataFragment webDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        webDataFragment = new WebDataFragment();
        bodyFragment = new BodyFragment();
        headerFragment = new HeaderFragment();
        bodyContentFragment = new BodyContentFragment();
        bodyContentFragment.setMain(this);

        setDefaultFragmentState();
    }

    public void onClickFragmentChanger(View view){
        fragmentTransaction = fragmentManager.beginTransaction();

        switch(view.getId()){
            case R.id.btnCancelData :
                fragmentTransaction.remove(webDataFragment);
                fragmentTransaction.add(R.id.bodyContentContainer, bodyContentFragment, BodyContentFragment.TAG);
                break;
            case R.id.btnSubmitData :
                // Save email and password for current website
                break;
        }

        fragmentTransaction.commit();
    }

    private void setDefaultFragmentState(){
        // Transaction begin
        fragmentTransaction = fragmentManager.beginTransaction();
        if(fragmentManager.findFragmentByTag(HeaderFragment.TAG) == null &&
                fragmentManager.findFragmentByTag(BodyFragment.TAG) == null &&
                    fragmentManager.findFragmentByTag(BodyContentFragment.TAG) == null) {
            fragmentTransaction.add(R.id.FragmContainer, headerFragment, HeaderFragment.TAG);
            fragmentTransaction.add(R.id.FragmContainer, bodyFragment, BodyFragment.TAG);
            fragmentTransaction.add(R.id.bodyContentContainer, bodyContentFragment, BodyContentFragment.TAG);
        }
        fragmentTransaction.commit();
        //end
    }

   public void setListItemClick(ViewHolder vhold){
        // Transaction begin
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.remove(bodyContentFragment);
       StaticDataContainer.CurrentViewHolder = vhold;
       if(fragmentManager.findFragmentByTag(BodyContentFragment.TAG) != null &&
               fragmentManager.findFragmentByTag(WebDataFragment.TAG) == null)
       fragmentTransaction.replace(R.id.bodyContentContainer, webDataFragment, WebDataFragment.TAG);
       // Creating webDataFragment using current static CurrentViewHolder
      //  fragmentTransaction.add(R.id.bodyContentContainer, webDataFragment, WebDataFragment.TAG);
        fragmentTransaction.commit();
        //end
    }
}
