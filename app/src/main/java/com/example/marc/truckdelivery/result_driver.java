package com.example.marc.truckdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.SQLiteHelper;
import db.adapter.DriverAdapter;
import db.adapter.DriverDataSource;
import db.object.DriverObject;

public class result_driver extends AppCompatActivity {


    ListView lv;
    Context context;
    List<DriverObject> drivers ;
    SQLiteHelper helper ;
    DriverObject driverSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_driver);
        context = this;
        final DriverDataSource dts = new DriverDataSource(this);
        helper.getInstance(context);

             /*
        //testing
        dts.createDriver(new DriverObject("Bond","James","007-007","Bond007","Bond","pass007"));
        dts.createDriver(new DriverObject("Name","Firstname","phone","plate","numTruck","password"));
        for(int i=0;i<10;i++){
            dts.createDriver(new DriverObject("Name"+i,"Firstname"+i,"phone"+i,"plate"+i,"numTruck"+i,"password"+i));
        }
        */

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

        lv = (ListView) findViewById(R.id.search_driver);

        drivers = new ArrayList<DriverObject>();

       // drivers=dts.getAllDrivers();


        DriverAdapter adapter = new DriverAdapter(context,drivers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                driverSelected = (DriverObject) parent.getItemAtPosition(position);
                int driverSelectedId=driverSelected.getId();
                Intent toDriver = new Intent(result_driver.this,Driver.class);
                toDriver.putExtra("idDriver",driverSelectedId);
                if (LocaleHelper.getLanguage(context) == "en") {
                    Toast.makeText(getBaseContext(), driverSelected.getName() + " selected", Toast.LENGTH_SHORT).show();
                    startActivity(toDriver);
                } else {
                    Toast.makeText(getBaseContext(), driverSelected.getName() + " selectionné", Toast.LENGTH_SHORT).show();
                    startActivity(toDriver);
                }
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true; //prends le style pour le menu de menu_search
    }

    @Override
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
                break;
            default:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
        }
        return true;
    }
    private void updateViews() {
        Resources resources = getResources();


    }

    public void newDriver(View view) {
        Intent newDriver=new Intent(this,DriverAdd.class);
        startActivity(newDriver);
    }
}
