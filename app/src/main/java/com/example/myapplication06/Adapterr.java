package com.example.myapplication06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Adapterr extends RecyclerView.Adapter<Adapterr.Viewholderr> {
    private List<Participant> participantList;
    private Context context;
    public Adapterr(Context c, List<Participant> ap, String category){
        this.context = c;
        List<Participant> l =new ArrayList<Participant>();
        for(int i=0;i<ap.size();i++){

            if(ap.get(i).competitionname.equals(category)) {
                l.add(ap.get(i));
                Log.d("xeex",ap.get(i).competitionname);
            }
        }
        this.participantList = l;
    }

    @NonNull
    @Override
    public Viewholderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_layout, parent, false);
        return new Viewholderr(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholderr holder, int position) {
        Participant participant = participantList.get(position);
        holder.name.setText(participant.name);
        holder.contact.setText((participant.contact));
        holder.category.setText((participant.competitionname));
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        String str=participant.idcard;
        str=str.replace("/registrationdata","");
        Log.d("xaax",str);
        ImageView img = holder.idcard;
        Picasso.get().load(str).resize(400,400).centerCrop().into(img);
        holder.idcard=img;
    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    public class Viewholderr extends RecyclerView.ViewHolder{
        TextView name;
        TextView contact;
        TextView category;
        ImageView idcard;

        public Viewholderr(@NonNull View itemView){
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.itemname);
            contact=(TextView) itemView.findViewById(R.id.itemcontact);
            category=(TextView) itemView.findViewById(R.id.itemcategory);
            idcard=(ImageView) itemView.findViewById(R.id.itemimage);
        }
    }
}
