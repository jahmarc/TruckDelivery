package com.example.marc.truckdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;
import android.app.Activity;

import com.example.marc.truckdelivery.LocaleHelper.*;

import db.adapter.DriverDataSource;
import db.object.DriverObject;


public class LoginPage extends AppCompatActivity {
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        /**
         * Add additional functions to actionbar
         */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

        LocaleHelper.OnCreate(this,"en");
        LocaleHelper.setLocale(this,"en");
        context = this;

    }

    //Method when we click on button Login
    public void login(View view) {
        //admin 1
        String admin1="helder";
        String pass1="1234";
        //admin 2
        String admin2="marc";
        String pass2="9876";
        EditText login  =(EditText)findViewById(R.id.Log_on);
        EditText pass = (EditText)findViewById(R.id.Log_pass);
        String loguser = login.getText().toString();
        String logpass = pass.getText().toString();
        DriverDataSource dts = new DriverDataSource(context);

        DriverObject driver =null;
        driver = dts.getUser(loguser);

        //Test on the login

            //Test if it's user 1
        if(loguser.equals(admin1)){
            //Test if password is correct
            if(logpass.equals(pass1)) {
                Intent intentAdmin = new Intent(this, admin_page.class);
                startActivity(intentAdmin);
                if (LocaleHelper.getLanguage(context) != "fr") {
                    Toast.makeText(getApplicationContext(), "Welcome " + loguser + " to the Admin Page.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bienvenue "+ loguser +" a la page administrative.",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(LocaleHelper.getLanguage(context)!="fr"){
                    Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //Test if it's user 2
        else if(loguser.equals(admin2)){
            //Test if password is correct
            if(logpass.equals(pass2)) {
                Intent intentAdmin = new Intent(this, admin_page.class);
                startActivity(intentAdmin);
                if (LocaleHelper.getLanguage(context) != "fr") {
                    Toast.makeText(getApplicationContext(), "Welcome " + loguser + " to the Admin Page.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Bienvenue "+ loguser +" a la page administrative.",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(LocaleHelper.getLanguage(context)!="fr"){
                    Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //Test si c'est un chauffeur
        else if(driver != null) {
            if (loguser.equals(driver.getNumTruck().toString()) && logpass.equals(driver.getPassword().toString())) {
                int id = driver.getId();
                Intent intentDriver = new Intent(this, driver_page.class);
                intentDriver.putExtra("id_chauffeur", id);
                startActivity(intentDriver);
                if (LocaleHelper.getLanguage(context) != "fr") {
                    Toast.makeText(getApplicationContext(), "Welcome " + loguser + " to the Driver Page.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Bienvenue " + loguser + " a la page des chauffeurs.", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (LocaleHelper.getLanguage(context) != "fr") {
                    Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
                }
            }
        }
            else{
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_basic, menu);
        return true; //prends le style pour le menu de menu_basic
    }

    @Override
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

        EditText logon = (EditText)findViewById(R.id.Log_on);
        EditText pass = (EditText)findViewById(R.id.Log_pass);
        Button log =(Button)findViewById(R.id.log_button);

        logon.setHint(resources.getString(R.string.enter_your_username));
        pass.setHint(resources.getString(R.string.password));
        log.setText(resources.getString(R.string.login));
    }


}

