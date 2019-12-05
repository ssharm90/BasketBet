package com.example.basketbet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class StatsActivity extends AppCompatActivity{

    private Button ret;
    private int topNum;
    private int bottomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent intent = getIntent();
        topNum = intent.getIntExtra("topTeam", 1);
        bottomNum = intent.getIntExtra("bottomTeam", 1);
        String tName = intent.getStringExtra("topTeamn");
        String bName = intent.getStringExtra("bottomTeamn");
        TextView tTextView = (TextView) findViewById(R.id.Team1Label);
        tTextView.setText(tName);
        TextView bTextView = (TextView) findViewById(R.id.team2Label);
        bTextView.setText(bName);

        statsApi(topNum);
        statsApi(bottomNum);

        ret = findViewById(R.id.winBut);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });
    }
    private void launchActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    public void statsApi(int teamNum) {
        //GET https://nba-players.herokuapp.com/players-stats/james/lebron

        String[] players = {
                "schroder/dennis",
                "irving/kyrie",
                "lin/jeremy",
                "walker/kemba",
                "lavine/zach",
                "james/lebron",
                "barnes/harrison",
                "jokic/nikola",
                "griffin/blake",
                "curry/stephen",
                "harden/james",
                "oladipo/victor",
                "rivers/austin",
                "kuzma/kyle",
                "brooks/marshon",
                "dragic/goran",
                "antetokounmpo/giannis",
                "butler/jimmy",
                "davis/anthony",
                "porzingis/kristaps",
                "westbrook/russell",
                "gordon/aaron",
                "embiid/joel",
                "booker/devin",
                "lillard/damian",
                "randolph/zach",
                "aldridge/lamarcus",
                "derozan/demar",
                "mitchell/donovan",
                "wall/john"};

        String urlAdd = players[teamNum];
                final TextView textView = (TextView) findViewById(R.id.Points1);
        // ...

        // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://nba-players.herokuapp.com/players-stats/";

        // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url + urlAdd,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                textView.setText("Response is: "+ response.substring(0, 500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                });

        // Add the request to the RequestQueue.
                queue.add(stringRequest);
    }


}

