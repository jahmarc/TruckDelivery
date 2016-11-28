package com.example.marc.truckdelivery;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.adapter.CustomerDataSource;
import db.adapter.DeliveryDataSource;
import db.adapter.DriverDataSource;
import db.object.CustomerObject;
import db.object.DeliveryObject;
import db.object.DriverObject;

import static com.example.marc.truckdelivery.R.id.buttondDelDelete;

public class Delivery extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Bundle bundle;
    Integer RDeliveryId;
    DeliveryDataSource dets;
    DeliveryObject delivery;
    Spinner spinnerClient;
    Spinner spinnerChauffeur;
    EditText editTextdeNumCourse;
    Button buttonDate;
    EditText editTextdeQte;
    EditText editTextdeCondi;
    EditText editTextdeMar;
    Button buttondSave;
    Button buttondDelDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        dets = new DeliveryDataSource(this);

        // Spinner element
        Spinner spinnerd = (Spinner) findViewById(R.id.spinnerChauffeur);
        Spinner spinnerc = (Spinner) findViewById(R.id.spinnerClient);

        // Spinner click listener
        spinnerd.setOnItemSelectedListener(this);
        spinnerc.setOnItemSelectedListener(this);

        //Fill the Spinners
            //Spinner Driver
        DriverDataSource dts = new DriverDataSource(this);
        List<DriverObject> drivers = new ArrayList<DriverObject>();

        drivers = dts.getAllDrivers();
        List<String> spinnerdriver =new ArrayList<String>();

        for(DriverObject driver : drivers){
            spinnerdriver.add(driver.getFirstname()+ " " + driver.getName());
        }

            //Spinner Customer
        CustomerDataSource cds = new CustomerDataSource(this);
        List<CustomerObject> customers = new ArrayList<CustomerObject>();

        customers = cds.getAllCustomers();
        List<String> spinnercustomer =new ArrayList<String>();

        for(CustomerObject customer : customers){
            spinnercustomer.add(customer.getSociety() + " " + customer.getFirstname()+ " " + customer.getName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerdriver);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnercustomer);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerd.setAdapter(dataAdapter);
        spinnerc.setAdapter(dataAdapter2);

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
         *Recuperation des valeurs en sécurité
         */
        if(savedInstanceState==null) {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                RDeliveryId = null ;
            } else {
                RDeliveryId = bundle.getInt("idDelivery") ;
            }
        }else{
            RDeliveryId = (int)savedInstanceState.getSerializable("idDelivery");
        }

        delivery = dets.getDeliveryById(RDeliveryId);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
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

         spinnerClient =(Spinner)findViewById(R.id.spinnerClient);
         spinnerChauffeur =(Spinner)findViewById(R.id.spinnerChauffeur);
         editTextdeNumCourse=(EditText)findViewById(R.id.editTextdeNumCourse);
         buttonDate = (Button)findViewById(R.id.button);
         editTextdeQte=(EditText)findViewById(R.id.editTextdeQte);
         editTextdeCondi=(EditText)findViewById(R.id.editTextdeCondi);
         editTextdeMar=(EditText)findViewById(R.id.editTextdeMar);
         buttondSave = (Button)findViewById(R.id.buttonSave);
        buttondDelDelete = (Button)findViewById(R.id.buttondDelDelete);

        editTextdeNumCourse.setText(resources.getString(R.string.num_ro_de_course));
        buttonDate.setText(resources.getString(R.string.choisir_une_date));
        buttonDate.setHint(resources.getString(R.string.choisir_une_date));
        editTextdeQte.setText(resources.getString(R.string.quantit));
        editTextdeCondi.setText(resources.getString(R.string.conditionnement));
        editTextdeMar.setText(resources.getString(R.string.marchandise));
        buttondSave.setText(resources.getString(R.string.sauvegarder));
        buttondDelDelete.setText(resources.getString(R.string.delete));

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

    public void UpdateDelivery(View view) {
        int id = delivery.getId();
       // int driverid = ;
        //int customerid = ;
        // date = ;
        int quantity = Integer.parseInt(editTextdeQte.getText().toString());
        String conditioning = editTextdeCondi.getText().toString();
        String article = editTextdeMar.getText().toString();
    }
    public void delete_Delivery(View view) {
        int id = delivery.getId();

        dets.deleteDelivery(id);

        Intent intent = new Intent(this,search_delivery.class);
        startActivity(intent);
    }

}
