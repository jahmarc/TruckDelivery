package com.example.marc.truckdelivery;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class admin_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6C7CE2")));

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_basic,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId()) {


            case R.id.app_bar_language:

        }
        return true;
    }

    public void toDelivery(View view) {
        Intent toDelivery = new Intent(this,Delivery.class);
        startActivity(toDelivery);
        //plutot search_delivery
    }

    public void toClient(View view) {
        Intent toClient = new Intent(this,Customer.class);
        startActivity(toClient);
        //plutot search_customer
    }

    public void toDriver(View view) {
        Intent toDriver = new Intent(this,search_driver.class);
        startActivity(toDriver);
    }
}
