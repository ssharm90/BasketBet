package com.example.basketbet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.JsonReader;
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
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class StatsActivity extends AppCompatActivity{

    private Button ret;
    private int topNum;
    private int bottomNum;
    private JSONObject json;



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

        JSONObject teamOneJson = statsApi(topNum);
        JSONObject teamTwoJson = statsApi(bottomNum);

        setStats(teamOneJson, teamTwoJson);

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

    public JSONObject statsApi(int teamNum) {
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
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url + urlAdd,
                         null, response -> {
                                // Display the first 500 characters of the response string.
                                json = response;
                                //textView.setText(response.toString().substring(0, 9));
                                System.out.println(response.toString().substring(3, 392));
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        System.out.println(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
                queue.add(stringRequest);
                return json;
    }
    public void setStats(JSONObject j1, JSONObject j2) {
        TextView points1View = findViewById(R.id.Points1);
        TextView points2View = findViewById(R.id.Points2);
        TextView rebounds1View = findViewById(R.id.reb1);
        TextView rebounds2View = findViewById(R.id.reb2);

        try {
            String pt1String = j1.getString("points_per_game");
            double pt1 = Double.parseDouble(pt1String);
            points1View.setText(pt1String);

            String pt2String = j2.getString("points_per_game");
            double pt2 = Double.parseDouble(pt1String);
            points2View.setText(pt2String);

            String rebound1String = j1.getString("rebounds_per_game");
            double reb1 = Double.parseDouble(rebound1String);
            points2View.setText(rebound1String);





        } catch (Exception e) {
            System.out.println("there was an error");
        }
    }

}

