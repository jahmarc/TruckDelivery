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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.SQLiteHelper;
import db.adapter.CustomerAdapter;
import db.adapter.CustomerDataSource;
import db.object.CustomerObject;
import db.object.DriverObject;

public class search_customer extends AppCompatActivity {

    ListView lv;
    Context context;
    List<CustomerObject> customers;
    SQLiteHelper helper;
    CustomerObject customerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
        context = this;
        final CustomerDataSource dts = new CustomerDataSource(this);


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

        lv = (ListView) findViewById(R.id.search_customer);

        customers = new ArrayList<CustomerObject>();

        CustomerAdapter adapter = new CustomerAdapter(context,customers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customerSelected = (CustomerObject) parent.getItemAtPosition(position);
                int customerSelectedID = customerSelected.getId();
                Intent toCustomer = new Intent(search_customer.this,Customer.class);
                toCustomer.putExtra("id",customerSelectedID);
                if(LocaleHelper.getLanguage(context)=="en"){
                    Toast.makeText(getBaseContext(),"Customer at "+customerSelected.getLocality()+" selected",Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getBaseContext(),"Client a "+customerSelected.getLocality()+" selectionné",Toast.LENGTH_SHORT).show();}
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    public void newCustomer(View view) {
        Intent newCustomer = new Intent(this,CustomerAdd.class);
        startActivity(newCustomer);
    }
}
