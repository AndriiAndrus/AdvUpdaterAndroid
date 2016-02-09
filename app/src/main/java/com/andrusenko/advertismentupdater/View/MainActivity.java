package com.andrusenko.advertismentupdater.View;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.andrusenko.advertismentupdater.Model.Adapters.ViewHolder;
import com.andrusenko.advertismentupdater.Model.Web.AsyncHttpCall;
import com.andrusenko.advertismentupdater.Model.db.RxDatabase;
import com.andrusenko.advertismentupdater.Presenter.PresenterMain;
import com.andrusenko.advertismentupdater.R;
import com.andrusenko.advertismentupdater.View.Fragments.BodyContentFragment;
import com.andrusenko.advertismentupdater.View.Fragments.BodyFragment;
import com.andrusenko.advertismentupdater.View.Fragments.WebDataFragment;
import com.cengalabs.flatui.FlatUI;

public class MainActivity extends FragmentActivity {

    // SHOULD NOT BE HERE!
    private RxDatabase rxDatabase;

    private PresenterMain presenter;

    private BodyFragment bodyFragment;
    private BodyContentFragment bodyContentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private WebDataFragment webDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.GRAPE);
        setContentView(R.layout.activity_main);

        try {
            getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRAPE, false, 2));
        } catch (Exception e) { /* Standart theame of ActionBar will be used */ }

        presenter = new PresenterMain(getApplicationContext());
        fragmentManager = getSupportFragmentManager();

        webDataFragment = new WebDataFragment();
        bodyFragment = new BodyFragment();
        bodyContentFragment = new BodyContentFragment();
        bodyContentFragment.setMain(this);

        setDefaultFragmentState();
        //TODO delete DEMO
        AsyncHttpCall callDemo = new AsyncHttpCall();
        callDemo.execute("param");
        String resultin;
        try {
            resultin = callDemo.get();
        } catch (Exception e) {
            resultin = e.getMessage();
        }
        Toast.makeText(getApplicationContext(), resultin, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickFragmentChanger(View view){
        fragmentTransaction = fragmentManager.beginTransaction();

        switch(view.getId()){
            case R.id.btnCancelData :
                fragmentTransaction.replace(R.id.bodyContentContainer, bodyContentFragment, BodyContentFragment.TAG);
                break;
            case R.id.btnSubmitData :
                // Save email and password for current website
                String domain = PresenterMain.getCurrentClickedVH().DOMAIN;
                String login = webDataFragment.editEmail.getText().toString();
                String password = webDataFragment.editPassword.getText().toString();
               boolean status = presenter.configuredWebsite(domain, login, password);
                if(status) {
                    Toast.makeText(getApplicationContext(), "Учетная запись успешно добавлена!", Toast.LENGTH_LONG).show();
                    fragmentTransaction.replace(R.id.bodyContentContainer, bodyContentFragment, BodyContentFragment.TAG);
                }else{
                    Toast.makeText(getApplicationContext(), "При добавлении вашей учетной записи произошла ошибка!", Toast.LENGTH_LONG).show();
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void setDefaultFragmentState(){
        // Transaction begin
        fragmentTransaction = fragmentManager.beginTransaction();

        if (fragmentManager.findFragmentByTag(BodyContentFragment.TAG) == null)
            fragmentTransaction.add(R.id.bodyContentContainer, bodyContentFragment, BodyContentFragment.TAG);

        fragmentTransaction.commit();
        //end
    }

   public void setListItemClick(ViewHolder vhold){
        // Transaction begin
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.remove(bodyContentFragment);
       PresenterMain.setCurrentClickedVH(vhold);
       if (fragmentManager.findFragmentByTag(WebDataFragment.TAG) == null)
       fragmentTransaction.replace(R.id.bodyContentContainer, webDataFragment, WebDataFragment.TAG);
       // Creating webDataFragment using current static CurrentViewHolder
      //  fragmentTransaction.add(R.id.bodyContentContainer, webDataFragment, WebDataFragment.TAG);
        fragmentTransaction.commit();
        //end
    }
}
