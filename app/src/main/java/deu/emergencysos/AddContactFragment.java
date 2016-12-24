package deu.emergencysos;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.lang.reflect.Method;

import API.MyClient;
import LocalDb.DbHelper;
import baseClasses.Contact;
import baseClasses.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {

    public Contact c1 = new Contact();
    public Contact c2 = new Contact();
    EditText etName, etSurname, etPhone, etEmail;
    DbHelper dbHelper;
    Button button;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact,
                container, false);
        button = (Button) view.findViewById(R.id.add_button);
        etName = (EditText) view.findViewById(R.id.editTextName);
        etSurname = (EditText) view.findViewById(R.id.editTextSurname);
        etPhone = (EditText) view.findViewById(R.id.editTextPhone);
        etEmail = (EditText) view.findViewById(R.id.editTextEMail);
        dbHelper =new DbHelper(getActivity());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    int a = dbHelper.getAllContacts().size();
                    Log.e("ContactSize", "" + a);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                try {
                    if (dbHelper.getAllContacts() == null || dbHelper.getAllContacts().size() <= 5) {
                        //    dbHelper.addContactToLocalDb(etName.getText().toString(),etSurname.getText().toString(),etPhone.getText().toString(),etEmail.getText().toString());
                        // Bu server için

                        Log.e("edit text icerigi", etName.getText().toString());
                        c1.setContactName(etName.getText().toString());
                        c1.setContactSurname(etSurname.getText().toString());
                        c1.setPhone(etPhone.getText().toString());
                        Log.e("kullanıcıId", "" + getUserServerId());
                        c1.setUserId(getUserServerId());

                        Log.e("AddContactFragment", "icinde");
                        c2.setContactName(etName.getText().toString());
                        c2.setContactSurname(etSurname.getText().toString());
                        c2.setPhone(etPhone.getText().toString());
                        c2.setEmail(etEmail.getText().toString());
                        // c2.setUserId(getUserId());
                        dbHelper.insertContactToSQLite(c2);
                        try {
                            new MyClient().PostContact(c1);

                            Contact c = dbHelper.getAllContacts().get(0);
                            Log.e("isim", c.getContactName());
                            Log.e("AddContactFragmentPost", "postEdildi");
                            Intent newIntent = new Intent(getActivity().getApplicationContext(), NavigationActivity.class);
                            startActivity(newIntent);
                        } catch (JSONException e) {
                            Toast.makeText(getActivity().getApplicationContext(), "You can not add contact more than 5", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "You can not add contact more than 5", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

    public int getUserId() {

        int a = 0;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        a = Integer.parseInt(preferences.getString("userId", null));
        return a;

    }

    private int getUserServerId() {
        int a = 0;
        SharedPreferences preferences = getActivity().getSharedPreferences("userServerId", getActivity().MODE_PRIVATE);

        String userID = preferences.getString("USERID", "");
        Log.e("parse", userID);
        a = Integer.parseInt(userID);
        return a;

    }

}
