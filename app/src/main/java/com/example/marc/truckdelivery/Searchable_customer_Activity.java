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
import db.adapter.DriverAdapter;
import db.adapter.DriverDataSource;
import db.object.CustomerObject;
import db.object.DriverObject;

public class Searchable_customer_Activity extends AppCompatActivity {

    ListView lv;
    List<CustomerObject> customers ;
    SQLiteHelper helper ;
    CustomerObject customerSelected;
    CustomerObject[] list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable_customer);
        context = this;
        helper.getInstance(context);

        //Get the intent, verify the action and get the query
        CustomerDataSource dts = new CustomerDataSource(context);
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

        lv = (ListView) findViewById(R.id.result_customer);

        CustomerAdapter adapter = new CustomerAdapter(context,customers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customerSelected = (CustomerObject) parent.getItemAtPosition(position);
                int customerSelectedId=customerSelected.getId();
                Intent toCustomer = new Intent(Searchable_customer_Activity.this,Customer.class);
                toCustomer.putExtra("idCustomer",customerSelectedId);
                if (LocaleHelper.getLanguage(context) == "en") {
                    Toast.makeText(getBaseContext(), customerSelected.getName() + " selected", Toast.LENGTH_SHORT).show();
                    startActivity(toCustomer);
                } else {
                    Toast.makeText(getBaseContext(), customerSelected.getName() + " selectionné", Toast.LENGTH_SHORT).show();
                    startActivity(toCustomer);
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
        CustomerDataSource dts = new CustomerDataSource(context);
        customers = dts.searchCustomer(query);
    }
}
