package com.example.marc.truckdelivery;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String ReditTextdrNom ;
    String ReditTextdrPrenom ;
    String ReditTextdrPlaque ;
    String ReditTextdrPhone ;
    String ReditTextdrCam ;
    String ReditTextdrPass;
    EditText editTextdrCam;
    EditText editTextdrNom;
    EditText editTextdrPrenom;
    EditText editTextdrPlaque;
    EditText editTextdrPhone;
    EditText editTextdrPass;
    DriverDataSource dts;
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
        updateViews();

        dts = new DriverDataSource(this);
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
                ReditTextdrPass = null ;
            } else {
                ReditTextdrNom = bundle.getString("name") ;
                ReditTextdrPrenom = bundle.getString("firstname");
                ReditTextdrPlaque = bundle.getString("plate");
                ReditTextdrPhone = bundle.getString("phone");
                ReditTextdrCam = bundle.getString("user");
                ReditTextdrPass = bundle.getString("pass");
            }
        }else{
            ReditTextdrNom = (String)savedInstanceState.getSerializable("name");
            ReditTextdrPrenom = (String)savedInstanceState.getSerializable("firstname");
            ReditTextdrPlaque = (String)savedInstanceState.getSerializable("plate");
            ReditTextdrPhone = (String)savedInstanceState.getSerializable("phone");
            ReditTextdrCam = (String)savedInstanceState.getSerializable("user");
            ReditTextdrPass = (String)savedInstanceState.getSerializable("pass");
            }


        editTextdrNom=(EditText)findViewById(R.id.editTextdrNom) ;
        editTextdrPrenom=(EditText)findViewById(R.id.editTextdrPrenom) ;
        editTextdrPlaque=(EditText)findViewById(R.id.editTextdrPlaque) ;
        editTextdrPhone=(EditText)findViewById(R.id.editTextdrPhone) ;
        editTextdrCam=(EditText)findViewById(R.id.editTextdrCam) ;
        editTextdrPass=(EditText)findViewById(R.id.editTextdrPass);

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

        TextView TextViewdrNom=(TextView)findViewById(R.id.textViewNom) ;
        TextView TextViewPrenom=(TextView)findViewById(R.id.textViewPrenom) ;
        TextView TextViewPlaque=(TextView)findViewById(R.id.textViewPlaque) ;
        TextView TextViewPhone=(TextView)findViewById(R.id.textViewPhone) ;
        Button drbuttonSave =(Button)findViewById(R.id.drbuttonSave);
        Button buttonDelivery =(Button)findViewById(R.id.buttonDelivery);
        TextView TextViewCam=(TextView)findViewById(R.id.textViewCam) ;
        TextView TextViewPass=(TextView)findViewById(R.id.textViewPass);

        TextViewdrNom.setText(resources.getString(R.string.nom));
        TextViewPrenom.setText(resources.getString(R.string.pr_nom));
        TextViewPlaque.setText(resources.getString(R.string.plaque));
        TextViewPhone.setText(resources.getString(R.string.t_l_phone));
        drbuttonSave.setHint(resources.getString(R.string.sauvegarder));
        buttonDelivery.setHint(resources.getString(R.string.livraisons));
        TextViewCam.setText(resources.getString(R.string.n_camion));
        TextViewPass.setText(resources.getString(R.string.password));
        TextViewPass.setHint(resources.getString(R.string.enter_a_newPass));



    }

    public void updateDriver(View view) {
        String Truck = editTextdrCam.getText().toString();
        String Nom = editTextdrNom.getText().toString() ;
        String Prenom = editTextdrPrenom.getText().toString();
        String Plaque = editTextdrPlaque.getText().toString();
        String Phone = editTextdrPhone.getText().toString();
        String Pass = editTextdrPass.getText().toString();

        DriverObject driverUpdated=new DriverObject(Nom,Prenom,Phone,Plaque,Truck,Pass);
        dts.updateDriver(driverUpdated);

    }
}
