package com.example.water.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "RecyclerViewJSON";
    private List<Model> feedsList;
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private ProgressBar progressBar;
    String idStr,idCStr,nameStr,lastnameStr,priceStr,dateStr,timeStr,imgStr,payStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
        String url = "http://192.168.2.36/spa/test22.php";
        new GetDataBinding().execute(url);

    }

    private class GetDataBinding extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Integer doInBackground(String... strings) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());

                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new MyAdapter(MainActivity.this, feedsList);
                mRecyclerView.setAdapter(adapter);


                adapter.setOnModelClickListener(new OnModelClickListener() {
                    @Override
                    public void onModelClick(Model item) {
                        //Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, item.getId_course(), Toast.LENGTH_LONG).show();
                        Bundle _bundle = getIntent().getExtras();
                        if (_bundle != null) {

                            String id_admin = _bundle.getString("admin_id");

                            idStr = item.getId_application();
                            idCStr = item.getId_application();
                            nameStr = item.getName_cus();
                            lastnameStr = item.getLasname_cus();
                            priceStr = item.getPrice_a();
                            dateStr = item.getDate_a();
                            timeStr = item.getTime_a();
                            imgStr = item.getImg_path();
                            payStr = item.getPay();

                            Intent intent = new Intent(MainActivity.this, ShowappActivity.class);
                            intent.putExtra("a_id", idStr);
                            intent.putExtra("a_name", nameStr);
                            intent.putExtra("a_lname", lastnameStr);
                            intent.putExtra("a_price", priceStr);
                            intent.putExtra("a_date", dateStr);
                            intent.putExtra("a_time", timeStr);
                            intent.putExtra("a_img", imgStr);
                            intent.putExtra("a_pay", payStr);
                            intent.putExtra("admin_id", id_admin);
                            startActivity(intent);

                            //Toast.makeText(MainActivity.this, id_admin, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch JSON data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("result");
            feedsList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                Model item = new Model();
                item.setId_application(post.optString("id_application"));
                item.setName_cus(post.optString("name_cus"));
                item.setLasname_cus(post.optString("lastname_cus"));
                item.setPrice_a(post.optString("price_a"));
                item.setDate_a(post.optString("date_a"));
                item.setTime_a(post.optString("time_a"));
                item.setImg_path(post.optString("img_path"));
                item.setPay(post.optString("pay"));
                feedsList.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
