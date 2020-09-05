package com.appdevgenie.practiceproject.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.practiceproject.R;
import com.appdevgenie.practiceproject.models.Skill;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LearnerSkillsRecyclerAdapter extends RecyclerView.Adapter<LearnerViewHolder> {

    private Context context;
    private ArrayList<Skill> skillArrayList;

    public LearnerSkillsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new LearnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {

        Skill skill = skillArrayList.get(holder.getAdapterPosition());
        holder.textName.setText(skill.getName());
        holder.textInfo.setText(TextUtils.concat(
                String.valueOf(skill.getScore()),
                " ",
                context.getString(R.string.skill_iq_score),
                " ",
                skill.getCountry()));

        Glide.with(context)
                .load(skill.getBadgeUrl())
                .placeholder(R.drawable.loading)
                .into(holder.imageView);
    }

    public void setSkillArrayList(ArrayList<Skill> skillArrayList) {
        this.skillArrayList = skillArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(skillArrayList == null){
            return 0;
        }else {
            return skillArrayList.size();
        }
    }
}
