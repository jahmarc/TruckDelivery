package com.example.marc.truckdelivery;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class search_delivery extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String>adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_delivery);

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

        context=this;

        String[] deliveries = getResources().getStringArray(R.array.Delivery);
        lv = (ListView) findViewById(R.id.search_delivery);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,deliveries);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(LocaleHelper.getLanguage(context)=="en"){
                    Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selectionné",Toast.LENGTH_SHORT).show();}
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true; //prends le style pour le menu de menu_search
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


    }
}
