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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

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
        EditText editTextcSociete =(EditText)findViewById(R.id.editTextcSociete);
        EditText editTextcName =(EditText)findViewById(R.id.editTextcName);
        EditText editTextcPrenom =(EditText)findViewById(R.id.editTextcPrenom);
        EditText editTextcPhone =(EditText)findViewById(R.id.editTextcPhone);
        EditText editTextcAdress =(EditText)findViewById(R.id.editTextcAdress);
        EditText editTextcNPA =(EditText)findViewById(R.id.editTextcNPA);
        EditText editTextcLocalite =(EditText)findViewById(R.id.editTextcLocalite);
        Button buttoncLivraison = (Button)findViewById(R.id.buttoncLivraisonDelete);
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
