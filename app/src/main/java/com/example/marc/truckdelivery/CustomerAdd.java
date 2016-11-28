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

import db.adapter.CustomerDataSource;
import db.adapter.DriverDataSource;
import db.object.CustomerObject;

public class CustomerAdd extends AppCompatActivity {

    EditText editTextcSociete_add;
    EditText editTextcName_add;
    EditText editTextcPrenom_add;
    EditText editTextcPhone_add;
    EditText editTextcNPA_add;
    EditText editTextcLocalite_add;
    Button buttoncSave_add;
    EditText editTextcAdress_add;
    CustomerDataSource dts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);

        dts = new CustomerDataSource(this);

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

        editTextcSociete_add = (EditText)findViewById(R.id.editTextcSociete_add);
        editTextcName_add = (EditText)findViewById(R.id.editTextcName_add);
        editTextcPrenom_add = (EditText)findViewById(R.id.editTextcPrenom_add);
        editTextcPhone_add = (EditText)findViewById(R.id.editTextcPhone_add);
        editTextcNPA_add = (EditText)findViewById(R.id.editTextcNPA_add);
        editTextcLocalite_add = (EditText)findViewById(R.id.editTextcLocalite_add);
        buttoncSave_add = (Button)findViewById(R.id.buttoncSave_add);
        editTextcAdress_add = (EditText)findViewById(R.id.editTextcAdress_add);

        editTextcSociete_add.setHint(resources.getString(R.string.soci_t));
        editTextcName_add.setHint(resources.getString(R.string.nom));
        editTextcPrenom_add.setHint(resources.getString(R.string.pr_nom));
        editTextcPhone_add.setHint(resources.getString(R.string.t_l_phone));
        editTextcNPA_add.setHint(resources.getString(R.string.npa));
        editTextcLocalite_add.setHint(resources.getString(R.string.localit));
        buttoncSave_add.setHint(resources.getString(R.string.sauvegarder));
        editTextcAdress_add.setHint(resources.getString(R.string.adresse));
    }

    public void Save_Customer(View view) {
        String society = editTextcSociete_add.getText().toString();
        String name = editTextcName_add.getText().toString();
        String firstname = editTextcPrenom_add.getText().toString();
        String phone = editTextcPhone_add.getText().toString();
        String adress = editTextcAdress_add.getText().toString();
        int postcode = Integer.parseInt(editTextcNPA_add.getText().toString());
        String locality = editTextcLocalite_add.getText().toString();

        dts.createCustomer(new CustomerObject(society,name,firstname,phone,adress,postcode,locality));
        Intent toS_Customer = new Intent(this,admin_page.class);
        startActivity(toS_Customer);
    }
}
