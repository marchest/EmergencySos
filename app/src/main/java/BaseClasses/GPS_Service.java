package baseClasses;

/**
 * Created by marchest on 22.10.2016.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class GPS_Service extends Service {
    int min = 1*60*1000;
    Handler handler = new Handler();

    SmsManager smsManager = SmsManager.getDefault();
    private LocationListener listener;
    private LocationManager locationManager;
   static String locationInfo="";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("coordinates", location.getLongitude() + " " + location.getLatitude());
                locationInfo = " "+location.getLatitude() +" "+location.getLongitude();
                sendBroadcast(i);
                Log.e("location",locationInfo);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, min/3, 0, listener);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
                wl.acquire();
                Toast.makeText(getApplicationContext(),"15 sec",Toast.LENGTH_SHORT).show();

                //     smsManager.sendTextMessage("05428425349",null,"The Screen is Turned Off",null,null);
                try {
                    smsManager.sendTextMessage("05428425349",null,locationInfo,null,null);
                }
                catch (Exception e){
                    e.printStackTrace();
                }



                handler.postDelayed(this, min);
                //wl.release();
            }
        };
        handler.post(runnable);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }
}