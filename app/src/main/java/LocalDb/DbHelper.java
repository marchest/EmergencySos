package LocalDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import baseClasses.Contact;

/**
 * Created by marchest on 3.12.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "emergencydb";
    public static final int DB_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL(UserDb.getSql());
        db.execSQL(ContactDb.getSql());
    //    db.execSQL(UserMessages.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ContactDb.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        // Foreign key desteğini açmak için
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public String addContactToLocalDb(String contactName,String contactSurname,String contactPhone){
      try {
          SQLiteDatabase db=this.getWritableDatabase();
          ContentValues values =new ContentValues();
          values.put(ContactDb.COL_CONTACTNAME,contactName);
          values.put(ContactDb.COL_CONTACTSURNAME,contactSurname);
          values.put(ContactDb.COL_CONTACTPHONE,contactPhone);
          db.insert(ContactDb.TABLE_NAME,null,values);
          db.close();
          return "İşlem başarılı";
      }
      catch (SQLiteAbortException e){
          e.printStackTrace();
          return "İşlem Başarısız";
      }

    }

    public  ArrayList<HashMap<String,String>> getAllContact(){

        ArrayList<HashMap<String,String>> contactList =new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db =this.getReadableDatabase();
        String getQuery ="SELECT * FROM " +ContactDb.TABLE_NAME;
        Cursor cursor =db.rawQuery(getQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String,String> map=new HashMap<String,String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i),cursor.getString(i));
                }
                contactList.add(map);
            }while(cursor.moveToNext());
        }
        return contactList;
    }


}
