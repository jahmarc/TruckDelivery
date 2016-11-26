package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import db.DBContract.CustomerEntry;
import db.SQLiteHelper;
import db.object.CustomerObject;
import db.object.DeliveryObject;

/**
 * Created by Marc on 15/11/2016.
 */

public class CustomerDataSource {

    private SQLiteHelper sqldb;
    private SQLiteDatabase db;
    private Context context;

    public CustomerDataSource(Context context){
        sqldb = SQLiteHelper.getInstance(context);
        this.context = context;
    }

    /**
     * Insert a new customer
     */
    public long createCustomer(CustomerObject customer){
        long id;
        db = sqldb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerEntry.KEY_SOCIETY, customer.getSociety());
        values.put(CustomerEntry.KEY_NAME, customer.getName());
        values.put(CustomerEntry.KEY_FIRSTNAME, customer.getFirstname());
        values.put(CustomerEntry.KEY_PHONE, customer.getPhone());
        values.put(CustomerEntry.KEY_ADRESS, customer.getAdress());
        values.put(CustomerEntry.KEY_POSTCODE, customer.getPostcode());
        values.put(CustomerEntry.KEY_LOCALITY, customer.getLocality());

        id = db.insert(CustomerEntry.TABLE_CUSTOMER, null, values);

        return id;
    }

    /**
     * Find one CustomerObject by Id
     */
    public CustomerObject getCustomerById(long id){
        db = sqldb.getWritableDatabase();
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER +
                " WHERE " + CustomerEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        CustomerObject customer = new CustomerObject();

        customer.setId(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_ID)));
        customer.setSociety(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_SOCIETY)));
        customer.setName(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_NAME)));
        customer.setFirstname(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_FIRSTNAME)));
        customer.setPhone(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_PHONE)));
        customer.setAdress(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_ADRESS)));
        customer.setPostcode(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_POSTCODE)));
        customer.setLocality(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_LOCALITY)));

        return customer;
    }

    /**
     * Get all Customers
     */
    public List<CustomerObject> getAllCustomers(){
        db = sqldb.getWritableDatabase();
        List<CustomerObject> customers = new ArrayList<CustomerObject>();
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER + " ORDER BY " + CustomerEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                CustomerObject customer = new CustomerObject();

                customer.setId(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_ID)));
                customer.setSociety(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_SOCIETY)));
                customer.setName(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_NAME)));
                customer.setFirstname(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_FIRSTNAME)));
                customer.setPhone(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_PHONE)));
                customer.setAdress(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_ADRESS)));
                customer.setPostcode(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_POSTCODE)));
                customer.setLocality(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_LOCALITY)));

                customers.add(customer);
            } while(cursor.moveToNext());
        }

        return customers;
    }

    /**
     *  Update a CustomerObject
     */
    public int updateCustomer(CustomerObject customer){
        db = sqldb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerEntry.KEY_SOCIETY, customer.getSociety());
        values.put(CustomerEntry.KEY_NAME, customer.getName());
        values.put(CustomerEntry.KEY_FIRSTNAME, customer.getFirstname());
        values.put(CustomerEntry.KEY_PHONE, customer.getPhone());
        values.put(CustomerEntry.KEY_ADRESS, customer.getAdress());
        values.put(CustomerEntry.KEY_POSTCODE, customer.getPostcode());
        values.put(CustomerEntry.KEY_LOCALITY, customer.getLocality());



        return this.db.update(CustomerEntry.TABLE_CUSTOMER, values, CustomerEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(customer.getId())});
    }

    /**
     * Search A list of customer with name
     */
    public List<CustomerObject> searchCustomer(String query){
        db = sqldb.getWritableDatabase();
        List<CustomerObject> customers = new ArrayList<CustomerObject>();
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER +
                " WHERE " + CustomerEntry.KEY_SOCIETY + " LIKE '" + query+"%'"
                + " OR " + CustomerEntry.KEY_NAME + " LIKE '" + query+"%'"
                + " OR " + CustomerEntry.KEY_FIRSTNAME + " LIKE '" + query+"%'"
                + " ORDER BY " + CustomerEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                CustomerObject customer = new CustomerObject();

                customer.setId(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_ID)));
                customer.setSociety(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_SOCIETY)));
                customer.setName(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_NAME)));
                customer.setFirstname(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_FIRSTNAME)));
                customer.setPhone(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_PHONE)));
                customer.setAdress(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_ADRESS)));
                customer.setPostcode(cursor.getInt(cursor.getColumnIndex(CustomerEntry.KEY_POSTCODE)));
                customer.setLocality(cursor.getString(cursor.getColumnIndex(CustomerEntry.KEY_LOCALITY)));

                customers.add(customer);
            } while(cursor.moveToNext());
        }

        return customers;
    }

    /**
     * Delete a Customer - this will also delete all Deliveries
     * for the Driver
     */
    public void deleteCustomer(long id){
        db = sqldb.getWritableDatabase();
        DeliveryDataSource pra = new DeliveryDataSource(context);
        //get all records of the user
        List<DeliveryObject> deliveries = pra.getDeliveriesByCustomerId(id);

        for(DeliveryObject delivery : deliveries){
            pra.deleteDelivery(delivery.getId());
        }

        //delete the person
        this.db.delete(CustomerEntry.TABLE_CUSTOMER, CustomerEntry.KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

    }
}
