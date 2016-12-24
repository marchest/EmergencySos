package deu.emergencysos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import API.MyClient;
import LocalDb.DbHelper;
import baseClasses.Contact;

public class Silbunu extends AppCompatActivity {
    Button button, buttongetir;
    TextView textView;
    DbHelper dbHelper;
    public Contact c1 = new Contact();
    public Contact c2 = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //////////////////////////////////////////////////////// SORUNSUZ KAYIT OLUSTU GETİRİLKDİ
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silbunu);
        dbHelper = new DbHelper(getApplicationContext());
        buttongetir = (Button) findViewById(R.id.button4);
        button = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int a = dbHelper.getAllContacts().size();
                    Log.e("ContactSize", "" + a);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                if (dbHelper.getAllContacts() == null || dbHelper.getAllContacts().size() < 5) {
                    //    dbHelper.addContactToLocalDb(etName.getText().toString(),etSurname.getText().toString(),etPhone.getText().toString(),etEmail.getText().toString());
                    // Bu server için
                    Log.e("Burayagirdi", "gridad");
                    c1.setContactName("russiadeneme");
                    c1.setContactSurname("amk");
                    c1.setPhone("1234123");
                    c1.setEmail("adasdasd");

                    // c2.setUserId(getUserId());
                    dbHelper.insertContactToSQLite(c1);

                } else {
                    Toast.makeText(getApplicationContext(), "You can not add contact more than 5", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttongetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact c = dbHelper.getAllContacts().get(0);
                Log.e("Getirİcinde", "icinde");
                boolean isNull = (dbHelper.getAllContacts() == null) ? true : false;
                Toast.makeText(getApplicationContext(), "" + isNull, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), c.getContactName(), Toast.LENGTH_LONG).show();
                textView.setText(c.getContactName());
            }
        });
    }
}
