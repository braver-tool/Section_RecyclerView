/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview;


import static android.view.View.VISIBLE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder> {
    private final Context context;
    private final List<ActivityModel> activityModelList;
    private final int sortByIndex;


    public ActivitiesAdapter(Context ctx, List<ActivityModel> activityModels, int index) {
        this.activityModelList = activityModels;
        this.context = ctx;
        this.sortByIndex = index;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_activities, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ActivityModel model = activityModelList.get(position);
        if (model != null) {
            String activityName = model.getActivityName();
            int activityId = AppUtils.getActivityID(activityName);
            int activityIcon = AppUtils.getActivityImage(context, activityId);
            int activityColor = AppUtils.getActivityColor(context, activityId);
            if (sortByIndex == 0) {
                holder.dateHeaderTextView.setText(model.getHeaderDate());
                holder.dateHeaderTextView.setVisibility((position != 0 && model.getHeaderDate().equals(activityModelList.get(position - 1).getHeaderDate())) ? View.GONE : VISIBLE);
            } else {
                holder.dateHeaderTextView.setText(activityName);
                holder.dateHeaderTextView.setVisibility((position != 0 && model.getActivityName().equals(activityModelList.get(position - 1).getActivityName())) ? View.GONE : VISIBLE);
            }
            holder.activityDateTimeTextView.setText(model.getActivityTime());
            holder.activityNameTextView.setText(activityName);
            holder.caloriesTextView.setText(model.getCalories());
            holder.durationTextView.setText(model.getDuration());
            holder.activityImageView.setImageResource(activityIcon);
            holder.activityColorBgLayout.setBackgroundTintList(ContextCompat.getColorStateList(context, activityColor));
        }
    }


    @Override
    public int getItemCount() {
        return activityModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatTextView dateHeaderTextView;
        private final AppCompatTextView activityNameTextView;
        private final AppCompatTextView caloriesTextView;
        private final AppCompatTextView durationTextView;
        private final AppCompatImageView activityImageView;
        private final AppCompatTextView activityDateTimeTextView;
        private final LinearLayout activityColorBgLayout;


        MyViewHolder(View v) {
            super(v);
            dateHeaderTextView = v.findViewById(R.id.dateHeaderTextView);
            activityNameTextView = v.findViewById(R.id.activityNameTextView);
            caloriesTextView = v.findViewById(R.id.caloriesTextView);
            durationTextView = v.findViewById(R.id.durationTextView);
            activityDateTimeTextView = v.findViewById(R.id.activityDateTimeTextView);
            activityImageView = v.findViewById(R.id.activityImageView);
            activityColorBgLayout = v.findViewById(R.id.activityColorBgLayout);
        }

    }
}

