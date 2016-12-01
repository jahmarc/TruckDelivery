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

import java.util.List;

import db.SQLiteHelper;
import db.adapter.CustomerAdapter;
import db.adapter.CustomerDataSource;
import db.object.CustomerObject;

public class search_customer extends AppCompatActivity {

    ListView lv;
    Context context;
    List<CustomerObject> customers;
    CustomerObject customerSelected;
    SQLiteHelper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
        context = this;
        final CustomerDataSource dts = new CustomerDataSource(this);
        helper.getInstance(context);


        //TEST
        //dts.createCustomer(new CustomerObject("","Nicolier","Marc","027777777","Rue de la rue 8",1955,"Chamoson"));
        //dts.createCustomer(new CustomerObject("SecuPoca","Pocas","Helder","027777777","Rue de la tour 10",1920,"Martigny"));
        //dts.createCustomer(new CustomerObject("Nestlé","","","02788987777","Avenue saint pierre",1000,"Chamoson"));


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

        customers = dts.getAllCustomers();

        CustomerAdapter adapter = new CustomerAdapter(context, customers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customerSelected = (CustomerObject) parent.getItemAtPosition(position);
                int customerSelectedID = customerSelected.getId();
                Intent toCustomer = new Intent(search_customer.this,Customer.class);
                toCustomer.putExtra("idCustomer",customerSelectedID);
                if(LocaleHelper.getLanguage(context)=="en"){
                    Toast.makeText(getBaseContext(),"Customer at "+customerSelected.getLocality()+" selected",Toast.LENGTH_SHORT).show();
                    startActivity(toCustomer);
                }else{
                    Toast.makeText(getBaseContext(),"Client a "+customerSelected.getLocality()+" selectionné",Toast.LENGTH_SHORT).show();}
                    startActivity(toCustomer);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        //Get the SearchView  and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Searchable_customer_Activity.class)));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
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

    public void newCustomer(View view) {
        Intent newCustomer = new Intent(this,CustomerAdd.class);
        startActivity(newCustomer);
    }
}
