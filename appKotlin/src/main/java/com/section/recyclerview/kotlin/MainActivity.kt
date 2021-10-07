/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview.kotlin

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var context: Context? = null
    private lateinit var activityRecyclerView: RecyclerView
    private var sortByIndex = 0
    private var activityModelList: MutableList<ActivityModel?>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sortByImageView -> showSortByOptionDialog()
            R.id.gitHubLogoImageView -> navigateSourcePage()
        }
    }

    /**
     * Initialize views from XML
     */
    private fun initializeUI() {
        context = this@MainActivity
        activityRecyclerView = findViewById(R.id.activityRecyclerView)
        val sortByImageView = findViewById<AppCompatImageView?>(R.id.sortByImageView)
        val gitHubLogoImageView = findViewById<GitHubLogoView?>(R.id.gitHubLogoImageView)
        val layoutViewManager = LinearLayoutManager(this)
        layoutViewManager.orientation = LinearLayoutManager.VERTICAL
        activityRecyclerView.layoutManager = layoutViewManager
        activityRecyclerView.setHasFixedSize(true)
        sortByImageView.setOnClickListener(this)
        gitHubLogoImageView.setOnClickListener(this)
        gitHubLogoImageView.animation = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom)
        populateRecyclerView()
    }

    /**
     * Method used to Load RecyclerView initially
     */
    private fun populateRecyclerView() {
        activityModelList = AppUtils.getSampleActivities()
        for (h in activityModelList!!.indices) {
            val headerDate = changeDateFormat(activityModelList!![h]?.getActivityTime())
            activityModelList!![h]?.setHeaderDate(headerDate)
        }
        AppUtils.getDateSortedList(activityModelList)
        setAdapter()
    }

    /**
     * Setting Recycler Adapter
     */
    private fun setAdapter() {
        if (activityModelList!!.size > 0) {
            val activitiesAdapter = ActivitiesAdapter(context, activityModelList, sortByIndex)
            activityRecyclerView.adapter = activitiesAdapter
        }
    }

    /**
     * Navigate to Github Source path
     */
    private fun navigateSourcePage() {
        if (AppUtils.isNetworkAvailable(this)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(AppUtils.GITHUB_URL)
            startActivity(intent)
        }
    }

    /**
     * Method used to show Alert Dialog with sort by options with user interaction
     */
    private fun showSortByOptionDialog() {
        val sortOptionList = ArrayList<String?>()
        sortOptionList.add("Date")
        sortOptionList.add("Activity Name")
        AlertDialog.Builder(this).setTitle("SortBy").setSingleChoiceItems(sortOptionList.toTypedArray<CharSequence?>(), sortByIndex) { dialog: DialogInterface?, index: Int ->
            dialog!!.dismiss()
            if (index != sortByIndex) {
                sortByIndex = index
                if (sortByIndex == 0) {
                    AppUtils.getDateSortedList(activityModelList)
                } else {
                    AppUtils.getNameSortedList(activityModelList)
                }
                setAdapter()
            }
        }.create().show()
    }

    /**
     * @param givenDate - String date ~ Activity Date
     * @return - String date as 'MMM dd, yyyy' format
     */
    private fun changeDateFormat(givenDate: String?): String? {
        var givenDate = givenDate
        var resultDate: Date?
        return try {
            resultDate = AppUtils.date_format.parse(givenDate)
            AppUtils.date_format_1.format(resultDate)
        } catch (e: ParseException) {
            if (Build.MODEL.lowercase(Locale.getDefault()).contains("moto") && (givenDate!!.lowercase(Locale.getDefault()).contains("am") || givenDate.lowercase(Locale.getDefault()).contains("pm"))) {
                try {
                    givenDate = givenDate.lowercase(Locale.getDefault()).replace("am", "a.m.").replace("pm", "p.m.")
                    resultDate = AppUtils.date_format.parse(givenDate)
                    if (resultDate != null) {
                        return AppUtils.date_format_1.format(resultDate)
                    }
                } catch (e1: ParseException) {
                    Log.e("##callDateFormatChangeMethod", "-------->" + e1.message)
                }
            }
            givenDate
        }
    }
}