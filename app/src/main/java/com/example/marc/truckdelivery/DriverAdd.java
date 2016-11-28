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
import android.widget.Button;
import android.widget.EditText;

import db.adapter.DriverDataSource;
import db.object.DriverObject;

public class DriverAdd extends AppCompatActivity {

    EditText editTextdr_sCam;
    EditText editTextdr_sNom;
    EditText editTextdr_sPrenom;
    EditText editTextdr_sPlaque;
    EditText editTextdr_sPhone;
    EditText editTextdr_sPassword;
    Button drbutton_sSave ;
    DriverDataSource dts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_add);

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

        editTextdr_sCam=(EditText)findViewById(R.id.editTextdr_sCam);
        editTextdr_sNom=(EditText)findViewById(R.id.editTextdr_sNom) ;
        editTextdr_sPrenom=(EditText)findViewById(R.id.editTextdr_sPrenom) ;
        editTextdr_sPlaque=(EditText)findViewById(R.id.editTextdr_sPlaque) ;
        editTextdr_sPhone=(EditText)findViewById(R.id.editTextdr_sPhone) ;
        editTextdr_sPassword=(EditText)findViewById(R.id.editTextdr_sPassword);
        drbutton_sSave =(Button)findViewById(R.id.drbutton_sSave);

        editTextdr_sCam.setHint(resources.getString(R.string.n_camion));
        editTextdr_sNom.setHint(resources.getString(R.string.nom));
        editTextdr_sPrenom.setHint(resources.getString(R.string.pr_nom));
        editTextdr_sPlaque.setHint(resources.getString(R.string.plaque));
        editTextdr_sPhone.setHint(resources.getString(R.string.t_l_phone));
        editTextdr_sPassword.setHint(resources.getString(R.string.password));
        drbutton_sSave.setHint(resources.getString(R.string.sauvegarder));

    }

    public void Save_Driver(View view) {
        String Truck = editTextdr_sCam.getText().toString();
        String Nom = editTextdr_sNom.getText().toString() ;
        String Prenom = editTextdr_sPrenom.getText().toString();
        String Plaque = editTextdr_sPlaque.getText().toString();
        String Phone = editTextdr_sPhone.getText().toString();
        String Password = editTextdr_sPassword.getText().toString();

        dts.createDriver(new DriverObject(Nom,Prenom,Phone,Plaque,Truck,Password));
        Intent toS_Driver = new Intent(this,admin_page.class);
        startActivity(toS_Driver);
    }
}
