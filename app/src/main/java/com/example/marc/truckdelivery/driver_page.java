package com.example.marc.truckdelivery;

import android.app.DatePickerDialog;
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
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import Fragment.DatePickerFragment;
import db.SQLiteHelper;
import db.adapter.DeliveryAdapter;
import db.adapter.DeliveryDataSource;
import db.adapter.DriverDataSource;
import db.object.DeliveryObject;
import db.object.DriverObject;

public class driver_page extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    ListView lv;
    Context context;
    List<DeliveryObject> deliveries;
    DeliveryObject deliverySelected;
    Button driver_page_chooseDate;
    EditText driver_page_EditTextDate;
    SQLiteHelper helper;
    Bundle bundle;
    Integer RDriverId;
    DriverDataSource dts;
    DriverObject driver;
    TextView driver_page_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_page);
        context = this;
        final DeliveryDataSource dets = new DeliveryDataSource(context);
        helper.getInstance(context);
        dts = new DriverDataSource(this);

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

        /*
         * Secure data retrieval
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

        /**
         * Add additional functions to actionbar
         */
        driver = dts.getDriverById(RDriverId);
        driver_page_name = (TextView)findViewById(R.id.driver_page_name);
        driver_page_name.setText(driver.getFirstname() + " "+ driver.getName());

        /**
         * Add listview & functions
         */
        lv = (ListView) findViewById(R.id.driver_page_livraisons);

        deliveries = new ArrayList<DeliveryObject>();
        deliveries = dets.getDeliveriesByDriverId(RDriverId);

        DeliveryAdapter adapter = new DeliveryAdapter(context, deliveries);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deliverySelected = (DeliveryObject) parent.getItemAtPosition(position);
                int deliverySelectedId=deliverySelected.getId();
                Intent toDelivery = new Intent(driver_page.this,dr_delivery.class);
                toDelivery.putExtra("idDelivery",deliverySelectedId);
                if (LocaleHelper.getLanguage(context) == "en") {
                    Toast.makeText(getBaseContext(),"Delivery n°" + deliverySelected.getId() + " of " + deliverySelected.getDate() + " selected", Toast.LENGTH_SHORT).show();
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
        inflater.inflate(R.menu.menu_search, menu);

        //Get the SearchView  and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Searchable_driver_Activity.class)));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default


        return true; //prends le style pour le menu de menu_search
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
    /**
     * Function to update the resources for language changes
     */
    private void updateViews() {
        Resources resources = getResources();

        driver_page_name = (TextView) findViewById(R.id.driver_page_name);
        driver_page_chooseDate = (Button)findViewById(R.id.driver_page_chooseDate);
        driver_page_EditTextDate = (EditText)findViewById(R.id.driver_page_EditTextDate);
        String currentDate = DateFormat.getDateInstance().format(new Date());


        driver_page_name.setHint(resources.getString(R.string.nom));
        driver_page_chooseDate.setHint(resources.getString(R.string.choisir_une_date));
        driver_page_EditTextDate.setText(currentDate);
    }
    /**
     * Date picker
     */
    public void showDatePickerDialog_driver_page(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
    /**
      * To set date on EditText
      * @param calendar
      */
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((EditText) findViewById(R.id.driver_page_EditTextDate)).setText(dateFormat.format(calendar.getTime()));
    }


}
