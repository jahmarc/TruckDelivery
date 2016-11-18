package db.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marc.truckdelivery.R;

import java.util.ArrayList;

import db.object.Driver;

/**
 * Created by Helder on 18.11.2016.
 */

public class DriverAdapter extends ArrayAdapter<Driver>{

    public DriverAdapter(Context context, ArrayList<Driver> drivers) {
        super(context,0, drivers);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        try{
        Driver driver = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_driver,parent,false);
        }
        EditText editTextdrNom = (EditText)convertView.findViewById(R.id.editTextdrNom);
        EditText editTextdrPrenom = (EditText)convertView.findViewById(R.id.editTextdrPrenom);
        EditText editTextdrPlaque = (EditText)convertView.findViewById(R.id.editTextdrPlaque);
        EditText editTextdrPhone = (EditText)convertView.findViewById(R.id.editTextdrPhone);
        EditText editTextdrCam = (EditText)convertView.findViewById(R.id.editTextdrCam);

        editTextdrCam.setText(driver.getId());
        editTextdrNom.setText(driver.getName());
        editTextdrPrenom.setText(driver.getFirstname());
        editTextdrPlaque.setText(driver.getPlate());
        editTextdrPhone.setText(driver.getPhone());

        return convertView;
        }catch (Exception ex){
            Log.e("adapter","error",ex);
            return null;
        }
    }
}
