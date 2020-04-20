package com.archvn.twinimagefinding

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_menu.*

class Home : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main_menu)

        val fromTop = AnimationUtils.loadAnimation(this,R.anim.from_top)
        val fromBottom =  AnimationUtils.loadAnimation(this, R.anim.from_bottom)

        findViewById<ImageView>(R.id.imageView).startAnimation(fromTop)
        findViewById<Button>(R.id.btnEasy).startAnimation(fromBottom)
        findViewById<Button>(R.id.btnMedium).startAnimation(fromBottom)
        findViewById<Button>(R.id.btnHard).startAnimation(fromBottom)

        val sharedPrefStatus : SharedPreferences = getSharedPreferences(Constants.PREF_FILE,0)
        val editor = sharedPrefStatus.edit()
        editor.putBoolean("MEDIUM_IS_UNLOCKED",true)
        editor.putBoolean("HARD_IS_UNLOCKED",true)
        editor.apply()

        if (sharedPrefStatus.getBoolean("MEDIUM_IS_UNLOCKED",false)) {
            btnMedium.setBackgroundResource(R.drawable.btn_medium)
            btnMedium.isClickable = true
            btnMedium.isEnabled= true
        }

        if (sharedPrefStatus.getBoolean("HARD_IS_UNLOCKED",false)) {
            btnHard.setBackgroundResource(R.drawable.btn_hard)
            btnHard.isClickable = true
            btnHard.isEnabled= true
        }

        btnEasy.setOnClickListener {
            val intent = Intent(this@Home, EasyMode::class.java)
            startActivity(intent)
        }

        btnMedium.setOnClickListener {
            val intent = Intent(this@Home, MediumMode::class.java)
            startActivity(intent)
        }

        btnHard.setOnClickListener {
            val intent = Intent(this@Home, HardMode::class.java)
            startActivity(intent)
        }

        btn_leaderboard.setOnClickListener {
            val intent = Intent(this@Home, Leaderboard::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val exit = AlertDialog.Builder(this)
        exit.setCancelable(false)
        exit.setTitle("Do you really want to go?")
        exit.setPositiveButton("Yes, please.")
            { _, _ -> finish() }
        exit.setNegativeButton("No, I can stay.")
           { _, _ ->
                Toast.makeText(
                    this@Home,
                    "Welcome back!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        exit.show()
    }
}