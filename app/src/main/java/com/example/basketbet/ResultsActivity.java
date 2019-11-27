package com.example.basketbet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class ResultsActivity extends AppCompatActivity{

    private Button ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ret = findViewById(R.id.retr);

        ret.setOnClickListener(unused -> {
            Log.i("button", "begin button was clicked!");
        });

    }

}
