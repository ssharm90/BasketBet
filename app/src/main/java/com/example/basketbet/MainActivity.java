package com.example.basketbet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    private Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        begin = (Button) findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
                getInfo();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void getInfo() {
        Spinner topSpinner = findViewById(R.id.topTeams);
        Team top = new Team();
        Spinner bottomSpinner = findViewById(R.id.bottomTeams);
        Team bottom = new Team();

        topSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                top.setNum(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });

        topSpinner.setSelection(top.getNum());

        try {
            teamGETRequest(top.getNum());
        } catch (IOException e) {
            Log.e("get", "get didnt work");
        }


        bottomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                bottom.setNum(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });

        bottomSpinner.setSelection(bottom.getNum());
        try {
            teamGETRequest(bottom.getNum());
        } catch (IOException e) {
            Log.e("get", "get didnt work");
        }
    }


    public  void teamGETRequest(int teamNum) throws IOException {
        String url = "https://www.balldontlie.io/api/v1/teams/" + teamNum;


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.toString());
            }
        });

    }





}
