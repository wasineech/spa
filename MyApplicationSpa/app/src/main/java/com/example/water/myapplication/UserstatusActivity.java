package com.example.water.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class UserstatusActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewJSON";
    private List<Application> feedsList;
    private RecyclerView aRecyclerView;
    private AppRecyclerViewAdapter adapterA;
    private ProgressBar progressBar;
    String iduserStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userstatus);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lamoon.otf", true);
        aRecyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";

        Bundle _bundle = getIntent().getExtras();
        if (_bundle != null) {
            iduserStr = _bundle.getString("iduser");
            String url = "http://172.20.10.6/spa/app_get_post.php?id_customer=" + iduserStr;
            new GetDataBinding().execute(url);
            Toast.makeText(getApplicationContext(), iduserStr ,Toast.LENGTH_SHORT).show();
        }

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
                adapterA = new AppRecyclerViewAdapter(UserstatusActivity.this, feedsList);
                aRecyclerView.setAdapter(adapterA);


                //adapterA.setOnItemClickListener(new OnItemClickListener() {
                //@Override
                // public void onItemClick(Course item) {
                //Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                        /*picStr = item.getPic();
                        nameStr = item.getName();
                        priceStr = item.getPrice();
                        amountStr = item.getAmount();
                        Intent intent = new Intent(MainActivity.this, CourseDataActivity.class);
                        intent.putExtra("c_pic", picStr);
                        intent.putExtra("c_name", nameStr);
                        intent.putExtra("c_price", priceStr);
                        intent.putExtra("c_amount", amountStr);
                        startActivity(intent);*/

                //}
                //});

            } else {
                Toast.makeText(UserstatusActivity.this, "Failed to fetch JSON data!", Toast.LENGTH_SHORT).show();
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
                Application item = new  Application();
                item.setId_application(post.optString("id_application"));
                item.setState(post.optString("state"));
                feedsList.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
