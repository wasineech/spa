package com.example.water.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class DetailActivity extends AppCompatActivity {
    Button btnBack;
    TextView text;
    Bundle bundle;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        text = findViewById(R.id.text_view);
        btnBack = findViewById(R.id.back);
        bundle = getIntent().getExtras();

        if(bundle != null){
            id = bundle.getString("id_app");
            text.setText("ID: " + id);


        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class );
                startActivity(intent);
            }
        });
    }
}
