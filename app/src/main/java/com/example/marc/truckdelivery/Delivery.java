package com.example.marc.truckdelivery;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Delivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
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

        Spinner spinnerClient =(Spinner)findViewById(R.id.spinnerClient);
        Spinner spinnerChauffeur =(Spinner)findViewById(R.id.spinnerChauffeur);
        EditText editTextdeNumCourse=(EditText)findViewById(R.id.editTextdeNumCourse);
        Button buttonDate = (Button)findViewById(R.id.buttonDate);
        EditText editTextdeQte=(EditText)findViewById(R.id.editTextdeQte);
        EditText editTextdeCondi=(EditText)findViewById(R.id.editTextdeCondi);
        EditText editTextdeMar=(EditText)findViewById(R.id.editTextdeMar);
        Button button2 = (Button)findViewById(R.id.button2);

        editTextdeNumCourse.setText(resources.getString(R.string.num_ro_de_course));
        buttonDate.setText(resources.getString(R.string.choisir_une_date));
        buttonDate.setHint(resources.getString(R.string.choisir_une_date));
        editTextdeQte.setText(resources.getString(R.string.quantit));
        editTextdeCondi.setText(resources.getString(R.string.conditionnement));
        editTextdeMar.setText(resources.getString(R.string.marchandise));
        button2.setText(resources.getString(R.string.sauvegarder));

    }
}
