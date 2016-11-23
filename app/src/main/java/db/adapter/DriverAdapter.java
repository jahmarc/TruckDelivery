package db.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import db.object.DriverObject;

import android.widget.TextView;


import com.example.marc.truckdelivery.R;

import java.util.List;



/**
 * Created by Helder on 21.11.2016.
 */

public class DriverAdapter extends ArrayAdapter<DriverObject>{

    private final Context context;
    private final List<DriverObject> drivers;

    public DriverAdapter(Context context, List<DriverObject> drivers) {
        super(context,-1, drivers);
        this.context = context;
        this.drivers = drivers;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout._row,parent,false);
        TextView textView = (TextView)rowView.findViewById(R.id.label);
        textView.setText(drivers.get(position).getName()+" "+ drivers.get(position).getFirstname());

        return rowView;
    }
}
