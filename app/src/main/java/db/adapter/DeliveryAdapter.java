package db.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marc.truckdelivery.R;

import java.util.List;

import db.object.DeliveryObject;


/**
 * Created by Helder on 23.11.2016.
 */

public class DeliveryAdapter extends ArrayAdapter<DeliveryObject> {

    private final Context context;
    private final List<DeliveryObject> deliveries;

    public DeliveryAdapter(Context context, List<DeliveryObject> deliveries) {
        super(context,-1, deliveries);
        this.context = context;
        this.deliveries = deliveries;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout._row,parent,false);
        TextView textView = (TextView)rowView.findViewById(R.id.label);
        textView.setText("N°: "+deliveries.get(position).getId() +" | "+deliveries.get(position).getDate()+" / " + deliveries.get(position).getQuantity()+" "+ deliveries.get(position).getConditioning());

        return rowView;
    }
}