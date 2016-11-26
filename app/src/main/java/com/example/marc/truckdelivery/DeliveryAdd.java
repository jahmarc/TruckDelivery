package com.example.marc.truckdelivery;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Fragment.DatePickerFragment;
import db.adapter.DeliveryDataSource;

public class DeliveryAdd extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner spinnerClient_add;
    Spinner spinnerChauffeur_add;
    EditText editTextdeNumCourse_add;
    Button buttonDate_add;
    EditText editTextdeQte_add;
    EditText editTextdeCondi_add;
    EditText editTextdeMar_add;
    Button buttonSave_add;
    DeliveryDataSource dts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_add);

        dts = new DeliveryDataSource(this);

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

        spinnerClient_add = (Spinner)findViewById(R.id.spinnerClient_add);
        spinnerChauffeur_add = (Spinner)findViewById(R.id.spinnerChauffeur_add);
        editTextdeNumCourse_add = (EditText)findViewById(R.id.editTextdeNumCourse_add);
        buttonDate_add = (Button)findViewById(R.id.buttonDate_add);
        editTextdeQte_add = (EditText)findViewById(R.id.editTextdeQte_add);
        editTextdeCondi_add = (EditText)findViewById(R.id.editTextdeCondi_add);
        editTextdeMar_add = (EditText)findViewById(R.id.editTextdeMar_add);
        buttonSave_add = (Button)findViewById(R.id.buttonSave_add);

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    /**
      * To set date on EditText
      * @param calendar
      */
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((EditText) findViewById(R.id.editTextdeDate)).setText(dateFormat.format(calendar.getTime()));
    }

    public void showDatePickerDialog(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
        }
}
