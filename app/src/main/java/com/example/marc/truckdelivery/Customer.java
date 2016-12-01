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
import db.object.CustomerObject;

import static com.example.marc.truckdelivery.R.id.editTextcAdress;

public class Customer extends AppCompatActivity {

    Bundle bundle;
    Integer RCustomerId;
    EditText editTextcAdress;
    EditText editTextcNPA;
    EditText editTextcLocalite;
    EditText editTextcPrenom;
    EditText editTextcPhone;
    EditText editTextcSociete;
    EditText editTextcName;
    Button buttoncSave;
    Button buttoncDelete;

    CustomerDataSource dts;
    CustomerObject customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
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

        /*
         *Recuperation des valeurs en sécurité
         */
        if(savedInstanceState==null) {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                RCustomerId = null ;
            } else {
                RCustomerId = bundle.getInt("idCustomer") ;
            }
        }else{
            RCustomerId = (int)savedInstanceState.getSerializable("idCustomer");
        }

        editTextcAdress = (EditText)findViewById(R.id.editTextcAdress);
        editTextcNPA = (EditText)findViewById(R.id.editTextcNPA);
        editTextcLocalite = (EditText)findViewById(R.id.editTextcLocalite);
        editTextcPrenom = (EditText)findViewById(R.id.editTextcPrenom);
        editTextcPhone = (EditText)findViewById(R.id.editTextcPhone);
        editTextcSociete = (EditText)findViewById(R.id.editTextcSociete);
        editTextcName = (EditText)findViewById(R.id.editTextcName);
        buttoncDelete = (Button)findViewById(R.id.buttoncDelete);
        buttoncSave = (Button)findViewById(R.id.buttoncSave);

        customer = dts.getCustomerById(RCustomerId);
        editTextcAdress.setText(""+customer.getAdress()) ;
        editTextcLocalite.setText(""+customer.getLocality())  ;
        editTextcPrenom.setText(""+customer.getFirstname())   ;
        editTextcPhone.setText(""+customer.getPhone())  ;
        editTextcSociete.setText(""+customer.getSociety())  ;
        editTextcName.setText(""+customer.getName())  ;
        editTextcNPA.setText(""+customer.getPostcode())  ;
        buttoncDelete.setText(getString(R.string.delete));
        buttoncSave.setText(getString(R.string.sauvegarder));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true; //prends le style pour le menu de menu_basic
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


        editTextcSociete.setHint(resources.getString(R.string.soci_t));
        editTextcName.setHint(resources.getString(R.string.nom));
        editTextcPrenom.setHint(resources.getString(R.string.pr_nom));
        editTextcPhone.setHint(resources.getString(R.string.t_l_phone));
        editTextcAdress.setHint(resources.getString(R.string.adresse));
        editTextcNPA.setHint(resources.getString(R.string.npa));
        editTextcLocalite.setHint(resources.getString(R.string.localit));
        buttoncDelete.setText(resources.getString(R.string.livraisons));
        buttoncSave.setText(resources.getString(R.string.sauvegarder));
    }

    public void updateCustomer(View view) {
        int id = customer.getId();
        String society = editTextcSociete.getText().toString();
        String name = editTextcName.getText().toString();
        String firstname = editTextcPrenom.getText().toString();
        String phone = editTextcPhone.getText().toString();
        String adress = editTextcAdress.getText().toString();
        int postcode = Integer.parseInt(editTextcNPA.getText().toString());
        String locality = editTextcLocalite.getText().toString();

        CustomerObject customerUpdated = new CustomerObject(id, society, name, firstname, phone, adress, postcode, locality);
        dts.updateCustomer(customerUpdated);

        Intent intent = new Intent(this,admin_page.class);
        startActivity(intent);
    }
    public void deleteCustomer(View view) {
        int id = customer.getId();

        dts.deleteCustomer(id);

        Intent intent = new Intent(this,admin_page.class);
        startActivity(intent);
    }

}
