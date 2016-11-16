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


public class LoginPage extends AppCompatActivity {
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));
        LocaleHelper.OnCreate(this,"en");
        LocaleHelper.setLocale(this,"en");
        context = this;
    }
    public void login(View view) {
        String admin1="helder";
        String admin2="marc";
        String driver="driver";
        EditText login  =(EditText)findViewById(R.id.Log_on);
        String log=login.getText().toString();

        if(log.equals(admin1)||log.equals(admin2)){
            Intent intentAdmin = new Intent(this,admin_page.class);
            startActivity(intentAdmin);
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(),"Welcome "+log+" to the Admin Page.",Toast.LENGTH_SHORT).show();}
            else{Toast.makeText(getApplicationContext(),"Bienvenue "+log+" a la page administrative.",Toast.LENGTH_SHORT).show();}
        }else if(log.equals(driver)){
            Intent intentDriver = new Intent(this,driver_page.class);
            startActivity(intentDriver);
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(),"Welcome "+log+" to your Driver Page.",Toast.LENGTH_SHORT).show();
                }
            else{Toast.makeText(getApplicationContext(),"Bienvenue "+log+" a votre page chauffeur.",Toast.LENGTH_SHORT).show();}
        }else{
            if(LocaleHelper.getLanguage(context)!="fr"){
                Toast.makeText(getApplicationContext(), "Your Username or Password is not correct!", Toast.LENGTH_SHORT).show();

            }else{Toast.makeText(getApplicationContext(), "Votre nom et/ou votre mot de passe n'est pas correct!", Toast.LENGTH_SHORT).show();
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

