package com.example.marc.truckdelivery;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    //stocker la preference de langue serait une bonne idée avec des shared Preferences
    //on saurait quelle langue charger a chaque page

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

        }else if(log.equals(driver)){
            Intent intentDriver = new Intent(this,driver_page.class);
            startActivity(intentDriver);
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

                break;
            case R.id.id_frFlag:

                break;
        }
        return true;
        }



}

