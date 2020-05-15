package com.dal.chucknorrisapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class filleradapter extends RecyclerView.Adapter<filleradapter.fillerviewholder> {
    private Context mCtx;

    public filleradapter(Context mCtx, List<filler> fillerList) {
        this.mCtx = mCtx;
        this.fillerList = fillerList;
    }

    private List<filler> fillerList;


    @NonNull
    @Override
    public fillerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view =inflater.inflate(R.layout.card_view,null);//inflate the cardview
        return new fillerviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull fillerviewholder holder, int position) {
        filler filler=fillerList.get(position);
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(filler.getImg()));
        holder.textView.setText(filler.getJokes());

    }

    @Override
    public int getItemCount() {
        return fillerList.size();
    }

    class fillerviewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public fillerviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image); //getting the image and jokes
            textView=itemView.findViewById(R.id.jokes);

        }
    }
}
