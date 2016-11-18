package com.example.marc.truckdelivery;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import db.adapter.DriverAdapter;
import db.adapter.DriverDataSource;
import db.object.*;
import db.object.Driver;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class search_driver extends AppCompatActivity {

    ListView lv;
    Context context;
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    DriverDataSource dts = new DriverDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
try {
    Driver driver1 = new Driver("Jean", "Paul", "0273066565", "VS259", "JP", "1234");
    Driver driver2 = new Driver("John", "Doe", "007", "VS007", "JD", "007");
    dts.createDriver(driver1);
    dts.createDriver(driver2);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_driver);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayUseLogoEnabled(true);
    getSupportActionBar().setLogo(R.drawable.ic_launcher);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
    context = this;

    drivers = (ArrayList<Driver>) dts.getAllDrivers();

    DriverAdapter adapter = new DriverAdapter(this, drivers);
    lv = (ListView) findViewById(R.id.search_driver);

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
catch(Exception e){
    Log.e("CREATE", "error", e);
}
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
