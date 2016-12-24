package deu.emergencysos;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import LocalDb.DbHelper;
import baseClasses.Contact;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteContactFragment extends Fragment {
DbHelper dbHelper;

    public DeleteContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_contact,
                container, false);
        dbHelper =new DbHelper(getActivity());
        ListView clickedListView = (ListView) view.findViewById(R.id.listView);//////buraya listview tıklanan eleman id gelecek
        try{
            if(dbHelper.getAllContacts() != null){
             final  Contact [] c ;
                c =dbHelper.getAllContacts().toArray(new Contact[dbHelper.getAllContacts().size()]);

                ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,objToStr(c));
                clickedListView.setAdapter(adapter);
                clickedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setCancelable(true);
                        builder.setTitle(R.string.delete_contact_message);
                        builder.setInverseBackgroundForced(true);
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                            }
                        });

                        builder.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dbHelper.deleteContact(c[position]);
                                Toast.makeText(getActivity(),"Contact has deleted",Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(getActivity().getApplicationContext(),NavigationActivity.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                    }
                });
                clickedListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Fragment newFragment = new EditContactFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("ContactName",c[position].getContactName());
                        bundle.putString("ContactSurName",c[position].getContactSurname());
                        bundle.putString("ContactPhone",c[position].getPhone());
                        bundle.putString("ContactEmail",c[position].getEmail());
                        newFragment.setArguments(bundle);
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return false;
                    }
                });
            }
        }catch (NullPointerException e){
            Toast.makeText(getActivity().getApplicationContext(), "Contact has not added yet", Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    public String[] objToStr(Contact[] con) {
        String[] arr = new String[con.length];
        for (int i = 0; i < con.length; i++) {
            arr[i] = con[i].getContactName() + "  " + con[i].getContactSurname() + "\n" + con[i].getPhone() + "  Mail: " + con[i].getEmail();
        }
        return arr;
    }

}
