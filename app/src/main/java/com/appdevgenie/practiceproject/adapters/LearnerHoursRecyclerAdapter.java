package com.appdevgenie.practiceproject.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.models.Hour;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LearnerHoursRecyclerAdapter extends RecyclerView.Adapter<LearnerViewHolder> {

    private Context context;
    private ArrayList<Hour> hourArrayList;

    public LearnerHoursRecyclerAdapter(Context context, ArrayList<Hour> hourArrayList) {
        this.context = context;
        this.hourArrayList = hourArrayList;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new LearnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {

        Hour hour = hourArrayList.get(holder.getAdapterPosition());
        holder.textName.setText(hour.getName());
        holder.textInfo.setText(TextUtils.concat(String.valueOf(hour.getHours()), " learning hours, ", hour.getCountry()));

        Glide.with(context)
                .load(hour.getBadgeUrl())
                .placeholder(R.drawable.loading)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(hourArrayList == null){
            return 0;
        }else {
            return hourArrayList.size();
        }
    }


}
