package LocalDb;


import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import baseClasses.Contact;
import deu.emergencysos.DeleteContactFragment;
import deu.emergencysos.EditContactFragment;

/**
 * Created by marchest on 3.12.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "emergencydb";
    public static final int DB_VERSION = 2;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //   db.execSQL(UserDb.getSql());
        db.execSQL(ContactDb.getSql());
        Log.v("DatabaseCr", "OLUSTU");
        //    db.execSQL(UserMessages.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactDb.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        // Foreign key desteğini açmak için
        super.onOpen(db);

    }

    public String addContactToLocalDb(String contactName, String contactSurname, String contactPhone, String contactEmail) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(ContactDb.COL_CONTACTNAME, contactName);
            values.put(ContactDb.COL_CONTACTSURNAME, contactSurname);
            values.put(ContactDb.COL_CONTACTPHONE, contactPhone);
            values.put(ContactDb.COL_CONTACTEMAIL, contactEmail);
            db.insert(ContactDb.TABLE_NAME, null, values);
            db.close();
            return "İşlem başarılı";
        } catch (SQLiteAbortException e) {
            e.printStackTrace();
            return "İşlem Başarısız";
        }

    }

    /*
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
        */
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        Log.e("DbHelpera", "while dısındal");
   /*     SQLiteDatabase db = this.getReadableDatabase();
        Log.e("getAllContacts","2");
        // String sqlQuery = "SELECT  * FROM " + TABLE_COUNTRIES;
        // Cursor cursor = db.rawQuery(sqlQuery, null);

        Cursor cursor = db.query(ContactDb.TABLE_NAME, new String[]{ContactDb.COL_CONTACTID, ContactDb.COL_CONTACTNAME,ContactDb.COL_CONTACTSURNAME, ContactDb.COL_CONTACTPHONE,ContactDb.COL_CONTACTEMAIL}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            //System.out.print("while içinde cursor");
            Log.e("DbHelper","while içinde");
            Contact contact = new Contact();
            contact.setContactId(cursor.getInt(0));
            contact.setContactName(cursor.getString(1));
            contact.setContactSurname(cursor.getString(2));
            contact.setPhone(cursor.getString(3));
            contact.setEmail(cursor.getString(4));
            contacts.add(contact);
        }
*/
        SQLiteDatabase db = this.getReadableDatabase();
        String getQuery = "SELECT * FROM " + ContactDb.TABLE_NAME;
        Cursor cursor = db.rawQuery(getQuery, null);
        Log.e("DbHelper", "while üstünde");
        while (cursor.moveToNext()) {
            //System.out.print("while içinde cursor");
            Log.e("DbHelper", "while içinde");
            Contact contact = new Contact();
            contact.setContactId(cursor.getInt(0));
            Log.v("ContactId", "" + cursor.getInt(0));
            contact.setContactName(cursor.getString(1));
            contact.setContactSurname(cursor.getString(2));
            contact.setPhone(cursor.getString(3));
            contact.setEmail(cursor.getString(4));
            contacts.add(contact);

            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact lhs, Contact rhs) {
                    return lhs.getContactName().compareTo(rhs.getContactName());
                }
            });
        }
        return contacts;
    }
    public boolean deleteContact(Contact contact){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ContactDb.TABLE_NAME+" WHERE "+ ContactDb.COL_CONTACTPHONE+"='"+contact.getPhone()+"'");
        return true;
    }

    public boolean updateContact(Contact contact, EditContactFragment fragment){
        String phone="";
        SQLiteDatabase db =this.getWritableDatabase();

        Bundle bundle=fragment.getArguments();
        if (bundle!=null){
             phone=bundle.getString("ContactPhone");

        }
        ContentValues values = new ContentValues();
        values.put(ContactDb.COL_CONTACTNAME, contact.getContactName());
        values.put(ContactDb.COL_CONTACTSURNAME, contact.getContactSurname());
        values.put(ContactDb.COL_CONTACTPHONE, contact.getPhone());
        values.put(ContactDb.COL_CONTACTEMAIL, contact.getEmail());
       // String updateQuery ="UPDATE "+ContactDb.TABLE_NAME+" SET "+ContactDb.COL_CONTACTNAME+" ="+contact.getContactName()+", "+ContactDb.COL_CONTACTSURNAME+" ="+contact.getContactSurname()+", "+ContactDb.COL_CONTACTPHONE+" ="+contact.getPhone()+", "+ContactDb.COL_CONTACTEMAIL+" ="+contact.getEmail()+" WHERE "+ContactDb.COL_CONTACTPHONE+" ="+phone;

       // db.execSQL(updateQuery);
        db.update(ContactDb.TABLE_NAME,values,ContactDb.COL_CONTACTPHONE+"="+phone,null);
        return true;
    }




    public String insertContactToSQLite(Contact contact) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ContactDb.COL_CONTACTNAME, contact.getContactName());
            values.put(ContactDb.COL_CONTACTSURNAME, contact.getContactSurname());
            values.put(ContactDb.COL_CONTACTPHONE, contact.getPhone());
            values.put(ContactDb.COL_CONTACTEMAIL, contact.getEmail());

            db.insert(ContactDb.TABLE_NAME, null, values);
            db.close();
            Log.e("insertIcınde", "insert basarılı");
            return "İşlem başarılı";
        } catch (SQLiteAbortException e) {
            e.printStackTrace();
            return "İşlem Başarısız";
        }

    }


}
