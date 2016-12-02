package deu.emergencysos;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import baseClasses.GPS_Service;
import baseClasses.SMS_Service;


public class MainActivity extends AppCompatActivity {
     boolean service_situtations = false;
    private Button btn_start, btn_stop;
    private TextView textView;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    textView.append("\n" + intent.getExtras().get("coordinates"));

                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.button);
        btn_stop = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);

        if (!runtime_permissions()&&smsRunTimePermissions()){
            enable_buttons();
        }

        else{
            enable_buttons();
        }
    }

    private void enable_buttons() {

            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent gpsIntent = new Intent(getApplicationContext(), GPS_Service.class);
                 //   Intent smsIntent = new Intent(getApplicationContext(), SMS_Service.class);
//                    startService(smsIntent);
                    startService(gpsIntent);
                    service_situtations = true;

                }
            });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gpsIntent = new Intent(getApplicationContext(), GPS_Service.class);
                stopService(gpsIntent);

            }
        });

        }




    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 16 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

            return true;
        }
        return false;
    }

    public boolean smsRunTimePermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return true;
        }
        return false;
    }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    enable_buttons();
                } else {
                    runtime_permissions();
                }
            }
        }
    }
