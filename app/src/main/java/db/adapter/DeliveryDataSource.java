package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import db.DBContract.DeliveryEntry;
import db.SQLiteHelper;
import db.object.Delivery;

/**
 * Created by Marc on 15/11/2016.
 */

public class DeliveryDataSource {
    private SQLiteDatabase db;
    Context context;

    public DeliveryDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new Delivery
     */
    public long createDelivery(Delivery delivery){
        long id;
        ContentValues values = new ContentValues();
        values.put(DeliveryEntry.KEY_ID_DRIVER, delivery.getDriverid());
        values.put(DeliveryEntry.KEY_ID_CUSTOMER, delivery.getCustomerid());
        values.put(DeliveryEntry.KEY_DATE, delivery.getDate());
        values.put(DeliveryEntry.KEY_QUANTITY, delivery.getQuantity());
        values.put(DeliveryEntry.KEY_CONDITIONING, delivery.getConditioning());
        values.put(DeliveryEntry.KEY_ARTICLE, delivery.getArticle());

        id = this.db.insert(DeliveryEntry.TABLE_DELIVERY, null, values);

        return id;
    }

    /**
     * Fin one delivery with id
     */
    public Delivery getDeliveryById(long id){
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Delivery delivery = new Delivery();

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
    public List<Delivery> getAllDeliveries(){
        List<Delivery> deliveries = new ArrayList<Delivery>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Delivery delivery = new Delivery();

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
    public int updateDelivery(Delivery delivery){
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
     * Search a Delivery by date
     */
    public List<Delivery> searchDeliveries(String date){
        List<Delivery> deliveries = new ArrayList<Delivery>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_DATE + " = " + date
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Delivery delivery = new Delivery();

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
    public List<Delivery> getDeliveriesByCustomerId(long id){
        List<Delivery> deliveries = new ArrayList<Delivery>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID_CUSTOMER + " = " + id
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Delivery delivery = new Delivery();

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
    public List<Delivery> getDeliveriesByDriverId(long id){
        List<Delivery> deliveries = new ArrayList<Delivery>();
        String sql = "SELECT * FROM " + DeliveryEntry.TABLE_DELIVERY +
                " WHERE " + DeliveryEntry.KEY_ID_DRIVER + " = " + id
                + " ORDER BY " + DeliveryEntry.KEY_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Delivery delivery = new Delivery();

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
