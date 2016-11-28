package com.example.marc.truckdelivery;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import db.SQLiteHelper;
import db.adapter.CustomerAdapter;
import db.adapter.CustomerDataSource;
import db.adapter.DeliveryAdapter;
import db.adapter.DeliveryDataSource;
import db.object.CustomerObject;
import db.object.DeliveryObject;

public class Searchable_delivery_Activity extends AppCompatActivity {

    ListView lv;
    List<DeliveryObject> deliveries ;
    SQLiteHelper helper ;
    DeliveryObject deliverySelected;
    DeliveryObject[] list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable_delivery);
        context = this;
        helper.getInstance(context);

        //Get the intent, verify the action and get the query
        DeliveryDataSource dts = new DeliveryDataSource(context);
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(query);
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

        lv = (ListView) findViewById(R.id.result_delivery);

        DeliveryAdapter adapter = new DeliveryAdapter(context,deliveries);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deliverySelected = (DeliveryObject) parent.getItemAtPosition(position);
                int deliverySelectedId=deliverySelected.getId();
                Intent toDelivery = new Intent(Searchable_delivery_Activity.this,Delivery.class);
                toDelivery.putExtra("idDelivery",deliverySelectedId);
                if (LocaleHelper.getLanguage(context) == "en") {
                    Toast.makeText(getBaseContext(), "Delivery n°" + deliverySelected.getId() + " of " + deliverySelected.getDate() + " selected", Toast.LENGTH_SHORT).show();
                    startActivity(toDelivery);
                } else {
                    Toast.makeText(getBaseContext(), "Livraison n°" + deliverySelected.getId() + " du " + deliverySelected.getDate() + " sélectionnée", Toast.LENGTH_SHORT).show();
                    startActivity(toDelivery);
                }
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic, menu);
        return true; //prends le style pour le menu de menu_basic
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

    private void doSearchQuery(String query) {
        DeliveryDataSource dts = new DeliveryDataSource(context);
        deliveries = dts.searchDeliveries(query);
    }
}
