package com.example.water.myapplication;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class PayActivity extends AppCompatActivity {


    String idStr,picStr,nameStr,priceStr,amountStr,payStr,dateStr,timeStr,idAdminStr,picPayStr,iduserStr;
    //RequestQueue requestQueue;

    //String HttpUrl = "http://192.168.2.36/spa/app_sent.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        ImageView pic = findViewById(R.id.pic);
        final TextView name = findViewById(R.id.name);
        final TextView price = findViewById(R.id.price);
        final TextView amount = findViewById(R.id.amount);


        Button buttonBuy = findViewById(R.id.button_buy);
        Bundle _bundle = getIntent().getExtras();


        if (_bundle != null) {
            iduserStr = _bundle.getString("iduser");
            idStr = _bundle.getString("c_id");
            picStr = _bundle.getString("c_pic");
            nameStr = _bundle.getString("c_name");
            priceStr = _bundle.getString("c_price");
            amountStr = _bundle.getString("c_amount");

            Glide.with(this).load(picStr).into(pic);
            name.setText(nameStr);
            price.setText(priceStr);
            amount.setText(amountStr);

            idAdminStr = "0";

            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            timeStr = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            //Toast.makeText(getApplicationContext(), dateStr ,Toast.LENGTH_SHORT).show();
        }

        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText payeiei = findViewById(R.id.pay);
                EditText picPayeiei = findViewById(R.id.picPay);
                payStr = payeiei.getText().toString();
                picPayStr = picPayeiei.getText().toString();

                new InsertAsyn().execute("http://172.20.10.6/spa/app_sent.php");

                Intent intent = new Intent(PayActivity.this, UserstatusActivity.class);
                intent.putExtra("iduser",iduserStr);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), payStr ,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private class InsertAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try{
                OkHttpClient _okHttpClient = new OkHttpClient();
                RequestBody _requestBody = new FormBody.Builder()
                        .add("price_a", idStr)
                        .add("date_a", dateStr)
                        .add("time_a", timeStr)
                        .add("img_path", picPayStr)
                        .add("pay", payStr)
                        .add("id_course", idStr)
                        .add("id_admin", idAdminStr)
                        .add("id_customer", iduserStr)
                        .build();

                Request _request = new Request.Builder().url(strings[0]).post(_requestBody).build();
                _okHttpClient.newCall(_request).execute();
                return "successfully";

            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "บันทึกข้อมูลเรียบร้อยแล้ว",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "ไม่สามารถบันทึกข้อมูลได้",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
