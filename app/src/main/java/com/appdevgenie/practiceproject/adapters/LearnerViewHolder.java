package com.appdevgenie.practiceproject.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.practiceproject.R;

public class LearnerViewHolder extends RecyclerView.ViewHolder{

    public TextView textName;
    public TextView textInfo;
    public ImageView imageView;

    public LearnerViewHolder(@NonNull View itemView) {
        super(itemView);

        textName = itemView.findViewById(R.id.item_text_name);
        textInfo = itemView.findViewById(R.id.item_text_info);
        imageView = itemView.findViewById(R.id.item_image_view);
    }
}
