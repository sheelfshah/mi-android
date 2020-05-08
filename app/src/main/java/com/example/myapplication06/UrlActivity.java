package com.example.myapplication06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class UrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        Intent i =getIntent();
        final String qrdecode=i.getStringExtra("qrdecode");
        TextView t=(TextView)findViewById(R.id.urltext);
        t.setText(qrdecode);
    }
}
