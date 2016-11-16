package com.example.marc.truckdelivery;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class Driver extends AppCompatActivity {

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
