package com.archvn.twinimagefinding

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.GridView
import com.archvn.data.CardAdapter
import com.archvn.model.Card
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list_lang: GridView? = null
    var langaugeList: ArrayList<Card> = ArrayList<Card>()
    var adapter: CardAdapter? = null

//    val START_TIME_TN_MILLIS: Long = 600000
//    var edtTime: TextView? = null
//    var btnStart: Button? = null
//    var btnStop: Button? = null
//    var Timer: CountDownTimer? = null

    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        setContentView(R.layout.activity_main)


        list_lang= findViewById(R.id.list_lang) as GridView
        addLangData()
        adapter = CardAdapter(langaugeList, this)
        list_lang?.adapter = adapter

        val millisInFuture:Long = 50000
        val countDownInterval:Long = 1000

        // Count down timer start button
        button_start.setOnClickListener{
            // Start the timer
            timer(millisInFuture,countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
//            button_pause.isEnabled = true

            isCancelled = false
            isPaused = false
        }

        // Count down timer stop/cancel button
        button_stop.setOnClickListener{
            // Start the timer
            isCancelled = true
            isPaused = false

            it.isEnabled = false
            button_start.isEnabled = true
//            button_pause.isEnabled = false
        }

    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = "${millisUntilFinished/1000}"

                if (isPaused){
                    text_view.text = "${text_view.text}"
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                }else if (isCancelled){
                    text_view.text = "${text_view.text}"
                    cancel()
                }else{
                    text_view.text = timeRemaining
                }
            }

            override fun onFinish() {
                text_view.text = "Done"
            }
        }

    }

    private fun addLangData() {

        for(item in 1 .. 16){
            val lang = Card()
            lang.name = "Android"
            lang.img_icon=R.drawable.fortune
            langaugeList.add(lang)
        }
    }
}
