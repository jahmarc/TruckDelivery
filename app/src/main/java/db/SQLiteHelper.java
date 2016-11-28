package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import db.DBContract.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * Created by Marc on 05/11/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "truck.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper instance;

    //use a singleton
    //we want always just one instance of the database
    private SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static SQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new SQLiteHelper(context.getApplicationContext());
            //Enable foreign key support
            instance.db.execSQL("PRAGMA foreign_keys = ON;");
        }

        return instance;
    }

    //Creation of tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DriverEntry.CREATE_TABLE_DRIVER);
        db.execSQL(CustomerEntry.CREATE_TABLE_CUSTOMER);
        db.execSQL(DeliveryEntry.CREATE_TABLE_DELIVERY);
    }


    //Upgardes of tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + DriverEntry.TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS " + CustomerEntry.TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + DeliveryEntry.TABLE_DELIVERY);

        //create new tables
        onCreate(db);
    }

    /**
     //	 * get datetime as a string
     //	 * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
