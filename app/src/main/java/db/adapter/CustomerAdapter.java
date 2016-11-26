package db.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marc.truckdelivery.R;

import java.util.List;

import db.object.CustomerObject;

/**
 * Created by Helder on 25.11.2016.
 */

public class CustomerAdapter extends ArrayAdapter<CustomerObject>{

    private final Context context;
    private final List<CustomerObject> customers;

    public CustomerAdapter(Context context, List<CustomerObject> customers) {
        super(context,-1, customers);
        this.context = context;
        this.customers = customers;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout._row,parent,false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        String society = customers.get(position).getSociety();
        String locality = customers.get(position).getLocality();
        String nom_pre_loc = customers.get(position).getName()+" "+customers.get(position).getFirstname()+" "+customers.get(position).getLocality();


        if(society.equals("")){
            textView.setText(nom_pre_loc);
        }else{
            textView.setText(society +" " +locality);
        }

        return rowView;
    }
}
