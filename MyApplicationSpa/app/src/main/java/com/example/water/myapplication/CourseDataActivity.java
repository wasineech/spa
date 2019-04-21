package com.example.water.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import me.anwarshahriar.calligrapher.Calligrapher;

public class CourseDataActivity extends AppCompatActivity {
    String idStr,picStr,nameStr,priceStr,amountStr,iduserStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_data);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        ImageView pic = findViewById(R.id.pic);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView amount = findViewById(R.id.amount);
        Button buttonBuy = findViewById(R.id.button_buy);
        Bundle _bundle = getIntent().getExtras();
        if (_bundle != null) {
            String m_id = _bundle.getString("c_id");
            String m_pic = _bundle.getString("c_pic");
            String m_name = _bundle.getString("c_name");
            String m_price = _bundle.getString("c_price");
            String m_amount = _bundle.getString("c_amount");
            //Toast.makeText(this,iduserStr, Toast.LENGTH_SHORT).show();

            Glide.with(this).load(m_pic).into(pic);
            name.setText(m_name);
            price.setText(m_price);
            amount.setText(m_amount);

        }

        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle _bundle = getIntent().getExtras();
                if (_bundle != null) {
                    String admin_id =
                    iduserStr = _bundle.getString("iduser");
                    idStr = _bundle.getString("c_id");
                    picStr = _bundle.getString("c_pic");
                    nameStr = _bundle.getString("c_name");
                    priceStr = _bundle.getString("c_price");
                    amountStr = _bundle.getString("c_amount");
                    Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                    intent.putExtra("iduser",iduserStr);
                    intent.putExtra("c_id", idStr);
                    intent.putExtra("c_pic", picStr);
                    intent.putExtra("c_name", nameStr);
                    intent.putExtra("c_price", priceStr);
                    intent.putExtra("c_amount", amountStr);
                    startActivity(intent);
                }
            }

        });
    }
}
