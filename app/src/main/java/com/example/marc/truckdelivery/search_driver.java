package com.example.marc.truckdelivery;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import db.adapter.DriverAdapter;
import db.adapter.DriverDataSource;
import db.object.DriverObject;
import db.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class search_driver extends AppCompatActivity {

    ListView lv;
    Context context;
    List<DriverObject> drivers ;
    SQLiteHelper helper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_driver);
        context = this;
        DriverDataSource dts = new DriverDataSource(this);
        helper.getInstance(context);

        dts.createDriver(new DriverObject("Bond","James","007-007","Bond007","Bond","pass007"));

        /**
         * Add additional functions to actionbar
         */
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

        lv = (ListView) findViewById(R.id.search_driver);

        drivers = new ArrayList<DriverObject>();

        drivers=dts.getAllDrivers();

        DriverAdapter adapter = new DriverAdapter(context,drivers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LocaleHelper.getLanguage(context) == "en") {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selectionné", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
}
