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

        val sharedPref: SharedPreferences = getSharedPreferences(Constants.PREF_FILE, PRIVATE_MODE)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.leaderboard)

        easy_mode_score.text = formatTime(sharedPref.getInt(Constants.BEST_HIGH_EASY_MODE,30))
        medium_mode_score.text = formatTime(sharedPref.getInt(Constants.BEST_HIGH_MEDIUM_MODE,45))
        hard_mode_score.text = formatTime(sharedPref.getInt(Constants.BEST_HIGH_HARD_MODE,60))

    }

    private fun formatTime(millis: Int): String {
        val minutes = millis / 60
        val seconds = millis % 60

        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }
}