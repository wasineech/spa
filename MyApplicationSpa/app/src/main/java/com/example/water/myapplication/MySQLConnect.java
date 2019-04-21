package com.example.water.myapplication;

import android.app.Activity;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {

    private final Activity main;
    private List<String> list;
    private String URL ="http://172.20.10.6/" ,GET_URL ="loginapp/get_cus.php" , SENT_URL = "loginapp/sent_cus.php";

    public MySQLConnect() { main = null;}

    public MySQLConnect(Activity mainA){
        main = mainA;
        list = new ArrayList<String>();
    }

    public List<String> getData(){

        String url = URL + GET_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);

        return list;
    }
    public void showJSON(String response) {
        String name_cus = "";
        String lastname_cus = "";
        String password_cus = "";
        String gender = "";
        String email = "";
        String tel = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject collectData = result.getJSONObject(i);
                name_cus = collectData.getString("name_cus");
                list.add(name_cus);
                lastname_cus = collectData.getString("lastname_cus");
                list.add(lastname_cus);
                password_cus = collectData.getString("password_cus");
                list.add(password_cus);
                gender = collectData.getString("gender");
                list.add(gender);
                email = collectData.getString("email");
                list.add(email);
                tel = collectData.getString("tel");
                list.add(tel);




            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    public void sentData(String value) {
        StrictMode.enableDefaults();
        if (Build.VERSION.SDK_INT> 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            ArrayList<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair( "isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair( "name_cus" , value));
            nameValuePairs.add(new BasicNameValuePair( "lastname_cus" , value));
            nameValuePairs.add(new BasicNameValuePair( "password_cus" , value));
            nameValuePairs.add(new BasicNameValuePair( "gender" , value));
            nameValuePairs.add(new BasicNameValuePair( "email" , value));
            nameValuePairs.add(new BasicNameValuePair( "tel" , value));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost( URL + SENT_URL);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);
            Toast.makeText(main, "Completed.", Toast.LENGTH_LONG).show();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}