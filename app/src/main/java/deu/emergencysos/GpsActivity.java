package deu.emergencysos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import BaseClasses.GpsTracker;

public class GpsActivity extends AppCompatActivity {

    Button btnShowLocation;
    String latitude;
    String longitude;
    GpsTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityGps);

        btnShowLocation = (Button) findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GpsTracker gps = new GpsTracker(GpsActivity.this);
                    if (gps.canGetLocation()) {
                        latitude = Double.toString(gps.getLatitude());
                        longitude = Double.toString(gps.getLongitude());
                        Toast.makeText(
                                getApplicationContext(),
                                "Your Location is -\nLat: " + latitude + "\nLong: "
                                        + longitude, Toast.LENGTH_LONG).show();
                        // \n is for new line
                    } else {
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }

            }
        });
    }
}
