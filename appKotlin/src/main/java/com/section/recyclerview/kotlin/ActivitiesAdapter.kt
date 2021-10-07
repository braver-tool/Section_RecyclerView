/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.section.recyclerview.kotlin.ActivitiesAdapter.MyViewHolder

class ActivitiesAdapter(private val context: Context?, private val activityModelList: MutableList<ActivityModel?>?, private val sortByIndex: Int) : RecyclerView.Adapter<MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_activities, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = activityModelList!![position]
        if (model != null) {
            val activityName = model.getActivityName()
            val activityId = AppUtils.getActivityID(activityName)
            val activityIcon = AppUtils.getActivityImage(context, activityId)
            val activityColor = AppUtils.getActivityColor(context, activityId)
            if (sortByIndex == 0) {
                holder.dateHeaderTextView?.text = model.getHeaderDate()
                holder.dateHeaderTextView?.visibility = if (position != 0 && model.getHeaderDate() == activityModelList[position - 1]?.getHeaderDate()) View.GONE else View.VISIBLE
            } else {
                holder.dateHeaderTextView?.text = activityName
                holder.dateHeaderTextView?.visibility = if (position != 0 && model.getActivityName() == activityModelList[position - 1]?.getActivityName()) View.GONE else View.VISIBLE
            }
            holder.activityDateTimeTextView?.text = model.getActivityTime()
            holder.activityNameTextView?.text = activityName
            holder.caloriesTextView?.text = model.getCalories()
            holder.durationTextView?.text = model.getDuration()
            holder.activityImageView?.setImageResource(activityIcon)
            holder.activityColorBgLayout?.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, activityColor) }
        }
    }

    override fun getItemCount(): Int {
        return activityModelList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class MyViewHolder internal constructor(v: View?) : RecyclerView.ViewHolder(v!!) {
        val dateHeaderTextView: AppCompatTextView? = v!!.findViewById(R.id.dateHeaderTextView)
        val activityNameTextView: AppCompatTextView? = v!!.findViewById(R.id.activityNameTextView)
        val caloriesTextView: AppCompatTextView? = v!!.findViewById(R.id.caloriesTextView)
        val durationTextView: AppCompatTextView? = v!!.findViewById(R.id.durationTextView)
        val activityImageView: AppCompatImageView? = v!!.findViewById(R.id.activityImageView)
        val activityDateTimeTextView: AppCompatTextView? = v!!.findViewById(R.id.activityDateTimeTextView)
        val activityColorBgLayout: LinearLayout? = v!!.findViewById(R.id.activityColorBgLayout)

    }
}