package com.example.marc.truckdelivery;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import db.adapter.DriverDataSource;
import db.object.DriverObject;

public class driver_page extends AppCompatActivity {
    Bundle bundle;
    Integer RDriverId;
    DriverDataSource dts;
    DriverObject driver;
    TextView driver_page_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_page);

        /**
         * Add additional functions to actionbar
         */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

        dts = new DriverDataSource(this);
        /*
         *Recuperation des valeurs en sécurité
         */
        if(savedInstanceState==null) {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                RDriverId = null ;
            } else {
                RDriverId = bundle.getInt("id_chauffeur") ;
            }
        }else{
            RDriverId = (int)savedInstanceState.getSerializable("id_chauffeur");
        }

        driver = dts.getDriverById(RDriverId);
        driver_page_name = (TextView)findViewById(R.id.driver_page_name);
        driver_page_name.setText(driver.getFirstname() + " "+ driver.getName());
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.id_enFlag:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
            case R.id.id_frFlag:
                LocaleHelper.setLocale(this,"fr");
                updateViews();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                Intent back = new Intent(this,LoginPage.class);
                startActivity(back);
                finish();
            default:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
        }
        return true;
    }
    private void updateViews() {
        Resources resources = getResources();

        TextView driver_page_name =(TextView) findViewById(R.id.driver_page_name);

        driver_page_name.setText(resources.getString(R.string.nom));

    }
}
