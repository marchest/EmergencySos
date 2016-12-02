package baseClasses;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Özay on 22.10.2016.
 */

public class SMS_Service extends Service {



    int min = 1*60*1000;
    Handler handler = new Handler();

    SmsManager smsManager = SmsManager.getDefault();
    Intent intent;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){

        super.onCreate();
        Log.i("asda","Sms Servis onCreate");
        Toast.makeText(getApplicationContext(),"Service is started!",Toast.LENGTH_SHORT).show();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
                wl.acquire();
                Toast.makeText(getApplicationContext(),"15 sec",Toast.LENGTH_SHORT).show();

           //     smsManager.sendTextMessage("05428425349",null,"The Screen is Turned Off",null,null);
                smsManager.sendTextMessage("05428425349",null,""+intent.getExtras().get("coordinates"),null,null);

                handler.postDelayed(this, min);
                //wl.release();
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Service is over!",Toast.LENGTH_SHORT).show();

    }
}