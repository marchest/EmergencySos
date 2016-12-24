package deu.emergencysos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import API.AsyncClient;
import API.MyClient;
import Helper.AppStatic;
import Helper.OnTaskCompleted;
import baseClasses.Contact;
import baseClasses.UserModel;

public class postActivity extends AppCompatActivity {
    Button buttonPost;
    Button postContact;
    Button getUserId;
    //{contactName:"Kagan",contactSurname:"Sarıca",contactPhone:"05364565669",userId:5}
    Contact contact = new Contact("name234", "surname234", "05451234578", 5);
    UserModel um = new UserModel("sondeneme", "sonn","son@gmail.com", "Male","999999");
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        buttonPost = (Button) findViewById(R.id.buttonPost);
        postContact = (Button) findViewById(R.id.postContact);
        getUserId = (Button) findViewById(R.id.getUserId);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    new MyClient().PostCourse(um);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        postContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new MyClient().PostContact(contact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //Contact ekleyebilmemiz için userın id sine ihtiyacımız vardı bunun için gerekli olan seyler yapıldı.

        getUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncClient("GET", new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(JSONObject result) {
                        JSONArray jsonArray =null;
                        String id="";
                        if(result == null){
                            Toast.makeText(getApplicationContext(), "result is null", Toast.LENGTH_SHORT).show();
                        }
                        try {
                       //     int code = result.getInt("StatusCode");
                     //       if (code == 999) {
                     //           Toast.makeText(getApplicationContext(), "999 ERR", Toast.LENGTH_SHORT).show();
                    //        } else {
                                //jsonArray = result.getJSONArray("Result");
                                jsonArray = result.getJSONArray("arrayname");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.e("Bugun",jsonArray.getJSONObject(i).getString("userName"));
                                        String name= jsonArray.getJSONObject(i).getString("userName");
                                    name=name.trim();
                                    if(name.equalsIgnoreCase("Badem")) {
                                         id = jsonArray.getJSONObject(i).getString("userId");
                                        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                                    }

                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute("users","");
            }
        });
    }
}
