package com.example.recyclerviewdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements OnNameSelectedListener {
    NamesAdapter namesAdapter = new NamesAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        recyclerView
                .setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(namesAdapter);

        try {
            new APIWorker().execute(new URL("https://api.github.com/users/google"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    private List<String> generateData() {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            names.add("Name: " + (i+1));
        }

        return names;
    }

    @Override
    public void onNameSelected(int position) {
        Toast.makeText(this, "Position: " + position, Toast.LENGTH_SHORT).show();
    }

    class APIWorker extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder result = new StringBuilder();
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)
                        urls[0].openConnection();
                InputStream inputStream = new
                        BufferedInputStream(httpsURLConnection.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));

                String line;
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

                Gson gson = new Gson();
                GithubProfile githubProfile = gson.
                        fromJson(result, GithubProfile.class);

                List<GithubProfile> apiResults = Collections
                        .singletonList(githubProfile);
                namesAdapter.setData(apiResults);


        }
    }
}
