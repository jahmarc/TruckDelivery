package com.example.marc.truckdelivery;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import db.object.DriverObject;

public class Driver extends AppCompatActivity {

    Bundle bundle;
    String ReditTextdrNom ;
    String ReditTextdrPrenom ;
    String ReditTextdrPlaque ;
    String ReditTextdrPhone ;
    String ReditTextdrCam ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));


        /*
        *Recuperation des valeurs en toute sécurité
         */
        if(savedInstanceState==null) {
            bundle = getIntent().getExtras();
            if (bundle == null) {
                ReditTextdrNom = null ;
                ReditTextdrPrenom = null  ;
                ReditTextdrPlaque = null  ;
                ReditTextdrPhone = null  ;
                ReditTextdrCam = null  ;
            } else {
                ReditTextdrNom = bundle.getString("name") ;
                ReditTextdrPrenom = bundle.getString("firstname");
                ReditTextdrPlaque = bundle.getString("plate");
                ReditTextdrPhone = bundle.getString("phone");
                ReditTextdrCam = bundle.getString("user");
            }
        }else{
            ReditTextdrNom = (String)savedInstanceState.getSerializable("name");
            ReditTextdrPrenom = (String)savedInstanceState.getSerializable("firstname");
            ReditTextdrPlaque = (String)savedInstanceState.getSerializable("plate");
            ReditTextdrPhone = (String)savedInstanceState.getSerializable("phone");
            ReditTextdrCam = (String)savedInstanceState.getSerializable("user");
            }

/*
        String ReditTextdrNom = (String) bundle.get("name");
        String ReditTextdrPrenom = bundle.getString("firstname");
        String ReditTextdrPlaque = bundle.getString("plate");
        String ReditTextdrPhone = bundle.getString("phone");
        String ReditTextdrCam = bundle.getString("user");
*/
        EditText editTextdrNom=(EditText)findViewById(R.id.editTextdrNom) ;
        EditText editTextdrPrenom=(EditText)findViewById(R.id.editTextdrPrenom) ;
        EditText editTextdrPlaque=(EditText)findViewById(R.id.editTextdrPlaque) ;
        EditText editTextdrPhone=(EditText)findViewById(R.id.editTextdrPhone) ;
        //Button drbuttonSave =(Button)findViewById(R.id.drbuttonSave);
        //Button buttonDelivery =(Button)findViewById(R.id.buttonDelivery);
        EditText editTextdrCam=(EditText)findViewById(R.id.editTextdrCam) ;

        editTextdrNom.setText(ReditTextdrNom);
        editTextdrPrenom.setText(ReditTextdrPrenom);
        editTextdrPlaque.setText(ReditTextdrPlaque);
        editTextdrPhone.setText(ReditTextdrPhone);
        editTextdrCam.setText(ReditTextdrCam);



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
            default:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
        }
        return true;
    }
    private void updateViews() {
        Resources resources = getResources();

        EditText editTextdrNom=(EditText)findViewById(R.id.editTextdrNom) ;
        EditText editTextdrPrenom=(EditText)findViewById(R.id.editTextdrPrenom) ;
        EditText editTextdrPlaque=(EditText)findViewById(R.id.editTextdrPlaque) ;
        EditText editTextdrPhone=(EditText)findViewById(R.id.editTextdrPhone) ;
        Button drbuttonSave =(Button)findViewById(R.id.drbuttonSave);
        Button buttonDelivery =(Button)findViewById(R.id.buttonDelivery);
        EditText editTextdrCam=(EditText)findViewById(R.id.editTextdrCam) ;

        editTextdrNom.setHint(resources.getString(R.string.nom));
        editTextdrPrenom.setHint(resources.getString(R.string.pr_nom));
        editTextdrPlaque.setHint(resources.getString(R.string.plaque));
        editTextdrPhone.setHint(resources.getString(R.string.t_l_phone));
        drbuttonSave.setHint(resources.getString(R.string.sauvegarder));
        buttonDelivery.setHint(resources.getString(R.string.livraisons));
        editTextdrCam.setHint(resources.getString(R.string.n_camion));


    }
}
