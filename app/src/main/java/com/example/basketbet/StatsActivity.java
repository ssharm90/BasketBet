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
//import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Text;

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

        statsApi(topNum, bottomNum);
        //statsApi(bottomNum);
        //System.out.println(json.toString());

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

    public void statsApi(int team1Num, int team2Num) {
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

        String urlAdd = players[team1Num];
                //final TextView textView = (TextView) findViewById(R.id.Points1);
        // ...

        // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://nba-players.herokuapp.com/players-stats/";

        // Request a string response from the provided URL.
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url + urlAdd,
                         null, response -> {
                                set1Stats(response);
                                // Display the first 500 characters of the response string.
                                //textView.setText(response.toString().substring(0, 9));
                                System.out.println(response.toString());
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        System.out.println(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
                queue.add(stringRequest);

        String url2Add = players[team1Num];
        //final TextView textView = (TextView) findViewById(R.id.Points1);
        // ...

        // Instantiate the RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2 ="https://nba-players.herokuapp.com/players-stats/";

        // Request a string response from the provided URL.
        JsonObjectRequest string2Request = new JsonObjectRequest(Request.Method.GET, url2 + url2Add,
                null, response2 -> {
            set2Stats(response2);
            // Display the first 500 characters of the response string.
            //textView.setText(response.toString().substring(0, 9));
            System.out.println(response2.toString());
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                System.out.println(error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue2.add(string2Request);
    }
    public void set1Stats(JSONObject j1) {
        TextView points1View = findViewById(R.id.Points1);
        //TextView points2View = findViewById(R.id.Points2);
        TextView rebounds1View = findViewById(R.id.reb1);
        //TextView rebounds2View = findViewById(R.id.reb2);
        TextView twofg1View = findViewById(R.id.twoFG1);
        //TextView twofg2View = findViewById(R.id.twoFG2);
        TextView threefg1View = findViewById(R.id.threeFG1);
        //TextView threefg2View = findViewById(R.id.threeFG2);
        TextView per1View = findViewById(R.id.per1);
        //TextView per2View = findViewById(R.id.per2);
        TextView turnover1View = findViewById(R.id.turnOver1);
        //TextView turnover2View = findViewById(R.id.turnOver2);
        TextView assists1View = findViewById(R.id.assists1);
        //TextView assists2View = findViewById(R.id.assists2);
        TextView blocks1View = findViewById(R.id.blocks1);
        //TextView blocks2View = findViewById(R.id.blocks2);
        TextView steals1View = findViewById(R.id.steals1);
        //TextView steals2View = findViewById(R.id.steals2);
        TextView min1View = findViewById(R.id.min1);
        //TextView min2View = findViewById(R.id.min2);

        try {
            String pt1String = j1.getString("points_per_game");
            double pt1 = Double.parseDouble(pt1String);
            points1View.setText("Points: " + pt1String);

            //System.out.println("fdhksaj +" + pt1);

            //String pt2String = j2.getString("points_per_game");
            //double pt2 = Double.parseDouble(pt1String);
            //points2View.setText(pt2String);

            String rebound1String = j1.getString("rebounds_per_game");
            double reb1 = Double.parseDouble(rebound1String);
            rebounds1View.setText("Rebounds: " + rebound1String);

            //String rebound2String = j2.getString("rebounds_per_game");
            //double reb2 = Double.parseDouble(rebound1String);
            //rebounds2View.setText(rebound2String);

            String twoFG1 = j1.getString("field_goal_percentage");
            double twoFGp1 = Double.parseDouble(twoFG1);
            twofg1View.setText("Twos: " + twoFG1);

           // String twoFG2 = j2.getString("field_goal_percentage");
           // double twoFGp2 = Double.parseDouble(twoFG2);
        //    twofg2View.setText(twoFG2);

            String threeFG1 = j1.getString("three_point_percentage");
            double threeFGp1 = Double.parseDouble(threeFG1);
            threefg1View.setText("Threes: " + threeFG1);

          //  String threeFG2 = j2.getString("three_point_percentage");
          //  double threeFGp2 = Double.parseDouble(threeFG2);
           // threefg2View.setText(threeFG2);

            String perS1 = j1.getString("player_efficiency_rating");
            double per1 = Double.parseDouble(perS1);
            per1View.setText("Efficiency: " + perS1);

        //    String perS2 = j2.getString("player_efficiency_rating");
           // double per2 = Double.parseDouble(perS2);
          //  per2View.setText(perS2);

            String turnS1 = j1.getString("turnovers_per_game");
            double turn1 = Double.parseDouble(turnS1);
            turnover1View.setText("Turnovers: " + turnS1);

           // String turnS2 = j2.getString("turnovers_per_game");
           // double turn2 = Double.parseDouble(turnS2);
           // turnover2View.setText(turnS2);

            String assistsS1 = j1.getString("assists_per_game");
            double ass1 = Double.parseDouble(assistsS1);
            assists1View.setText("Assists: " + assistsS1);

            //String assistsS2 = j2.getString("assists_per_game");
            //double ass2 = Double.parseDouble(assistsS2);
            //assists2View.setText(assistsS2);

            String blocksS1 = j1.getString("blocks_per_game");
            double b1 = Double.parseDouble(blocksS1);
            blocks1View.setText("Blocks: " + blocksS1);

            //String blocksS2 = j2.getString("blocks_per_game");
            //double b2 = Double.parseDouble(blocksS2);
            //blocks2View.setText(blocksS2);

            String stealsS1 = j1.getString("steals_per_game");
            double s1 = Double.parseDouble(stealsS1);
            steals1View.setText("Steals: " + stealsS1);

            //String stealsS2 = j2.getString("steals_per_game");
            //double s2 = Double.parseDouble(stealsS2);
            //steals2View.setText(stealsS2);

            String minS1 = j1.getString("minutes_per_game").substring(0,2);
            double min1 = Double.parseDouble(minS1);
            min1View.setText("Minutes : " + minS1);

            //String minS2 = j2.getString("minutes_per_game").substring(0,2);
            //double min2 = Double.parseDouble(minS2);
            //min2View.setText(minS2);



        } catch (Exception e) {
            System.out.println("there was an error");
        }
    }

    public void set2Stats(JSONObject j2) {
        TextView points1View = findViewById(R.id.Points1);
        TextView points2View = findViewById(R.id.Points2);
        TextView rebounds1View = findViewById(R.id.reb1);
        TextView rebounds2View = findViewById(R.id.reb2);
        TextView twofg1View = findViewById(R.id.twoFG1);
        TextView twofg2View = findViewById(R.id.twoFG2);
        TextView threefg1View = findViewById(R.id.threeFG1);
        TextView threefg2View = findViewById(R.id.threeFG2);
        TextView per1View = findViewById(R.id.per1);
        TextView per2View = findViewById(R.id.per2);
        TextView turnover1View = findViewById(R.id.turnOver1);
        TextView turnover2View = findViewById(R.id.turnOver2);
        TextView assists1View = findViewById(R.id.assists1);
        TextView assists2View = findViewById(R.id.assists2);
        TextView blocks1View = findViewById(R.id.blocks1);
        TextView blocks2View = findViewById(R.id.blocks2);
        TextView steals1View = findViewById(R.id.steals1);
        TextView steals2View = findViewById(R.id.steals2);
        TextView min1View = findViewById(R.id.min1);
        TextView min2View = findViewById(R.id.min2);

        try {
            //String pt1String = j1.getString("points_per_game");
            //double pt1 = Double.parseDouble(pt1String);
            //points1View.setText("Points:");

            //System.out.println("fdhksaj +" + pt1);

            String pt2String = j2.getString("points_per_game");
            double pt2 = Double.parseDouble(pt2String);
            points2View.setText(pt2String);

        //    String rebound1String = j1.getString("rebounds_per_game");
          //  double reb1 = Double.parseDouble(rebound1String);
            //rebounds1View.setText(rebound1String);

            String rebound2String = j2.getString("rebounds_per_game");
            double reb2 = Double.parseDouble(rebound2String);
            rebounds2View.setText(rebound2String);

           // String twoFG1 = j1.getString("field_goal_percentage");
           // double twoFGp1 = Double.parseDouble(twoFG1);
           // twofg1View.setText(twoFG1);

            String twoFG2 = j2.getString("field_goal_percentage");
            double twoFGp2 = Double.parseDouble(twoFG2);
            twofg2View.setText(twoFG2);

         //   String threeFG1 = j1.getString("three_point_percentage");
          //  double threeFGp1 = Double.parseDouble(threeFG1);
         //  threefg1View.setText(threeFG1);

            String threeFG2 = j2.getString("three_point_percentage");
            double threeFGp2 = Double.parseDouble(threeFG2);
            threefg2View.setText(threeFG2);

          //  String perS1 = j1.getString("player_efficiency_rating");
          //  double per1 = Double.parseDouble(perS1);
           // per1View.setText(perS1);

            String perS2 = j2.getString("player_efficiency_rating");
            double per2 = Double.parseDouble(perS2);
            per2View.setText(perS2);

          //  String turnS1 = j1.getString("turnovers_per_game");
           // double turn1 = Double.parseDouble(turnS1);
        //    turnover1View.setText(turnS1);

            String turnS2 = j2.getString("turnovers_per_game");
            double turn2 = Double.parseDouble(turnS2);
            turnover2View.setText(turnS2);

          //  String assistsS1 = j1.getString("assists_per_game");
            //double ass1 = Double.parseDouble(assistsS1);
            //assists1View.setText(assistsS1);

            String assistsS2 = j2.getString("assists_per_game");
            double ass2 = Double.parseDouble(assistsS2);
            assists2View.setText(assistsS2);

         //   String blocksS1 = j1.getString("blocks_per_game");
           // double b1 = Double.parseDouble(blocksS1);
           // blocks1View.setText(blocksS1);

            String blocksS2 = j2.getString("blocks_per_game");
            double b2 = Double.parseDouble(blocksS2);
            blocks2View.setText(blocksS2);

          //  String stealsS1 = j1.getString("steals_per_game");
          //  double s1 = Double.parseDouble(stealsS1);
          //  steals1View.setText(stealsS1);

            String stealsS2 = j2.getString("steals_per_game");
            double s2 = Double.parseDouble(stealsS2);
            steals2View.setText(stealsS2);

           // String minS1 = j1.getString("minutes_per_game").substring(0,2);
           // double min1 = Double.parseDouble(minS1);
           // min1View.setText(minS1);

            String minS2 = j2.getString("minutes_per_game").substring(0,2);
            double min2 = Double.parseDouble(minS2);
            min2View.setText(minS2);



        } catch (Exception e) {
            System.out.println("there was an error");
        }
    }

}

