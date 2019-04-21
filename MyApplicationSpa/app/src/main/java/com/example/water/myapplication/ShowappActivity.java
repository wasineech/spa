package com.example.water.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ShowappActivity extends AppCompatActivity {
    String idStr,nameStr,lastnameStr,priceStr,dateStr,timeStr,imgStr,payStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showapp);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        TextView id = findViewById(R.id.id);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        TextView pay = findViewById(R.id.pay);
        ImageView pic = findViewById(R.id.pic);
        Button buttonConfirm = findViewById(R.id.button_confirm);
        Bundle _bundle = getIntent().getExtras();
        if (_bundle != null) {
            String m_id = _bundle.getString("a_id");
            String m_name = _bundle.getString("a_name");
            String m_lastname = _bundle.getString("a_lname");
            String m_price = _bundle.getString("a_price");
            String m_date = _bundle.getString("a_date");
            String m_time = _bundle.getString("a_time");
            String m_img = _bundle.getString("a_img");
            String m_pay = _bundle.getString("a_pay");
            String id_admin = _bundle.getString("admin_id");

            //Toast.makeText(this,iduserStr, Toast.LENGTH_SHORT).show();

            id.setText(m_id);
            name.setText(m_name + " " + m_lastname);
            price.setText(m_price);
            date.setText(m_date);
            time.setText(m_time);
            Glide.with(this).load(m_img).into(pic);
            pay.setText(m_pay);

        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle _bundle = getIntent().getExtras();
                if (_bundle != null) {
                    String m_id = _bundle.getString("a_id");
                    String id_admin = _bundle.getString("admin_id");
                    confirm(m_id, id_admin);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    //intent.putExtra("a_id", m_id);
                    startActivity(intent);
                }
            }

        });
    }
    public void confirm(final String m_id, final String id_admin) {

        String url = "http://192.168.2.36/spa/udp_app_post.php?id_application=" + m_id + "&id_admin=" + id_admin;
        RequestQueue requestQueue = Volley.newRequestQueue(ShowappActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);


                    String id_application = jsonObject1.getString("id_application");
                    String id_admin1 = jsonObject1.getString("id_admin");


                    Intent intent = new Intent(ShowappActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HiteshURLerror", "" + error);
            }
        });

        requestQueue.add(stringRequest);


    }
}
