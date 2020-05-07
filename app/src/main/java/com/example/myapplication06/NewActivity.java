package com.example.myapplication06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {

    private Adapterr adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent i =getIntent();
        final String category=i.getStringExtra("category");
        TextView heading = findViewById(R.id.newheading);
        heading.setText(category+ " Participants");

        progressDialog =new ProgressDialog(NewActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Participant>> call= service.getAllParticipants();
        call.enqueue(new Callback<List<Participant>>() {
            @Override
            public void onResponse(Call<List<Participant>> call, Response<List<Participant>> response) {
                progressDialog.dismiss();
                generateDataList(response.body(), category);
            }

            @Override
            public void onFailure(Call<List<Participant>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewActivity.this, "Something went wrong...Please check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateDataList(List<Participant> participantList, String category){
        recyclerView=findViewById(R.id.recyclerview);
        adapter= new Adapterr(this,participantList,category);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
