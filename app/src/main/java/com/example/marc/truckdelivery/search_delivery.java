package com.example.marc.truckdelivery;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.SQLiteHelper;
import db.adapter.DeliveryAdapter;
import db.adapter.DeliveryDataSource;
import db.object.DeliveryObject;

public class search_delivery extends AppCompatActivity {

    ListView lv;
    Context context;
    List<DeliveryObject> deliveries;
    SQLiteHelper helper;
    DeliveryObject deliverySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_delivery);
        context = this;
        final DeliveryDataSource dts = new DeliveryDataSource(this);
        helper.getInstance(context);


        //TEST
        //dts.createDelivery(new DeliveryObject(1, 1, "25.11.2016", 5, "pal", "tomates"));
        //dts.createDelivery(new DeliveryObject(2, 2, "26.11.2016", 2, "pal", "choux"));

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

        lv = (ListView) findViewById(R.id.search_delivery);

        deliveries = dts.getAllDeliveries();

        DeliveryAdapter adapter = new DeliveryAdapter(context,deliveries);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deliverySelected = (DeliveryObject)parent.getItemAtPosition(position);
                int deliverySelectedId = deliverySelected.getId();
                Intent toDelivery = new Intent(search_delivery.this,Delivery.class);
                toDelivery.putExtra("idDelivery",deliverySelectedId);
                if(LocaleHelper.getLanguage(context)=="en"){
                    Toast.makeText(getBaseContext(),deliverySelected.getArticle()+" selected",Toast.LENGTH_SHORT).show();
                    startActivity(toDelivery);
                } else{
                    Toast.makeText(getBaseContext(),deliverySelected.getArticle()+" selectionné",Toast.LENGTH_SHORT).show();}
                    startActivity(toDelivery);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        //Get the SearchView  and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Searchable_driver_Activity.class)));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
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
            case R.id.app_bar_search:
                onSearchRequested();
                return true;
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

    public void newDelivery(View view) {
        Intent newDelivery = new Intent(this,DeliveryAdd.class);
        startActivity(newDelivery);
    }
}
