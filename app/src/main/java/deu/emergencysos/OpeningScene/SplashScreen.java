package deu.emergencysos.OpeningScene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import LocalDb.DbHelper;
import deu.emergencysos.R;
import deu.emergencysos.UserInfoActivity;

public class SplashScreen extends AppCompatActivity {
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences settings = getSharedPreferences("SQL", 0);
        boolean firstTimeSplash = settings.getBoolean("firstTimeSplash", true);
        if (firstTimeSplash) {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("Splash", "Run içinde");
                    //Veri tabanını oluşturuyoruz.
                    dbHelper = new DbHelper(getApplicationContext());

                    Intent intent = new Intent(SplashScreen.this, UserInfoActivity.class);
                    startActivity(intent);
                    try {
                        this.finish();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

                public void finish() {
                    // TODO
                }

            }, 2000);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstTimeSplash", false);
            editor.commit();
        } else {
            Intent intent = new Intent(SplashScreen.this, UserInfoActivity.class);
            startActivity(intent);
        }
    }

}
