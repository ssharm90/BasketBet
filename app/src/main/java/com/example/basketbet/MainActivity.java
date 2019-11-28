package com.example.basketbet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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
        begin = findViewById(R.id.begin);

        begin.setOnClickListener(unused -> {
            Log.i("button", "begin button was clicked!");
            startCalculation();
        });

    }

    /**
     * Changes screen to activity_result.xml.
     */
    public void startCalculation() {
        Intent intent = new Intent(this, ResultsActivity.class);
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
    }


    public static void teamGETRequest(int teamNum) throws IOException {
        URL urlForGetRequest = new URL("https://www.balldontlie.io/api/v1/teams/" + teamNum);
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("id", "a1bcdef"); // set userId its a sample here
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            Log.i("Get", "JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }


}
