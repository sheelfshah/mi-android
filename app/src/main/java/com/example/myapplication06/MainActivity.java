package com.example.myapplication06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchNew(View v){
        Intent i=new Intent(this, NewActivity.class);
        i.putExtra("category",((TextView)v).getText().toString());
        startActivity(i);
    }

    public void btnClick(View v){
        Intent i=new Intent(this,ScannedBarcodeActivity.class);
        startActivity(i);
    }
}
