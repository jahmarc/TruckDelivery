package com.example.marc.truckdelivery;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import db.adapter.DriverDataSource;
import db.object.DriverObject;

public class Driver extends AppCompatActivity {

    Bundle bundle;
    Integer RDriverId ;
    EditText editTextdrCam;
    EditText editTextdrNom;
    EditText editTextdrPrenom;
    EditText editTextdrPlaque;
    EditText editTextdrPhone;
    EditText editTextdrPass;
    DriverDataSource dts;
    DriverObject driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

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

        dts = new DriverDataSource(this);
        /*
         *Recuperation des valeurs en sécurité
         */
        if(savedInstanceState==null) {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                RDriverId = null ;
            } else {
                RDriverId = bundle.getInt("idDriver") ;
            }
        }else{
            RDriverId = (int)savedInstanceState.getSerializable("idDriver");
           }

            //Get the driver and set the textViews
        editTextdrNom=(EditText)findViewById(R.id.editTextdrNom) ;
        editTextdrPrenom=(EditText)findViewById(R.id.editTextdrPrenom) ;
        editTextdrPlaque=(EditText)findViewById(R.id.editTextdrPlaque) ;
        editTextdrPhone=(EditText)findViewById(R.id.editTextdrPhone) ;
        editTextdrCam=(EditText)findViewById(R.id.editTextdrCam) ;
        editTextdrPass=(EditText)findViewById(R.id.editTextdrPass);

        driver = dts.getDriverById(RDriverId);
        editTextdrCam.setText(driver.getNumTruck());
        editTextdrNom.setText(driver.getName());
        editTextdrPrenom.setText(driver.getFirstname());
        editTextdrPhone.setText(driver.getPhone());
        editTextdrPlaque.setText(driver.getPlate());
        editTextdrPass.setHint("****");


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

        TextView TextViewdrNom=(TextView)findViewById(R.id.textViewNom) ;
        TextView TextViewPrenom=(TextView)findViewById(R.id.textViewPrenom) ;
        TextView TextViewPlaque=(TextView)findViewById(R.id.textViewPlaque) ;
        TextView TextViewPhone=(TextView)findViewById(R.id.textViewPhone) ;
        Button drbuttonSave =(Button)findViewById(R.id.drbuttonSave);
        Button deleteDriver =(Button)findViewById(R.id.deleteDriver);
        TextView TextViewCam=(TextView)findViewById(R.id.textViewCam) ;
        TextView TextViewPass=(TextView)findViewById(R.id.textViewPass);
        editTextdrPass=(EditText)findViewById(R.id.editTextdrPass);

        TextViewdrNom.setText(resources.getString(R.string.nom));
        TextViewPrenom.setText(resources.getString(R.string.pr_nom));
        TextViewPlaque.setText(resources.getString(R.string.plaque));
        TextViewPhone.setText(resources.getString(R.string.t_l_phone));
        drbuttonSave.setHint(resources.getString(R.string.sauvegarder));
        deleteDriver.setHint(resources.getString(R.string.delete));
        TextViewCam.setText(resources.getString(R.string.n_camion));
        TextViewPass.setHint(resources.getString(R.string.password));

    }

    public void updateDriver(View view) {
        int id = driver.getId();
        String Truck = editTextdrCam.getText().toString();
        String Nom = editTextdrNom.getText().toString() ;
        String Prenom = editTextdrPrenom.getText().toString();
        String Plaque = editTextdrPlaque.getText().toString();
        String Phone = editTextdrPhone.getText().toString();
        String Pass = editTextdrPass.getText().toString();

        DriverObject driverUpdated=new DriverObject(id,Nom,Prenom,Phone,Plaque,Truck,Pass);
        dts.updateDriver(driverUpdated);

        Intent intent = new Intent(this,search_driver.class);
        startActivity(intent);

    }
    public void delete_Driver(View view) {
        int id = driver.getId();

        dts.deleteDriver(id);

        Intent intent = new Intent(this,search_driver.class);
        startActivity(intent);
    }
}
