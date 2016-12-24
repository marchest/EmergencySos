package LocalDb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import okhttp3.internal.Util;

/**
 * Created by marchest on 3.12.2016.
 */
public class UserDb {

    public static final String TABLE_NAME="user";
    public static final String COL_USERID="user_id";
    public static final String COL_USERNAME="user_name";
    public static final String COL_USERSURNAME="user_surname";
    public static final String COL_USEREMAIL="user_email";
    public static final String COL_USERPHONE="user_phone";
    public static final String COL_USERGENDER="user_gender";


    static String getSql(){
         String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME + "("
                +COL_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_USERNAME + " TEXT,"
                + COL_USERSURNAME + " TEXT,"
                +COL_USEREMAIL+"TEXT,"
                +COL_USERPHONE +"TEXT,"
                +COL_USERGENDER +"TEXT"
                + ")";

        return CREATE_TABLE_USER;
    }
  /*  long update(SQLiteDatabase db){

        ContentValues cv=new ContentValues();

    }
*/

}
