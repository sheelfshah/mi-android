package com.example.myapplication06;
import com.google.gson.annotations.SerializedName;

public class Participant {
    //can be made private, but saves effort

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("contact")
    public String contact;
    @SerializedName("college")
    public String college;
    @SerializedName("city")
    public String city;
    @SerializedName("idcard")
    public String idcard;
    @SerializedName("competitionname")
    public String competitionname;

    public Participant(Integer id, String name, String email, String contact, String college, String city, String idcard, String competitionname){
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.college = college;
        this.city = city;
        this.idcard = idcard;
        this.competitionname = competitionname;
    }
}
