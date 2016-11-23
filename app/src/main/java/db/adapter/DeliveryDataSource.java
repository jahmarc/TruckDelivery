package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import db.DBContract.DeliveryEntry;
import db.SQLiteHelper;
import db.object.DeliveryObject;

/**
 * Created by Marc on 15/11/2016.
 */

public class DeliveryDataSource {
    private SQLiteHelper sqldb;
    private SQLiteDatabase db;
    Context context;

    public DeliveryDataSource(Context context){
        sqldb = SQLiteHelper.getInstance(context);
        this.context = context;
    }

    /**
     * Insert a new DeliveryObject
     */
    public long createDelivery(DeliveryObject delivery){
        long id;
        db = sqldb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DeliveryEntry.KEY_ID_DRIVER, delivery.getDriverid());
        values.put(DeliveryEntry.KEY_ID_CUSTOMER, delivery.getCustomerid());
        values.put(DeliveryEntry.KEY_DATE, delivery.getDate());
        values.put(DeliveryEntry.KEY_QUANTITY, delivery.getQuantity());
        values.put(DeliveryEntry.KEY_CONDITIONING, delivery.getConditioning());
        values.put(DeliveryEntry.KEY_ARTICLE, delivery.getArticle());

        id = db.insert(DeliveryEntry.TABLE_DELIVERY, null, values);

        return id;
    }

    /**
     * Fin one delivery with id
     */
    public DeliveryObject getDeliveryById(long id){
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        DeliveryObject delivery = new DeliveryObject();

        delivery.setId(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID)));
        delivery.setDriverid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_DRIVER)));
        delivery.setCustomerid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_CUSTOMER)));
        delivery.setDate(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_DATE)));
        delivery.setQuantity(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_QUANTITY)));
        delivery.setConditioning(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_CONDITIONING)));
        delivery.setArticle(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_ARTICLE)));


        return delivery;
    }

    /**
     * Get all deliveries
     */
    public List<DeliveryObject> getAllDeliveries(){
        db = sqldb.getReadableDatabase();
        List<DeliveryObject> deliveries = new ArrayList<DeliveryObject>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DeliveryObject delivery = new DeliveryObject();

                delivery.setId(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID)));
                delivery.setDriverid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_DRIVER)));
                delivery.setCustomerid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_CUSTOMER)));
                delivery.setDate(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_DATE)));
                delivery.setQuantity(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_QUANTITY)));
                delivery.setConditioning(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_CONDITIONING)));
                delivery.setArticle(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_ARTICLE)));

                deliveries.add(delivery);
            } while(cursor.moveToNext());
        }

        return deliveries;
    }

    /**
     * Update a delivery
     */
    public int updateDelivery(DeliveryObject delivery){
        ContentValues values = new ContentValues();
        values.put(DeliveryEntry.KEY_ID_DRIVER, delivery.getDriverid());
        values.put(DeliveryEntry.KEY_ID_CUSTOMER, delivery.getCustomerid());
        values.put(DeliveryEntry.KEY_DATE, delivery.getDate());
        values.put(DeliveryEntry.KEY_QUANTITY, delivery.getQuantity());
        values.put(DeliveryEntry.KEY_CONDITIONING, delivery.getConditioning());
        values.put(DeliveryEntry.KEY_ARTICLE, delivery.getArticle());

        return this.db.update(DeliveryEntry.TABLE_DELIVERY, values, DeliveryEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(delivery.getId())});
    }

    /**
     * Search a DeliveryObject by date
     */
    public List<DeliveryObject> searchDeliveries(String date){
        List<DeliveryObject> deliveries = new ArrayList<DeliveryObject>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_DATE + " = " + date
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DeliveryObject delivery = new DeliveryObject();

                delivery.setId(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID)));
                delivery.setDriverid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_DRIVER)));
                delivery.setCustomerid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_CUSTOMER)));
                delivery.setDate(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_DATE)));
                delivery.setQuantity(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_QUANTITY)));
                delivery.setConditioning(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_CONDITIONING)));
                delivery.setArticle(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_ARTICLE)));

                deliveries.add(delivery);
            } while(cursor.moveToNext());
        }

        return deliveries;
    }

    /**
     * Search deliveries by CustomerID
     */
    public List<DeliveryObject> getDeliveriesByCustomerId(long id){
        List<DeliveryObject> deliveries = new ArrayList<DeliveryObject>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID_CUSTOMER + " = " + id
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DeliveryObject delivery = new DeliveryObject();

                delivery.setId(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID)));
                delivery.setDriverid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_DRIVER)));
                delivery.setCustomerid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_CUSTOMER)));
                delivery.setDate(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_DATE)));
                delivery.setQuantity(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_QUANTITY)));
                delivery.setConditioning(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_CONDITIONING)));
                delivery.setArticle(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_ARTICLE)));

                deliveries.add(delivery);
            } while(cursor.moveToNext());
        }

        return deliveries;
    }

    /**
     * Search deliveries by DriverID
     */
    public List<DeliveryObject> getDeliveriesByDriverId(long id){
        List<DeliveryObject> deliveries = new ArrayList<DeliveryObject>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID_DRIVER + " = " + id
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DeliveryObject delivery = new DeliveryObject();

                delivery.setId(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID)));
                delivery.setDriverid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_DRIVER)));
                delivery.setCustomerid(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_ID_CUSTOMER)));
                delivery.setDate(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_DATE)));
                delivery.setQuantity(cursor.getInt(cursor.getColumnIndex(DeliveryEntry.KEY_QUANTITY)));
                delivery.setConditioning(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_CONDITIONING)));
                delivery.setArticle(cursor.getString(cursor.getColumnIndex(DeliveryEntry.KEY_ARTICLE)));

                deliveries.add(delivery);
            } while(cursor.moveToNext());
        }

        return deliveries;
    }

}
