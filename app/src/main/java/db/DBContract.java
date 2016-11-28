package db;

import android.provider.BaseColumns;

/**
 * Created by Marc on 05/11/2016.
 */

public class DBContract {

    public DBContract(){
        //empty constructor
        //should never be instantiated
    }

    public static abstract class DriverEntry implements BaseColumns{

        //Table name driver
        public static final String TABLE_DRIVER = "driver";

        //DriverObject Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_FIRSTNAME = "firstname";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_PLATE = "plate";
        public static final String KEY_USER = "user";
        public static final String KEY_PASSWORD = "password";

        //Table DriverObject create statement
        public static final String CREATE_TABLE_DRIVER = "CREATE TABLE "
                + TABLE_DRIVER + "("
                + DriverEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DriverEntry.KEY_NAME + " TEXT, "
                + DriverEntry.KEY_FIRSTNAME + " TEXT, "
                + DriverEntry.KEY_PHONE + " TEXT, "
                + DriverEntry.KEY_PLATE + " TEXT, "
                + DriverEntry.KEY_USER + " TEXT, "
                + DriverEntry.KEY_PASSWORD + " TEXT "
                + ");";
    }

    public static abstract class CustomerEntry implements BaseColumns{

        //Table name customer
        public static final  String TABLE_CUSTOMER = "customer";

        //CustomerObject Column names
        public static final String KEY_ID = "id";
        public static final String KEY_SOCIETY = "society";
        public static final String KEY_NAME = "name";
        public static final String KEY_FIRSTNAME = "firstname";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_ADRESS = "adress";
        public static final String KEY_POSTCODE = "postcode";
        public static final String KEY_LOCALITY = "locality";


        //Table CustomerObject create statement
        public static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE "
                + TABLE_CUSTOMER + "("
                + CustomerEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CustomerEntry.KEY_SOCIETY + " TEXT, "
                + CustomerEntry.KEY_NAME + " TEXT, "
                + CustomerEntry.KEY_FIRSTNAME + " TEXT, "
                + CustomerEntry.KEY_PHONE + " TEXT, "
                + CustomerEntry.KEY_ADRESS + " TEXT, "
                + CustomerEntry.KEY_POSTCODE + " INTEGER, "
                + CustomerEntry.KEY_LOCALITY + " TEXT "
                + ");";

    }

    public static abstract class DeliveryEntry implements BaseColumns{

        //Table name delivery
        public static final  String TABLE_DELIVERY = "delivery";

        //DeliveryObject Column names
        public static final String KEY_ID = "id";
        public static final String KEY_ID_DRIVER = "driverid";
        public static final String KEY_ID_CUSTOMER = "customerid";
        public static final String KEY_DATE = "date";
        public static final String KEY_QUANTITY = "quantity";
        public static final String KEY_CONDITIONING = "conditioning";
        public static final String KEY_ARTICLE = "article";


        //Table DeliveryObject create statement
        public static final String CREATE_TABLE_DELIVERY = "CREATE TABLE "
                + TABLE_DELIVERY + "("
                + DeliveryEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DeliveryEntry.KEY_ID_DRIVER + " INTEGER, "
                + DeliveryEntry.KEY_ID_CUSTOMER + " INTEGER, "
                + DeliveryEntry.KEY_DATE + " DATETIME, "
                + DeliveryEntry.KEY_QUANTITY + " INTEGER, "
                + DeliveryEntry.KEY_CONDITIONING + " TEXT, "
                + DeliveryEntry.KEY_ARTICLE + " TEXT, "
                + "FOREIGN KEY (" + KEY_ID_DRIVER + ") REFERENCES " + DriverEntry.TABLE_DRIVER + " (" + KEY_ID + "), "
                + "FOREIGN KEY (" + KEY_ID_CUSTOMER + ") REFERENCES " + CustomerEntry.TABLE_CUSTOMER + " (" + KEY_ID +") "
                + ");";

    }
}
