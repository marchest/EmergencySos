package API;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Helper.AppStatic;
import Helper.OnTaskCompleted;

/**
 * Created by marchest on 30.11.2016.
 */

public class AsyncClient extends AsyncTask<String, Void, JSONObject> {
    private OnTaskCompleted listener;
    private String method;
    public JSONObject RESPONSE;

    public AsyncClient(String method, OnTaskCompleted listener) {
        this.listener = listener;
        this.method = method;
    }

    @Override
    protected void onPreExecute() {
        RESPONSE = null;
    }


    @Override
    protected void onPostExecute(JSONObject result) {
        RESPONSE = result;
        listener.onTaskCompleted(result);
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        return MakeRequest(args);
    }

    public JSONObject MakeRequest(String... args) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("StatusCode", 999);
        } catch (JSONException e) {
        }
        String op = args[0];
        String data = args[1];

        if (method == "GET") {
            try {
                jo = ApiCaller.GET(AppStatic.URL + op + data);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                try {
                    jo.put("ErrorMessage", e.getMessage());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (method == "POST") {
            jo = ApiCaller.POST(AppStatic.URL + op, data);
        }
        return jo;
    }
}
