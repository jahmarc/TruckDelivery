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
import db.object.DeliveryObject;
import db.object.DriverObject;

public class DeliveryAdd extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    Spinner spinnerClient_add;
    Spinner spinnerChauffeur_add;

    EditText editTextdeDate;
    Button buttonDate_add;
    EditText editTextdeQte_add;
    EditText editTextdeCondi_add;
    EditText editTextdeMar_add;
    Button buttonSave_add;
    DeliveryDataSource dts;
    int customerpos;
    int driverpos;
    List<Integer> DriverIDs;
    List<Integer> CustomerIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_add);

        dts = new DeliveryDataSource(this);

        // Spinner element
        spinnerClient_add = (Spinner)findViewById(R.id.spinnerChauffeur_add);
        spinnerChauffeur_add = (Spinner)findViewById(R.id.spinnerClient_add);

        // Spinner click listener
        spinnerClient_add.setOnItemSelectedListener(new CustomerSpinnerClass());
        spinnerChauffeur_add.setOnItemSelectedListener(new DriverSpinnerClass());

        //Fill the Spinners
        //Spinner Driver
        DriverDataSource dts = new DriverDataSource(this);
        List<DriverObject> drivers = new ArrayList<DriverObject>();

        drivers = dts.getAllDrivers();
        List<String> spinnerdriver =new ArrayList<String>(drivers.size());
        DriverIDs = new ArrayList<Integer>(drivers.size());

        for(DriverObject driver : drivers){
            spinnerdriver.add(driver.getFirstname()+ " " + driver.getName());
            DriverIDs.add(driver.getId());

        }

        //Spinner Customer
        CustomerDataSource cds = new CustomerDataSource(this);
        List<CustomerObject> customers = new ArrayList<CustomerObject>();

        customers = cds.getAllCustomers();
        List<String> spinnercustomer =new ArrayList<String>();
        CustomerIDs = new ArrayList<Integer>(customers.size());

        for(CustomerObject customer : customers){
            spinnercustomer.add(customer.getId() + " " + customer.getSociety() + " " + customer.getFirstname()+ " " + customer.getName());
            CustomerIDs.add(customer.getId());
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

        buttonDate_add = (Button)findViewById(R.id.buttonDate_add);
        editTextdeDate = (EditText)findViewById(R.id.editTextdeDate);
        editTextdeQte_add = (EditText)findViewById(R.id.editTextdeQte_add);
        editTextdeCondi_add = (EditText)findViewById(R.id.editTextdeCondi_add);
        editTextdeMar_add = (EditText)findViewById(R.id.editTextdeMar_add);
        buttonSave_add = (Button)findViewById(R.id.buttonSave_add);


        buttonDate_add.setText(resources.getString(R.string.choisir_une_date));
        buttonDate_add.setHint(resources.getString(R.string.choisir_une_date));
        editTextdeQte_add.setHint(resources.getString(R.string.quantit));
        editTextdeCondi_add.setHint(resources.getString(R.string.conditionnement));
        editTextdeMar_add.setHint(resources.getString(R.string.marchandise));
        buttonSave_add.setText(resources.getString(R.string.sauvegarder));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    class DriverSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), " " + item, Toast.LENGTH_LONG).show();

            //Saving id to save the Delivery
            driverpos = position;

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    class CustomerSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), " " + item, Toast.LENGTH_LONG).show();

            //Saving id to save the Delivery

            customerpos = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
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

    public void Save_Delivery(View view) {
        spinnerClient_add = (Spinner)findViewById(R.id.spinnerClient_add);
        spinnerChauffeur_add = (Spinner)findViewById(R.id.spinnerChauffeur_add);
        //editTextdeNumCourse_add = (EditText)findViewById(R.id.editTextdeNumCourse_add);
        buttonDate_add = (Button)findViewById(R.id.buttonDate_add);
        editTextdeQte_add = (EditText)findViewById(R.id.editTextdeQte_add);
        editTextdeCondi_add = (EditText)findViewById(R.id.editTextdeCondi_add);
        editTextdeMar_add = (EditText)findViewById(R.id.editTextdeMar_add);
        buttonSave_add = (Button)findViewById(R.id.buttonSave_add);

        int driverid =  DriverIDs.get(driverpos);
        int customerid = CustomerIDs.get(customerpos);
        String date = editTextdeDate.getText().toString();
        int quantity = Integer.parseInt(editTextdeQte_add.getText().toString());
        String conditioning = editTextdeCondi_add.getText().toString();
        String article = editTextdeMar_add.getText().toString();

        dts.createDelivery(new DeliveryObject(driverid, customerid, date, quantity, conditioning, article));
        Intent toS_Delivery = new Intent(this, admin_page.class);
        startActivity(toS_Delivery);
    }
}
