package com.example.marc.truckdelivery;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import Fragment.DatePickerFragment;
import db.adapter.CustomerDataSource;
import db.adapter.DeliveryDataSource;
import db.adapter.DriverDataSource;
import db.object.CustomerObject;
import db.object.DriverObject;

public class DeliveryAdd extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    Spinner spinnerClient_add;
    Spinner spinnerChauffeur_add;
    EditText editTextdeNumCourse_add;
    Button buttonDate_add;
    EditText editTextdeQte_add;
    EditText editTextdeCondi_add;
    EditText editTextdeMar_add;
    Button buttonSave_add;
    DeliveryDataSource dts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_add);

        dts = new DeliveryDataSource(this);

        // Spinner element
        spinnerClient_add = (Spinner)findViewById(R.id.spinnerChauffeur_add);
        spinnerChauffeur_add = (Spinner)findViewById(R.id.spinnerClient_add);

        // Spinner click listener
        spinnerClient_add.setOnItemSelectedListener(this);
        spinnerChauffeur_add.setOnItemSelectedListener(this);

        //Fill the Spinners
        //Spinner Driver
        DriverDataSource dts = new DriverDataSource(this);
        List<DriverObject> drivers = new ArrayList<DriverObject>();

        drivers = dts.getAllDrivers();
        List<String> spinnerdriver =new ArrayList<String>(drivers.size());

        for(DriverObject driver : drivers){
            spinnerdriver.add(driver.getId(), driver.getFirstname()+ " " + driver.getName());
        }

        //Spinner Customer
        CustomerDataSource cds = new CustomerDataSource(this);
        List<CustomerObject> customers = new ArrayList<CustomerObject>();

        customers = cds.getAllCustomers();
        List<String> spinnercustomer =new ArrayList<String>();

        for(CustomerObject customer : customers){
            spinnercustomer.add(customer.getId(),customer.getSociety() + " " + customer.getFirstname()+ " " + customer.getName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerdriver);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnercustomer);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerChauffeur_add.setAdapter(dataAdapter);
        spinnerClient_add.setAdapter(dataAdapter2);

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
        updateViews();
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
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
    private void updateViews() {
        Resources resources = getResources();

        spinnerClient_add = (Spinner)findViewById(R.id.spinnerClient_add);
        spinnerChauffeur_add = (Spinner)findViewById(R.id.spinnerChauffeur_add);
        editTextdeNumCourse_add = (EditText)findViewById(R.id.editTextdeNumCourse_add);
        buttonDate_add = (Button)findViewById(R.id.buttonDate_add);
        editTextdeQte_add = (EditText)findViewById(R.id.editTextdeQte_add);
        editTextdeCondi_add = (EditText)findViewById(R.id.editTextdeCondi_add);
        editTextdeMar_add = (EditText)findViewById(R.id.editTextdeMar_add);
        buttonSave_add = (Button)findViewById(R.id.buttonSave_add);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
        ((EditText) findViewById(R.id.editTextdeDate)).setText(dateFormat.format(calendar.getTime()));
    }

    public void showDatePickerDialog(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
        }
}
