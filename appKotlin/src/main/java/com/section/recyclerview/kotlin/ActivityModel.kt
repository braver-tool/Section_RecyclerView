/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview.kotlin

class ActivityModel {
    private var ActivityName: String? = null
    private var Calories: String? = null
    private var Duration: String? = null
    private var ActivityTime: String? = null
    private var headerDate: String? = null

    constructor() {}
    constructor(activityName: String?, calories: String?, duration: String?, activityTime: String?) {
        ActivityName = activityName
        Calories = calories
        Duration = duration
        ActivityTime = activityTime
        headerDate = ""
    }

    fun getHeaderDate(): String? {
        return headerDate
    }

    fun setHeaderDate(headerDate: String?) {
        this.headerDate = headerDate
    }

    fun getActivityName(): String? {
        return ActivityName
    }

    fun getCalories(): String? {
        return Calories
    }

    fun getDuration(): String? {
        return Duration
    }

    fun getActivityTime(): String? {
        return ActivityTime
    }
}