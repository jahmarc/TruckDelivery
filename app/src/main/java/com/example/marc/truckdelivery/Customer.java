package com.example.marc.truckdelivery;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
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
            default:
                LocaleHelper.setLocale(this,"en");
                updateViews();
                break;
        }
        return true;
    }
    private void updateViews() {
        Resources resources = getResources();
        EditText editTextcSociete =(EditText)findViewById(R.id.editTextcSociete);
        EditText editTextcName =(EditText)findViewById(R.id.editTextcName);
        EditText editTextcPrenom =(EditText)findViewById(R.id.editTextcPrenom);
        EditText editTextcPhone =(EditText)findViewById(R.id.editTextcPhone);
        EditText editTextcAdress =(EditText)findViewById(R.id.editTextcAdress);
        EditText editTextcNPA =(EditText)findViewById(R.id.editTextcNPA);
        EditText editTextcLocalite =(EditText)findViewById(R.id.editTextcLocalite);
        Button buttoncLivraison = (Button)findViewById(R.id.buttoncLivraison);
        Button buttoncSave = (Button)findViewById(R.id.buttoncSave);

        editTextcSociete.setText(resources.getString(R.string.soci_t));
        editTextcName.setText(resources.getString(R.string.nom));
        editTextcPrenom.setText(resources.getString(R.string.pr_nom));
        editTextcPhone.setText(resources.getString(R.string.t_l_phone));
        editTextcAdress.setText(resources.getString(R.string.adresse));
        editTextcNPA.setText(resources.getString(R.string.npa));
        editTextcLocalite.setText(resources.getString(R.string.localit));
        buttoncLivraison.setText(resources.getString(R.string.livraisons));
        buttoncSave.setText(resources.getString(R.string.sauvegarder));


    }
}
