package com.archvn.twinimagefinding

import android.widget.ProgressBar

object ActionUtils{

    fun setProgress(progressBar: ProgressBar?, startTime: Long, endTime: Long) {
        progressBar?.max = endTime.toInt()
        progressBar?.secondaryProgress = endTime.toInt()
        progressBar?.progress = startTime.toInt()
    }

    fun formatTime(millis: Int): String {
        val minutes = millis / 60
        val seconds = millis % 60

        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
    }

}