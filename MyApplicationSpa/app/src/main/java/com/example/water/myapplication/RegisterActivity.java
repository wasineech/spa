package com.example.water.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import static com.android.volley.Request.*;

public class RegisterActivity extends AppCompatActivity {

    Button button_register;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText name, lastname,password, email, tel;
    ProgressDialog progressDialog;
    String getname, getlastname, getpassword, getemail, gettel, finalResult, getgender;
    HttpParse httpParse = new HttpParse();
    HashMap<String,String> hashMap = new HashMap<>();
    String HttpURL = "http://172.20.10.6/spa/sent_cus.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        password = findViewById(R.id.password);
        email =  findViewById(R.id.email);
        tel = findViewById(R.id.tel);
        radioGroup = findViewById(R.id.radio);
        button_register = findViewById(R.id.btnRegister);






        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getname = name.getText().toString();
                getlastname = lastname.getText().toString();
                getpassword = password.getText().toString();
                getemail = email.getText().toString();
                gettel = tel.getText().toString();

                submitForm(getname,getlastname,getpassword,getemail,gettel);
//                int selectid = radioGroup.getCheckedRadioButtonId();
//                radioButton = findViewById(selectid);
//                getgender = radioButton.getText().toString();
            }
        });
    }
    public void submitForm(final String namecus, final String lastnamecus, final String passwordcus, final String emailcus, final String telcus) {

        class submitFormClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("namecus", params[0]);
                hashMap.put("lastnamecus", params[1]);
                hashMap.put("passwordcus", params[2]);
                //hashMap.put("gendercus", params[3]);
                hashMap.put("emailcus", params[3]);
                hashMap.put("telcus", params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;



            }
        }
        submitFormClass submitformclassed = new submitFormClass();
        submitformclassed.execute(namecus,lastnamecus,passwordcus,emailcus,telcus);
    }
}