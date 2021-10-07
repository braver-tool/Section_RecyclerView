/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AppUtils {

    public static final SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
    public static final SimpleDateFormat date_format_1 = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    public static final String GITHUB_URL = "https://github.com/braver-tool/SectionRecyclerView/blob/main/app/src/main/java/com/section/recyclerview/MainActivity.java";
    private static final String[] activityNameList = {"Walking", "Running", "Cycling", "Hiking",
            "Tennis", "Hockey", "Yoga", "Fishing", "Dancing", "Skating"};

    /**
     * Method is return boolean values based on device's mobile data/wi-fi
     *
     * @param context - Current Activity
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean status = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo;
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                status = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return status;
    }

    /**
     * @param title - String data (Contains Activity Name)
     * @return - Int Data ~ i.e. Activity Position in the array to select icon and color
     */
    public static int getActivityID(String title) {
        int pos = 0;
        for (int i = 0; i < activityNameList.length; i++) {
            if (title.equals(activityNameList[i])) {
                pos = i + 1;
                break;
            }
        }
        return pos;
    }


    /**
     * @param context    - Current Activity
     * @param activityId - Int data ~ activity position in the array
     * @return - Activity Icon from Drawable
     */
    public static int getActivityImage(Context context, int activityId) {
        int activityImage;
        try {
            @SuppressLint("Recycle") TypedArray activityImageArray = context.getResources().obtainTypedArray(R.array.activityIcons);
            activityImage = activityImageArray.getResourceId(activityId - 1, 0);
        } catch (Exception e) {
            activityImage = R.drawable.ic_activity_walking;
            Log.e("##getActivityImage", "------->Utils----->" + e.getMessage());
        }
        return activityImage != 0 ? activityImage : R.drawable.ic_activity_walking;
    }

    /**
     * @param context    - Current Activity
     * @param activityId - Int data ~ activity position in the array
     * @return - Activity color from Drawable
     */
    public static int getActivityColor(Context context, int activityId) {
        int activityColor;
        try {
            @SuppressLint("Recycle") TypedArray activityColorArray = context.getResources().obtainTypedArray(R.array.activityColors);
            activityColor = activityColorArray.getResourceId(activityId - 1, 0);
        } catch (Exception e) {
            activityColor = R.color.color_walking;
            Log.e("##getActivityColor", "------->Utils----->" + e.getMessage());
        }
        return activityColor != 0 ? activityColor : R.color.color_walking;
    }


    /**
     * @param activityModelList - List<ActivityModel>
     * @return - List<ActivityModel>
     * Method used to sort activityModelList based on ActivityTime
     */
    public static List<ActivityModel> getDateSortedList(List<ActivityModel> activityModelList) {
        Collections.sort(activityModelList, (a1, a2) -> {
            try {
                return date_format.parse(a1.getActivityTime()).compareTo(date_format.parse(a2.getActivityTime()));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        });
        Collections.reverse(activityModelList);
        return activityModelList;
    }

    /**
     * @param activityModelList - List<ActivityModel>
     * @return - List<ActivityModel>
     * Method used to sort activityModelList based on Activity Name
     */
    public static List<ActivityModel> getNameSortedList(List<ActivityModel> activityModelList) {
        Collections.sort(activityModelList, (a1, a2) -> {
            try {
                return a1.getActivityName().compareTo(a2.getActivityName());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
        return activityModelList;
    }

    /**
     * @return - List<ActivityModel>
     * Method returns default Activity List
     */
    public static List<ActivityModel> getSampleActivities() {
        List<ActivityModel> activityModelList = new ArrayList<>();
        activityModelList.add(new ActivityModel("Walking", "156 cal", "20 mins", "2021-10-06 07:10 am"));
        activityModelList.add(new ActivityModel("Running", "745 cal", "78 mins", "2021-10-06 11:33 am"));
        activityModelList.add(new ActivityModel("Cycling", "452 cal", "52 mins", "2021-10-03 06:10 pm"));
        activityModelList.add(new ActivityModel("Hiking", "354 cal", "72 mins", "2021-10-03 08:56 am"));
        activityModelList.add(new ActivityModel("Tennis", "101 cal", "61 mins", "2021-10-05 11:10 pm"));
        activityModelList.add(new ActivityModel("Hockey", "64 cal", "33 mins", "2021-10-04 02:10 am"));
        activityModelList.add(new ActivityModel("Yoga", "211 cal", "58 mins", "2021-10-01 07:10 pm"));
        activityModelList.add(new ActivityModel("Fishing", "35 cal", "42 mins", "2021-10-05 05:10 am"));
        activityModelList.add(new ActivityModel("Dancing", "689 cal", "65 mins", "2021-10-03 07:10 pm"));
        activityModelList.add(new ActivityModel("Skating", "547 cal", "77 mins", "2021-10-04 09:10 am"));
        activityModelList.add(new ActivityModel("Walking", "656 cal", "120 mins", "2021-09-06 03:10 am"));
        activityModelList.add(new ActivityModel("Skating", "125 cal", "68 mins", "2021-09-21 10:23 am"));
        activityModelList.add(new ActivityModel("Yoga", "352 cal", "21 mins", "2021-10-05 09:45 pm"));
        activityModelList.add(new ActivityModel("Skating", "654 cal", "34 mins", "2021-09-28 04:23 am"));
        activityModelList.add(new ActivityModel("Walking", "141 cal", "54 mins", "2021-10-05 06:11 pm"));
        activityModelList.add(new ActivityModel("Hockey", "69 cal", "78 mins", "2021-09-04 11:34 am"));
        activityModelList.add(new ActivityModel("Walking", "213 cal", "36 mins", "2021-09-25 08:43 pm"));
        activityModelList.add(new ActivityModel("Dancing", "312 cal", "97 mins", "2021-10-05 07:13 am"));
        activityModelList.add(new ActivityModel("Walking", "539 cal", "112 mins", "2021-09-25 10:34 pm"));
        activityModelList.add(new ActivityModel("Skating", "57 cal", "38 mins", "2021-10-04 11:53 am"));
        activityModelList.add(new ActivityModel("Hockey", "321 cal", "65 mins", "2021-09-28 02:10 am"));
        activityModelList.add(new ActivityModel("Tennis", "453 cal", "35 mins", "2021-09-25 10:32 am"));
        activityModelList.add(new ActivityModel("Yoga", "234 cal", "77 mins", "2021-10-06 11:22 pm"));
        activityModelList.add(new ActivityModel("Hockey", "14 cal", "46 mins", "2021-09-28 04:43 am"));
        activityModelList.add(new ActivityModel("Hiking", "56 cal", "13 mins", "2021-10-05 06:54 pm"));
        activityModelList.add(new ActivityModel("Tennis", "133 cal", "42 mins", "2021-09-04 10:11 am"));
        activityModelList.add(new ActivityModel("Hiking", "211 cal", "24 mins", "2021-09-25 06:34 pm"));
        activityModelList.add(new ActivityModel("Fishing", "88 cal", "65 mins", "2021-10-06 01:22 am"));
        activityModelList.add(new ActivityModel("Tennis", "95 cal", "666 mins", "2021-09-27 05:45 pm"));
        activityModelList.add(new ActivityModel("Skating", "34 cal", "72 mins", "2021-10-05 03:32 am"));
        activityModelList.add(new ActivityModel("Hockey", "236 cal", "17 mins", "2021-09-01 11:10 am"));
        activityModelList.add(new ActivityModel("Dancing", "128 cal", "47 mins", "2021-09-26 02:23 am"));
        activityModelList.add(new ActivityModel("Cycling", "532 cal", "74 mins", "2021-10-02 05:45 pm"));
        activityModelList.add(new ActivityModel("Skating", "568 cal", "66 mins", "2021-09-29 08:23 am"));
        activityModelList.add(new ActivityModel("Dancing", "428 cal", "70 mins", "2021-10-03 02:11 pm"));
        activityModelList.add(new ActivityModel("Tennis", "76 cal", "123 mins", "2021-09-04 01:34 am"));
        activityModelList.add(new ActivityModel("Hiking", "77 cal", "53 mins", "2021-09-26 05:43 pm"));
        activityModelList.add(new ActivityModel("Dancing", "89 cal", "49 mins", "2021-10-01 10:13 am"));
        activityModelList.add(new ActivityModel("Fishing", "126 cal", "63 mins", "2021-09-28 05:34 pm"));
        activityModelList.add(new ActivityModel("Yoga", "128 cal", "55 mins", "2021-10-04 09:53 am"));
        return activityModelList;
    }
}
