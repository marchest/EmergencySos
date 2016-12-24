package API;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Helper.AppStatic;
import Helper.OnTaskCompleted;
import baseClasses.Contact;
import baseClasses.UserModel;
import retrofit2.http.HTTP;


/**
 * Created by marchest on 29.11.2016.
 */
public class MyClient {
    public static String callWebService(String wcfUrl,JSONObject jsonObject) {

        String jsonString = "";
    /*    try {
            //Bağlantıyı sağlamak için HttpClient sınıfımızı tanımlıyoruz
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;
            //Post işlemi için sınıfımızı tanımlıyoruz...
            HttpPost post=new HttpPost();

            //Json objesinde tuttugumuz icerikleri String hale getirip, setEntity methoduna atıyoruz..
            HttpEntity httpEntity;
            StringEntity stringEntity=new StringEntity(jsonObject.toString(), HTTP.UTF_8);
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpEntity=stringEntity;
            post.setEntity(httpEntity);
            //RESTful Web Servisinin baglancağı url yi veriyoruz...
            post.setURI(new URI(wcfUrl));
            post.setHeader("Content-type", "application/json");
            // HttpEntity tutulan dataların HttpResponse tarafından çalıstırılmasını saglama..
            response=httpClient.execute(post);

            //response değişkenindeki  nesneyi, json string değerine çeviriyoruz...
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = rd.readLine()) != null) {
                sb.append(line + NL);

            }
            //Elde ettigimiz json string i değişkene atadık
            jsonString = sb.toString();
            rd.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return jsonString;
    }

    public static StringBuffer postWebService(String url){
        StringBuffer chaine =new StringBuffer("");
        try {
            URL myUrl=new URL(url);
            HttpURLConnection connection =(HttpURLConnection)myUrl.openConnection();
            connection.setRequestProperty("User-Agent","");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            BufferedReader rd =new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while((line = rd.readLine()) != null){
                chaine.append(line);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    return chaine;


    }

    public static void PostCourse(UserModel um) throws JSONException {
        //// TO DOOOO

        JSONObject obj = new JSONObject();
        try {
            obj.put("userName", um.getName());
            obj.put("userSurname",um.getSurName());
            obj.put("userEmail",um.getEmail());
            obj.put("userGender", um.getGender());
            obj.put("userPhone", um.getPhoneNumber());
            String objAsStr = obj.toString();
            //JSONObject jsonObject=ApiCaller.POST(AppStatic.URL,objAsStr);

            new AsyncClient("POST", new OnTaskCompleted() {
                @Override
                public void onTaskCompleted(JSONObject result) {
                    Log.e("Post","Post Basarılı");

                }
            }).execute("users", objAsStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void PostContact(Contact contact) throws JSONException {
        //// TO DOOOO

        JSONObject obj = new JSONObject();
        try {
            obj.put("contactName", contact.getContactName());
            obj.put("contactSurname",contact.getContactSurname());
            obj.put("contactPhone", contact.getPhone());
            obj.put("userId", contact.getUserId());
            String objAsStr = obj.toString();
            //JSONObject jsonObject=ApiCaller.POST(AppStatic.URL,objAsStr);

            new AsyncClient("POST", new OnTaskCompleted() {
                @Override
                public void onTaskCompleted(JSONObject result) {
                    Log.e("Post","Post Basarılı");

                }
            }).execute("Contacts", objAsStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
