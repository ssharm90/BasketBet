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
import android.widget.TextView;
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
    RequestQueue requestQueue;
    private Team top;
    private Team bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        begin = (Button) findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity();

            }
        });
    }

    private void launchActivity() {
        Spinner topSpinner = findViewById(R.id.topTeams);
        top = new Team();
        Spinner bottomSpinner = findViewById(R.id.bottomTeams);
        bottom = new Team();
        top.setNum(topSpinner.getSelectedItemPosition());
        top.setName(topSpinner.getSelectedItem().toString());
        bottom.setName(bottomSpinner.getSelectedItem().toString());
        bottom.setNum(bottomSpinner.getSelectedItemPosition());


        /*topSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                top.setNum(position);
                top.setName(topSpinner.getItemAtPosition(position).toString());
                Toast.makeText(MainActivity.this, "Botot, name: " + top.getName() + " num: " + top.getNum(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });*/

        Toast.makeText(MainActivity.this, "name: " + top.getName() + " num: " + top.getNum(), Toast.LENGTH_LONG).show();

        //topSpinner.setSelection(top.getNum());


        /*bottomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                bottom.setNum(position);
                bottom.setName(bottomSpinner.getItemAtPosition(position).toString());
                Toast.makeText(MainActivity.this, "Botot, name: " + bottom.getName() + " num: " + bottom.getNum(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });*/

        //bottomSpinner.setSelection(bottom.getNum());
        //Toast.makeText(MainActivity.this, "Botot, name: " + bottom.getName() + " num: " + bottom.getNum(), Toast.LENGTH_LONG).show();




        Intent intent = new Intent(this, StatsActivity.class);
        intent.putExtra("topTeam", top.getNum());
        intent.putExtra("bottomTeam", bottom.getNum());
        intent.putExtra("topTeamn", top.getName());
        intent.putExtra("bottomTeamn", bottom.getName());

        startActivity(intent);
    }

    /**
    public void getInfo() {
        Spinner topSpinner = findViewById(R.id.topTeams);
        top = new Team();
        Spinner bottomSpinner = findViewById(R.id.bottomTeams);
        bottom = new Team();

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

    } */

    /**
    public  void teamGETRequest(int teamNum) throws IOException {
        String url = "https://www.balldontlie.io/api/v1/teams/" + teamNum;


        requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView textView = (TextView) findViewById(R.id.team2Label);
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error.toString());
                System.out.println(error.toString());
            }
        });

        stringRequest.setTag("Request1");

        requestQueue.add(stringRequest);

    }

    protected void onStop() {
        super.onStop();
        if(requestQueue != null) {
            requestQueue.cancelAll("Request1");
        }
    }
     */



}
