package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import db.DBContract;
import db.DBContract.DriverEntry;
import db.SQLiteHelper;
import db.object.DeliveryObject;
import db.object.DriverObject;

/**
 * Created by Marc on 15/11/2016.
 */

public class DriverDataSource {
    private SQLiteHelper sqldb;
    private SQLiteDatabase db;
    private Context context;

    public DriverDataSource(Context context){
        sqldb = SQLiteHelper.getInstance(context);
        this.context = context;

    }

    /**
     * Insert a new driver
     */
    public long createDriver(DriverObject driver){
        long id;
        db = sqldb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.DriverEntry.KEY_NAME, driver.getName());
        values.put(DBContract.DriverEntry.KEY_FIRSTNAME, driver.getFirstname());
        values.put(DBContract.DriverEntry.KEY_PHONE, driver.getPhone());
        values.put(DBContract.DriverEntry.KEY_PLATE, driver.getPlate());
        values.put(DBContract.DriverEntry.KEY_USER, driver.getNumTruck());
        values.put(DBContract.DriverEntry.KEY_PASSWORD, driver.getPassword());

        id = db.insert(DBContract.DriverEntry.TABLE_DRIVER, null, values);

        return id;
    }

    /**
     * Find one DriverObject by Id
     */
    public DriverObject getDriverById(long id){
        db = sqldb.getWritableDatabase();
        String sql = "SELECT * FROM " + DriverEntry.TABLE_DRIVER +
                " WHERE " + DriverEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        DriverObject driver = new DriverObject();
        driver.setId(cursor.getInt(cursor.getColumnIndex(DriverEntry.KEY_ID)));
        driver.setName(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_NAME)));
        driver.setFirstname(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_FIRSTNAME)));
        driver.setPhone(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PHONE)));
        driver.setPlate(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PLATE)));
        driver.setNumTruck(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_USER)));
        driver.setPassword(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PASSWORD)));

        return driver;
    }

    /**
     * Get all Drivers
     */
    public List<DriverObject> getAllDrivers(){
        db = sqldb.getReadableDatabase();
        List<DriverObject> drivers = new ArrayList<DriverObject>();
        String sql = "SELECT * FROM " + DriverEntry.TABLE_DRIVER + " ORDER BY " + DriverEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DriverObject driver = new DriverObject();
                driver.setId(cursor.getInt(cursor.getColumnIndex(DriverEntry.KEY_ID)));
                driver.setName(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_NAME)));
                driver.setFirstname(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_FIRSTNAME)));
                driver.setPhone(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PHONE)));
                driver.setPlate(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PLATE)));
                driver.setNumTruck(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_USER)));
                driver.setPassword(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PASSWORD)));

                drivers.add(driver);
            } while(cursor.moveToNext());
        }

        return drivers;
    }

    /**
     *  Update a DriverObject
     */
    public int updateDriver(DriverObject driver){
        db = sqldb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DriverEntry.KEY_NAME, driver.getName());
        values.put(DriverEntry.KEY_FIRSTNAME, driver.getFirstname());
        values.put(DriverEntry.KEY_PHONE, driver.getPhone());
        values.put(DriverEntry.KEY_PLATE, driver.getPlate());
        values.put(DriverEntry.KEY_USER, driver.getNumTruck());
        values.put(DriverEntry.KEY_PASSWORD, driver.getPassword());

        return this.db.update(DriverEntry.TABLE_DRIVER, values, DriverEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(driver.getId())});
    }

    /**
     * Search a DriverObject by name or firstname
     */
    public List<DriverObject> searchDriver(String query){
        db = sqldb.getWritableDatabase();
        List<DriverObject> drivers = new ArrayList<DriverObject>();
        String sql = "SELECT * FROM " + DriverEntry.TABLE_DRIVER +
                " WHERE " + DriverEntry.KEY_NAME + " LIKE '" + query+"%'"
                + " OR " + DriverEntry.KEY_FIRSTNAME + " LIKE '" + query+"%'"
                + " ORDER BY " + DriverEntry.KEY_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DriverObject driver = new DriverObject();
                driver.setId(cursor.getInt(cursor.getColumnIndex(DriverEntry.KEY_ID)));
                driver.setName(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_NAME)));
                driver.setFirstname(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_FIRSTNAME)));
                driver.setPhone(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PHONE)));
                driver.setPlate(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PLATE)));
                driver.setNumTruck(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_USER)));
                driver.setPassword(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PASSWORD)));

                drivers.add(driver);
            } while(cursor.moveToNext());
        }

        return drivers;
    }

    /**
     * return a driver by his username
     */
    public DriverObject getUser(String user)
    {
        db = sqldb.getWritableDatabase();
        String sql = "SELECT * FROM " + DriverEntry.TABLE_DRIVER +
                " WHERE " + DriverEntry.KEY_USER + " LIKE '" + user+"'";


        Cursor cursor = db.rawQuery(sql, null);

        DriverObject driver = new DriverObject();

       if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
           driver.setId(cursor.getInt(cursor.getColumnIndex(DriverEntry.KEY_ID)));
           driver.setName(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_NAME)));
           driver.setFirstname(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_FIRSTNAME)));
           driver.setPhone(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PHONE)));
           driver.setPlate(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PLATE)));
           driver.setNumTruck(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_USER)));
           driver.setPassword(cursor.getString(cursor.getColumnIndex(DriverEntry.KEY_PASSWORD)));
           return driver;
       }



        return null;
    }

    /**
     * Delete a Driver - this will also delete all Deliveries
     * for the Driver
     */
    public void deleteDriver(long id){
        db = sqldb.getWritableDatabase();
        DeliveryDataSource pra = new DeliveryDataSource(context);
        //get all records of the user
        List<DeliveryObject> deliveries = pra.getDeliveriesByDriverId(id);

        for(DeliveryObject delivery : deliveries){
            pra.deleteDelivery(delivery.getId());
        }

        //delete the person
        this.db.delete(DriverEntry.TABLE_DRIVER, DriverEntry.KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

    }

}
