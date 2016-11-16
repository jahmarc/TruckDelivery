package com.example.marc.truckdelivery;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import static com.example.marc.truckdelivery.R.string.ibdriver;

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

    public void toDelivery(View view) {
        Intent toDelivery = new Intent(this,search_delivery.class);
        startActivity(toDelivery);

    }

    public void toClient(View view) {
        Intent toClient = new Intent(this,search_customer.class);
        startActivity(toClient);

    }

    public void toDriver(View view) {
        Intent toDriver = new Intent(this,search_driver.class);
        startActivity(toDriver);
    }
    private void updateViews() {
        Resources resources = getResources();

        ImageButton driver = (ImageButton)findViewById(R.id.imageButtonDriver);
        ImageButton customer = (ImageButton)findViewById(R.id.imageButtonCustomer);
        ImageButton delivery = (ImageButton)findViewById(R.id.imageButtonDelivery);

        driver.setContentDescription(resources.getString(R.string.ibdriver));
        customer.setContentDescription(resources.getString(R.string.ibcustomer));
        delivery.setContentDescription(resources.getString(R.string.ibdelivery));
    }
}
