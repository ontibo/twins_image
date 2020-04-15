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

        val sharedPref: SharedPreferences = getSharedPreferences("BEST_TIME", PRIVATE_MODE)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.leaderboard)

        easy_mode_score.text = sharedPref.getString("EASY_MODE","00:40")
        medium_mode_score.text = sharedPref.getString("MEDIUM_MODE","00:30")
        hard_mode_score.text = sharedPref.getString("HARD","00:20")

    }
}