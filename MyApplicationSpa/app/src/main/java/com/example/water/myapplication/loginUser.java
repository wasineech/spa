package com.example.water.myapplication;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.anwarshahriar.calligrapher.Calligrapher;


public class loginUser extends Activity {
    private Handler handler = new Handler();
    private static final int REFRESH_SCREEN = 1;
    String username, password, url;
    String GET_URL, SENT_URL;
    Button btnSubmit;
    Button btnRegister;
    TextView txtUsername, txtPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginUser.this, Register2Activity.class);
                startActivity(intent);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(loginUser.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                    builder.setCancelable(true);
                    builder.setMessage("กรุณากรอกข้อมูลให้ครบ");
                    builder.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    android.app.AlertDialog dialog = builder.create();
                    dialog.show();


                } else {

                    login(username, password);

                }
            }
        });

    }

    public void login(final String username, final String password) {

        url = "http://172.20.10.6/spa/get_postcus.php?email=" + username + "&password_cus=" + password;
        RequestQueue requestQueue = Volley.newRequestQueue(loginUser.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);


                    String nameadmin = jsonObject1.getString("email");
                    String passwordadmin = jsonObject1.getString("password_cus");
                    String iduser = jsonObject1.getString("id_customer");

                    Intent intent = new Intent(loginUser.this,HomeActivity.class);
                    intent.putExtra("iduser",iduser);
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

    public void gologinAddmin (View view) {
        Intent intent = new Intent(loginUser.this, LoginActivity.class);
        startActivity(intent);
    }

}
