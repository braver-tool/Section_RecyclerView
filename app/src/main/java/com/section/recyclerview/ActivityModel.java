/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview;

public class ActivityModel {

    private String ActivityName;
    private String Calories;
    private String Duration;
    private String ActivityTime;
    private String headerDate;

    public ActivityModel() {
    }

    public ActivityModel(String activityName, String calories, String duration, String activityTime) {
        ActivityName = activityName;
        Calories = calories;
        Duration = duration;
        ActivityTime = activityTime;
        headerDate = "";
    }

    public String getHeaderDate() {
        return headerDate;
    }

    public void setHeaderDate(String headerDate) {
        this.headerDate = headerDate;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public String getCalories() {
        return Calories;
    }

    public String getDuration() {
        return Duration;
    }

    public String getActivityTime() {
        return ActivityTime;
    }

}

