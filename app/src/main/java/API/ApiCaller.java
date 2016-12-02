package API;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import Helper.AppStatic;

/**
 * Created by marchest on 30.11.2016.
 */
public class ApiCaller {
    public static JSONObject GET(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(false);
       // urlConnection.setRequestProperty("Authorization", "bearer " + AppStatic.TOKEN);
        urlConnection.setRequestMethod("GET");

        urlConnection.connect();

        int status = urlConnection.getResponseCode();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        //BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);
        JSONObject jObj = new JSONObject(jsonString);
        return jObj;
    }



    public static JSONObject POST(String uri, String json) {
        HttpURLConnection urlConnection;
        String url;
        String data = json;
        String result = "";
        try {
            //Connect
            urlConnection = (HttpURLConnection) ((new URL(uri).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
        //    urlConnection.setRequestProperty("Authorization", "bearer " + AppStatic.TOKEN);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();

            //Read
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            result = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
        }
        return jsonObject;
    }
}
