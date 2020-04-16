package com.archvn.twinimagefinding

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.leaderboard.*

class Leaderboard : AppCompatActivity() {
    private var PRIVATE_MODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences = getSharedPreferences(Constants.BEST_TIME, PRIVATE_MODE)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.leaderboard)

        easy_mode_score.text = "00:"+ sharedPref.getInt(Constants.BEST_HIGH_EASY_MODE,20).toString()
        medium_mode_score.text = "00:"+ sharedPref.getInt(Constants.BEST_HIGH_MEDIUM_MODE,45).toString()
        hard_mode_score.text = "00:"+ sharedPref.getInt(Constants.BEST_HIGH_HARD_MODE,60).toString()

    }
}