/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private RecyclerView activityRecyclerView;
    private int sortByIndex = 0;
    private List<ActivityModel> activityModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sortByImageView) {
            showSortByOptionDialog();
        } else if (v.getId() == R.id.gitHubLogoImageView) {
            navigateSourcePage();
        }
    }

    /**
     * Initialize views from XML
     */
    private void initializeUI() {
        context = MainActivity.this;
        activityRecyclerView = findViewById(R.id.activityRecyclerView);
        AppCompatImageView sortByImageView = findViewById(R.id.sortByImageView);
        GitHubLogoView gitHubLogoImageView = findViewById(R.id.gitHubLogoImageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityRecyclerView.setLayoutManager(layoutManager);
        activityRecyclerView.setHasFixedSize(true);
        sortByImageView.setOnClickListener(this);
        gitHubLogoImageView.setOnClickListener(this);
        gitHubLogoImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        populateRecyclerView();
    }

    /**
     * Method used to Load RecyclerView initially
     */
    private void populateRecyclerView() {
        activityModelList = AppUtils.getSampleActivities();
        for (int h = 0; h < activityModelList.size(); h++) {
            String headerDate = changeDateFormat(activityModelList.get(h).getActivityTime());
            activityModelList.get(h).setHeaderDate(headerDate);
        }
        AppUtils.getDateSortedList(activityModelList);
        setAdapter();
    }

    /**
     * Setting Recycler Adapter
     */
    private void setAdapter() {
        if (activityModelList.size() > 0) {
            ActivitiesAdapter activitiesAdapter = new ActivitiesAdapter(context, activityModelList, sortByIndex);
            activityRecyclerView.setAdapter(activitiesAdapter);
        }
    }

    /**
     * Navigate to Github Source path
     */
    private void navigateSourcePage() {
        if (AppUtils.isNetworkAvailable(this)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(AppUtils.GITHUB_URL));
            startActivity(intent);
        }
    }

    /**
     * Method used to show Alert Dialog with sort by options with user interaction
     */
    private void showSortByOptionDialog() {
        ArrayList<String> sortOptionList = new ArrayList<>();
        sortOptionList.add("Date");
        sortOptionList.add("Activity Name");
        new AlertDialog.Builder(this).setTitle("SortBy").setSingleChoiceItems(sortOptionList.toArray(new CharSequence[0]), sortByIndex, (dialog, index) -> {
            dialog.dismiss();
            if (index != sortByIndex) {
                sortByIndex = index;
                if (sortByIndex == 0) {
                    AppUtils.getDateSortedList(activityModelList);
                } else {
                    AppUtils.getNameSortedList(activityModelList);
                }
                setAdapter();
            }
        }).create().show();
    }

    /**
     * @param givenDate - String date ~ Activity Date
     * @return - String date as 'MMM dd, yyyy' format
     */
    private String changeDateFormat(String givenDate) {
        Date resultDate;
        try {
            resultDate = AppUtils.date_format.parse(givenDate);
            return AppUtils.date_format_1.format(resultDate);
        } catch (ParseException e) {
            if (android.os.Build.MODEL.toLowerCase().contains("moto") && (givenDate.toLowerCase().contains("am") || givenDate.toLowerCase().contains("pm"))) {
                try {
                    givenDate = givenDate.toLowerCase().replace("am", "a.m.").replace("pm", "p.m.");
                    resultDate = AppUtils.date_format.parse(givenDate);
                    if (resultDate != null) {
                        return AppUtils.date_format_1.format(resultDate);
                    }
                } catch (ParseException e1) {
                    Log.e("##callDateFormatChangeMethod", "-------->" + e1.getMessage());
                }
            }
            return givenDate;
        }
    }


}