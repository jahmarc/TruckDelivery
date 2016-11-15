package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import db.DBContract.CustomerEntry;
import db.SQLiteHelper;
import db.object.Customer;
/**
 * Created by Marc on 15/11/2016.
 */

public class CustomerDataSource {

    private SQLiteDatabase db;
    private Context context;

    public CustomerDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new customer
     */
    public long createCustomer(Customer customer){
        long id;
        ContentValues values = new ContentValues();
        values.put(CustomerEntry.KEY_SOCIETY, customer.getSociety());
        values.put(CustomerEntry.KEY_NAME, customer.getName());
        values.put(CustomerEntry.KEY_FIRSTNAME, customer.getFirstname());
        values.put(CustomerEntry.KEY_PHONE, customer.getPhone());
        values.put(CustomerEntry.KEY_ADRESS, customer.getAdress());
        values.put(CustomerEntry.KEY_POSTCODE, customer.getPostcode());
        values.put(CustomerEntry.KEY_LOCALITY, customer.getLocality());

        id = this.db.insert(CustomerEntry.TABLE_CUSTOMER, null, values);

        return id;
    }

    /**
     * Find one Customer by Id
     */
    public Customer getCustomerById(long id){
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER +
                " WHERE " + CustomerEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Customer customer = new Customer();

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
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER + " ORDER BY " + CustomerEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Customer customer = new Customer();

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
     *  Update a Customer
     */
    public int updateCustomer(Customer customer){
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
    public List<Customer> searchCustomer(String query){
        List<Customer> customers = new ArrayList<Customer>();
        String sql = "SELECT * FROM " + CustomerEntry.TABLE_CUSTOMER +
                " WHERE " + CustomerEntry.KEY_SOCIETY + " = " + query
                + " OR " + CustomerEntry.KEY_NAME + " = " + query
                + " OR " + CustomerEntry.KEY_FIRSTNAME + " = " + query
                + " ORDER BY " + CustomerEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Customer customer = new Customer();

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
}
