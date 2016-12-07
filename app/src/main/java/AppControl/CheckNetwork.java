package AppControl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by marchest on 29.11.2016.
 */
public class CheckNetwork {

    public boolean isConnected(Context context){

        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }

    }
/*
    public boolean isConnected() {
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED &&
                (!ForexWiz.isUseWifi() || netInfo.getType()==ConnectivityManager.TYPE_WIFI);
    }*/
}
