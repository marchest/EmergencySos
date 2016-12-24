package deu.emergencysos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import LocalDb.DbHelper;
import baseClasses.Contact;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditContactFragment extends Fragment {
EditText editTextPhone,editTextEMail,editTextName,editTextSurname;
    Button button;
    DbHelper dbHelper;
    public EditContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contact,
                container, false);
        dbHelper=new DbHelper(getActivity());
         button = (Button) view.findViewById(R.id.edit_button);
        editTextName =(EditText)view.findViewById(R.id.editTextName);
        editTextSurname=(EditText)view.findViewById(R.id.editTextSurname);
        editTextEMail=(EditText)view.findViewById(R.id.editTextEMail);
        editTextPhone=(EditText)view.findViewById(R.id.editTextPhone);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            editTextName.setText(bundle.getString("ContactName",""));
            editTextSurname.setText(bundle.getString("ContactSurName",""));
            editTextPhone.setText(bundle.getString("ContactPhone",""));
            editTextEMail.setText(bundle.getString("ContactEmail",""));

        }

        else{
            Intent i=new Intent(getActivity().getApplicationContext(),NavigationActivity.class);
            startActivity(i);

        }
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Contact contactToEdit=new Contact(editTextName.getText().toString(),editTextSurname.getText().toString(),editTextPhone.getText().toString(),editTextEMail.getText().toString());
                dbHelper.updateContact(contactToEdit,EditContactFragment.this);
                Toast.makeText(getActivity().getApplicationContext(),"Contact updated",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity().getApplicationContext(),NavigationActivity.class);
                startActivity(i);
            }
        });
        return view;
    }


}
