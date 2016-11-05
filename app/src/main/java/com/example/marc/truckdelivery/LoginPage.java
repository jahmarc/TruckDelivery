package com.example.marc.truckdelivery;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
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
        getMenuInflater().inflate(R.menu.menu_login_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId()) {
            //case R.id.app_bar_search:

            case R.id.app_bar_language:

        }
        return true;
        }



}

