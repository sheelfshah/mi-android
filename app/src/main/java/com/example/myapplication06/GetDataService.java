package com.example.myapplication06;

import com.example.myapplication06.Participant;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/registrationdata")
    Call<List<Participant>> getAllParticipants();
}
