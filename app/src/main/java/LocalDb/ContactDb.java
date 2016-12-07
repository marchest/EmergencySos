package LocalDb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import baseClasses.Contact;

/**
 * Created by marchest on 3.12.2016.
 */
public class ContactDb {

    public static final String TABLE_NAME="contact";
    public static final String COL_CONTACTID="contact_id";
    public static final String COL_CONTACTNAME="contact_name";
    public static final String COL_CONTACTSURNAME="contact_surname";
    public static final String COL_CONTACTPHONE="contact_phone";
   // public static final String COL_USERID="userID";

    /// http://mrbool.com/how-to-use-sqlite-databases-in-android/27043 burda örnek var
    static String getSql(){
        String CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_NAME + "("
                +COL_CONTACTID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_CONTACTNAME + " TEXT,"
                + COL_CONTACTSURNAME + " TEXT,"
                +COL_CONTACTPHONE +"TEXT"
             //   +COL_USERID +"INTEGER NOT NULL"
                + ")";

        return CREATE_TABLE_CONTACT;
    }





}
