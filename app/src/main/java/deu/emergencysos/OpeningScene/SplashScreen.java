package deu.emergencysos.OpeningScene;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import deu.emergencysos.MainActivity;
import deu.emergencysos.R;
import deu.emergencysos.UserInfoActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this, UserInfoActivity.class);
                startActivity(intent);
                try {
                    this.finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            public void finish(){
            // TODO
            }

        },2000);
    }
}
