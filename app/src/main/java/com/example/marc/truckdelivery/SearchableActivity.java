package com.example.marc.truckdelivery;

import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import db.adapter.DriverDataSource;
import db.object.DriverObject;

public class SearchableActivity extends AppCompatActivity {

    DriverObject[] list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        context = this;

        //Get the intent, verify the action and get the query
        DriverDataSource dts = new DriverDataSource(context);
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            list = (DriverObject[]) dts.searchDriver(query);
            intent.putExtra("liste", list);
        }

    }
}
