package deu.emergencysos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import API.AsyncClient;
import API.MyClient;
import Helper.OnTaskCompleted;
import baseClasses.UserModel;

public class UserInfoActivity extends AppCompatActivity {
    Button btnSave;
    TextView tvName, tvSurname, tvPhone, tvEmail;
    RadioGroup radioGroup;
    String userIdentity = "";
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //İlk yüklendikten sonra çalışacak bu activity de
        SharedPreferences settings = getSharedPreferences("UserActivity", 0);
        boolean firstTimeUserActivty = settings.getBoolean("firstTimeUserActivty", true);
        Log.e("firstTimeUserActivty", "" + firstTimeUserActivty);

        btnSave = (Button) findViewById(R.id.save_button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tvName = (TextView) findViewById(R.id.editTextName);
        tvSurname = (TextView) findViewById(R.id.editTextSurname);
        tvEmail = (TextView) findViewById(R.id.editTextEMail);
        tvPhone = (TextView) findViewById(R.id.editTextPhone);

        if (firstTimeUserActivty) {
            Log.e("UserInfoAc", "girdi");


            btnSave.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int genderId = radioGroup.getCheckedRadioButtonId();

                                String gender = getGender(genderId);
                                Log.e("GENDER", gender);
                                final UserModel userModel = new UserModel(tvName.getText().toString(), tvSurname.getText().toString(), tvEmail.getText().toString(), gender, tvPhone.getText().toString());
                                new MyClient().PostCourse(userModel);

                                new AsyncClient("GET", new OnTaskCompleted() {
                                    @Override
                                    public void onTaskCompleted(JSONObject result) {
                                        JSONArray jsonArray = null;
                                        String id = "";
                                        if (result == null) {
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
                                                Log.e("Bugun", jsonArray.getJSONObject(i).getString("userName"));
                                                String name = jsonArray.getJSONObject(i).getString("userName");
                                                String surname = jsonArray.getJSONObject(i).getString("userSurname");
                                                name = name.trim();
                                                surname = surname.trim();
                                                if (name.equalsIgnoreCase(userModel.getName().toString())) {
                                                    if (surname.equalsIgnoreCase(userModel.getSurName().toString())) {
                                                        id = jsonArray.getJSONObject(i).getString("userId");
                                                        userIdentity = id;
                                                        SharedPreferences preferences = getSharedPreferences("userServerId", MODE_PRIVATE);
                                                        SharedPreferences.Editor edit = preferences.edit();
                                                        edit.putString("USERID", userIdentity);
                                                        edit.commit();
                                                        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).execute("users", "");
                        /*        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor =preferences.edit();
                                editor.putString("userId",userIdentity);
                                editor.putString("userName",userModel.getName().toString());
                                editor.putString("userSurname",userModel.getSurName().toString());
                                editor.putString("userGender",userModel.getGender().toString());
                                editor.putString("userEmail",userModel.getEmail().toString());
                                editor.putString("userPhone",userModel.getPhoneNumber().toString());
                                editor.commit();
                                */
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e("threadin içinde", "içinde");

                                        SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        //      editor.putString("userId",userIdentity);
                                        //   Log.e("UserInfoId",userIdentity);
                                        editor.putString("userName", userModel.getName().toString());
                                        editor.putString("userSurname", userModel.getSurName().toString());
                                        editor.putString("userGender", userModel.getGender().toString());
                                        editor.putString("userEmail", userModel.getEmail().toString());
                                        editor.putString("userPhone", userModel.getPhoneNumber().toString());
                                        editor.commit();

                                    }
                                }).start();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                //   Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).start();
                    Toast.makeText(getApplicationContext(), "User has created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserInfoActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }
            });
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstTimeUserActivty", false);
            Log.i("firstTimeUserActivtySondaki", "" + firstTimeUserActivty);
            editor.commit();


        } else {
            Log.e("Else içinde", "else içinde");
            Intent intent = new Intent(UserInfoActivity.this, NavigationActivity.class);
            startActivity(intent);
        }


    }

    final public String getGender(int id) {
        String gender = "";
        switch (id) {
            case R.id.radioButton: {
                gender = "Male";
                break;
            }
            case R.id.radioButton2: {
                gender = "Female";
                break;
            }

        }
        Log.e("getGenderIcı", gender);
        return gender;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences("UserActivity", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstTimeUserActivty", false);
        //   Log.i("firstTimeUserActivtySondaki",""+firstTimeUserActivty);
        editor.commit();
    }
}
