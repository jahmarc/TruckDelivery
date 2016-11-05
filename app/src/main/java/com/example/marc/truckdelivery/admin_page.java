package com.example.marc.truckdelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class admin_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Button admin_buttonDelivery = (Button)findViewById(R.id.admin_buttonDelivery);
        Button admin_buttonClient = (Button)findViewById(R.id.admin_buttonClient);
        Button admin_buttonDriver = (Button)findViewById(R.id.admin_buttonDriver);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_login_page,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
