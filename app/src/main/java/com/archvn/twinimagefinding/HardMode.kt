package com.archvn.twinimagefinding

import android.app.AlertDialog
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyflipview.EasyFlipView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class HardMode : AppCompatActivity() {

    var cards: ArrayList<Int> = ArrayList<Int>()
    var cardsVisible: ArrayList<Int> = ArrayList<Int>()
    var CARDS = intArrayOf(
        R.drawable.card1,
        R.drawable.card1,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card7,
        R.drawable.card8,
        R.drawable.card9,
        R.drawable.card10,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card7,
        R.drawable.card8,
        R.drawable.card9,
        R.drawable.card10
    )

    private var PRIVATE_MODE = 0

    var firstCard: String = Constants.STR_BLANK
    var secondCard: String = Constants.STR_BLANK
    var clickFirst: Int = Constants.CLICK_FIRST
    var clickSecond: Int = Constants.CLICK_FIRST
    var cardNumber: Int = Constants.CARD_NUMBER_ONE
    var turn: Int = 0
    var RemainingTime: Long = 0
    var b: Bundle? = null


    private var isPaused = false
    private var isCancelled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        setContentView(R.layout.hard_mode)
        b = Bundle()
        b!!.putInt("level", Constants.LEVEL_HARD)

        shuffle(CARDS, Constants.HARD_NO_OF_CARDS)
        shuffle(CARDS, Constants.HARD_NO_OF_CARDS) // double shuffle

        for (card in CARDS) {
            cards!!.add(card)
        }
        addImageData()

        object : CountDownTimer(Constants.HARD_TIME, Constants.TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                if (isPaused || isCancelled) {
                    cancel()
                } else {
                    text_view.text =
                        formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())

                    RemainingTime = millisUntilFinished
                    if (turn == (Constants.HARD_NO_OF_CARDS)) {
                        b!!.putString("Data", "win")
                        var time: Long = Constants.HARD_TIME.minus(millisUntilFinished)
                            .div(Constants.TIMER_INTERVAL)
                        b!!.putInt("Time", time.toInt())
                        cancel()
                        this.onFinish()
                    }
                }
            }

            override fun onFinish() {
                if (turn < (Constants.HARD_NO_OF_CARDS)) {
                    b!!.putString("Data", "lost")
                    var time: Long = Constants.HARD_TIME.div(Constants.TIMER_INTERVAL)
                    b!!.putInt("Time", time.toInt())
                }
                // Lock All Card
                processLockAllCard()
                // Notification game result
                b!!.let { createAlertEndGame(it) }
            }
        }.start()

        /* CARD 1 */
        val img_view_front: ImageView = findViewById<ImageView>(R.id.img_front)
        img_view_front.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number)

            doStuff(1, strPosition.getText().toString())
        }

        /* CARD 2 */
        val img_view_front2: ImageView = findViewById<ImageView>(R.id.img_front2)
        img_view_front2.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number2)

            doStuff(2, strPosition.getText().toString())
        }

        /* CARD 3 */
        val img_view_front3: ImageView = findViewById<ImageView>(R.id.img_front3)
        img_view_front3.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number3)

            doStuff(3, strPosition.getText().toString())
        }

        /* CARD 4 */
        val img_view_front4: ImageView = findViewById<ImageView>(R.id.img_front4)
        img_view_front4.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number4)

            doStuff(4, strPosition.getText().toString())
        }

        /* CARD 5 */
        val img_view_front5: ImageView = findViewById<ImageView>(R.id.img_front5)
        img_view_front5.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number5)

            doStuff(5, strPosition.getText().toString())
        }

        /* CARD 6 */
        val img_view_front6: ImageView = findViewById<ImageView>(R.id.img_front6)
        img_view_front6.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number6)

            doStuff(6, strPosition.getText().toString())
        }

        /* CARD 7 */
        val img_view_front7: ImageView = findViewById<ImageView>(R.id.img_front7)
        img_view_front7.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number7)

            doStuff(7, strPosition.getText().toString())
        }

        /* CARD 8 */
        val img_view_front8: ImageView = findViewById<ImageView>(R.id.img_front8)
        img_view_front8.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number8)

            doStuff(8, strPosition.getText().toString())
        }

        /* CARD 9 */
        val img_view_front9: ImageView = findViewById<ImageView>(R.id.img_front9)
        img_view_front9.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number9)

            doStuff(9, strPosition.getText().toString())
        }

        /* CARD 10 */
        val img_view_front10: ImageView = findViewById<ImageView>(R.id.img_front10)
        img_view_front10.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number10)

            doStuff(10, strPosition.getText().toString())
        }

        /* CARD 11 */
        val img_view_front11: ImageView = findViewById<ImageView>(R.id.img_front11)
        img_view_front11.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number11)

            doStuff(11, strPosition.getText().toString())
        }

        /* CARD 12 */
        val img_view_front12: ImageView = findViewById<ImageView>(R.id.img_front12)
        img_view_front12.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number12)

            doStuff(12, strPosition.getText().toString())
        }

        /* CARD 13 */
        val img_view_front13: ImageView = findViewById<ImageView>(R.id.img_front13)
        img_view_front13.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number13)

            doStuff(13, strPosition.getText().toString())
        }

        /* CARD 14 */
        val img_view_front14: ImageView = findViewById<ImageView>(R.id.img_front14)
        img_view_front14.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number14)

            doStuff(14, strPosition.getText().toString())
        }

        /* CARD 15 */
        val img_view_front15: ImageView = findViewById<ImageView>(R.id.img_front15)
        img_view_front15.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number15)

            doStuff(15, strPosition.getText().toString())
        }

        /* CARD 16 */
        val img_view_front16: ImageView = findViewById<ImageView>(R.id.img_front16)
        img_view_front16.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number16)

            doStuff(16, strPosition.getText().toString())
        }

        /* CARD 17 */
        val img_view_front17: ImageView = findViewById<ImageView>(R.id.img_front17)
        img_view_front17.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number17)

            doStuff(17, strPosition.getText().toString())
        }

        /* CARD 18 */
        val img_view_front18: ImageView = findViewById<ImageView>(R.id.img_front18)
        img_view_front18.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number18)

            doStuff(18, strPosition.getText().toString())
        }

        /* CARD 19 */
        val img_view_front19: ImageView = findViewById<ImageView>(R.id.img_front19)
        img_view_front19.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number19)

            doStuff(19, strPosition.getText().toString())
        }

        /* CARD 20 */
        val img_view_front20: ImageView = findViewById<ImageView>(R.id.img_front20)
        img_view_front20.setOnClickListener {
            var strPosition: TextView = findViewById<TextView>(R.id.tv_number20)

            doStuff(20, strPosition.getText().toString())
        }
    }

    override fun onBackPressed() {
        isPaused = true
        val pause =
            AlertDialog.Builder(this)
        pause.setTitle("Game paused")
        pause.setMessage("Do you want to quit ?")
        pause.setCancelable(false)
        pause.setPositiveButton(
            "Resume"
        ) { dialog, which ->
            isPaused = false
            object : CountDownTimer(RemainingTime, Constants.TIMER_INTERVAL) {
                var time = 0
                override fun onTick(millisUntilFinished: Long) {
                    if (isPaused || isCancelled) {
                        cancel()
                    } else {
                        text_view.text =
                            formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                        RemainingTime = millisUntilFinished
                        if (turn == (Constants.HARD_NO_OF_CARDS)) {
                            b!!.putString("Data", "win")
                            var time: Long = Constants.HARD_TIME.minus(millisUntilFinished)
                                .div(Constants.TIMER_INTERVAL)
                            b!!.putInt("Time", time.toInt())
                            cancel()
                            this.onFinish()
                        }
                    }
                }

                override fun onFinish() {
                    if (turn < (Constants.HARD_NO_OF_CARDS)) {
                        b!!.putString("Data", "lost")
                        var time: Long = Constants.HARD_TIME.div(Constants.TIMER_INTERVAL)
                        b!!.putInt("Time", time.toInt())
                    }
                    // Lock All Card
                    processLockAllCard()
                    // Notification game result
                    b!!.let { createAlertEndGame(it) }
                }
            }.start()
        }
        pause.setNegativeButton(
            "Quit"
        ) { dialog, which ->
            isCancelled = true
            finish()
        }
        pause.show()
    }

    override fun onUserLeaveHint() {
        isPaused = true
        val pause =
            AlertDialog.Builder(this)
        pause.setTitle("Game paused")
        pause.setMessage("Do you want to quit ?")
        pause.setCancelable(false)
        pause.setPositiveButton(
            "Resume"
        ) { dialog, which ->
            isPaused = false
            object : CountDownTimer(RemainingTime, Constants.TIMER_INTERVAL) {
                var time = 0
                override fun onTick(millisUntilFinished: Long) {
                    if (isPaused || isCancelled) {
                        cancel()
                    } else {
                        text_view.text =
                            formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                        RemainingTime = millisUntilFinished
                        if (turn == (Constants.HARD_NO_OF_CARDS)) {
                            b!!.putString("Data", "win")
                            var time: Long = Constants.HARD_TIME.minus(millisUntilFinished)
                                .div(Constants.TIMER_INTERVAL)
                            b!!.putInt("Time", time.toInt())
                            cancel()
                            this.onFinish()
                        }
                    }
                }

                override fun onFinish() {
                    if (turn < (Constants.HARD_NO_OF_CARDS)) {
                        b!!.putString("Data", "lost")
                        var time: Long = Constants.HARD_TIME.div(Constants.TIMER_INTERVAL)
                        b!!.putInt("Time", time.toInt())
                    }
                    // Lock All Card
                    processLockAllCard()
                    // Notification GAME OVER
                    b!!.let { createAlertEndGame(it) }
                }
            }.start()
        }
        pause.setNegativeButton(
            "Quit"
        ) { dialog, which ->
            isCancelled = true
            finish()
        }
        pause.show()
    }

    fun shuffle(cards: IntArray, n: Int) {
        val random = Random()
        for (i in 0 until n) {
            val r: Int = random.nextInt(n - i)
            val temp = cards[r]
            cards[r] = cards[i]
            cards[i] = temp
        }
    }

    fun doStuff(posCard: Int, strPosition: String) {

        // Lan click dau tien
        if (Constants.CARD_NUMBER_ONE == cardNumber && clickFirst != posCard) {
            firstCard = strPosition                 // Set gia tri can so sanh cho card dau tien
            clickFirst = posCard                    // Set vi tri card dau tien
            cardNumber = Constants.CARD_NUMBER_TWO  // So cacd se tang len 2

            // Lock FIRST CARD (Co flip card)
            processOpenLockVisibleCard(clickFirst, Constants.MODE_LOCK_CARD)
        }
        // Lan click thu hai
        else if (Constants.CARD_NUMBER_TWO == cardNumber) {

            secondCard = strPosition                // Set gia tri can so sanh cho card thu 2
            clickSecond = posCard                   // Set vi tri card thu 2

            // Lock Card thu 2 (Co flip card)
            processOpenLockVisibleCard(clickSecond, Constants.MODE_LOCK_CARD)

            // Lock Toan bo tat ca cac CARD (Khong co FLIP CARD)
            for (pos in 1..Constants.HARD_NO_OF_CARDS) {

                processOpenLockVisibleCard(pos, Constants.MODE_LOCK_CARD_NO_FLIP)
            }

            // Hander de tam dung man hinh de flip card va tinh toan
            Handler().postDelayed({
                caculate()
            }, Constants.TIME_HANDLER)
        }
    }

    // Function kiem tra 2 card giong nhau
    fun caculate() {

        // Truong hop 2 card giong nhau
        if (firstCard.equals(secondCard)) {

            // Hien tai se khoa 2 card theo clickFirst va clickSecond
            processOpenLockVisibleCard(clickFirst, Constants.MODE_VISIBLE_CARD)
            processOpenLockVisibleCard(clickSecond, Constants.MODE_VISIBLE_CARD)

            // Add card -> card Visible
            cardsVisible.add(clickFirst)
            cardsVisible.add(clickSecond)

            // Tang luot dem de kiem tra ket thuc game
            turn += 2
        }

        cardNumber = Constants.CARD_NUMBER_ONE
        firstCard = Constants.STR_BLANK
        secondCard = Constants.STR_BLANK
        clickFirst = Constants.CLICK_FIRST
        clickSecond = Constants.CLICK_FIRST

        // Mo tat ca card chua duoc add vao cardVisible (Card chua match)
        for (pos in 1..Constants.HARD_NO_OF_CARDS) {
            if (!checkCardVisible(pos)) {

                // Demo Function moi
                processOpenLockVisibleCard(pos, Constants.MODE_OPEN_CARD)
            }
        }

        // Kiem tra ket thuc
        checkEnd()
    }

    // Create Alert
    // WIN: FLIP ALL CARD IN TIME
    // LOSE: FULL TIME
    fun createAlertEndGame(b: Bundle) {
        var pref: SharedPreferences = getSharedPreferences(Constants.BEST_TIME, PRIVATE_MODE)
        var editor = pref.edit()

        var bestHardScore = pref.getInt(Constants.BEST_HIGH_HARD_MODE, 60)

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.result_dialog, null)
        builder.setView(dialogView)

        if (b.getString("Data") == "win") {

            if (Integer.valueOf(b["Time"].toString()) < bestHardScore) {
                editor.putInt(
                    Constants.BEST_HIGH_HARD_MODE,
                    Integer.valueOf(b["Time"].toString())
                ).apply()
                dialogView.findViewById<TextView>(R.id.newhigh).text = "New High Score!"
                dialogView.findViewById<TextView>(R.id.newhigh).setTextColor(Color.RED)
            }

            dialogView.findViewById<TextView>(R.id.status).text = Constants.MESSAGE_WIN
            dialogView.findViewById<TextView>(R.id.status).setTextColor(Color.BLUE)
            dialogView.findViewById<TextView>(R.id.status).setTextSize(40F)
            dialogView.findViewById<TextView>(R.id.time).text =
                "Your time: " + formatTime(b["Time"].toString().toInt())
        } else {
            dialogView.findViewById<TextView>(R.id.status).text = Constants.MESSAGE_LOSE
            dialogView.findViewById<TextView>(R.id.status).setTextColor(Color.RED)
            dialogView.findViewById<TextView>(R.id.status).setTextSize(40F)
            dialogView.findViewById<TextView>(R.id.time).text =
                "Your time: " + formatTime(b["Time"].toString().toInt())
        }
        builder.setPositiveButton("Close") { dialog, which ->
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }


    // Kiem tra CARD ton tai trong list Match
    fun checkCardVisible(positionCurrent: Int): Boolean {
        for (pos in 0..cardsVisible.size - 1) {
            if (positionCurrent == cardsVisible.get(pos)) {
                return true
            }
        }
        return false
    }

    // Kiem tra ket thuc game
    fun checkEnd() {

        if ((Constants.HARD_NO_OF_CARDS) == turn) {
            processVisibleAllCard()
        }
    }

    // Function OPEN, LOCK, VISIBLE CARD BY POSITION AND MODE_OPEN, MODE_LOCK, MODE_VISIBLE
    fun processOpenLockVisibleCard(position: Int, modeCard: Int) {

        var isEnabled: Boolean = false
        when (position) {
            1 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                // 1 - MODE_OPEN_CARD
                // 2 - Back Side k duoc hien thi -> easyFlipView.isBackSide = true
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                // 1 - MODE_LOCK_CARD
                // 2 - Back Side duoc hien thi -> easyFlipView.isBackSide = false
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            2 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back2)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front2)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView2)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            3 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back3)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front3)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView3)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            4 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back4)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front4)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView4)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            5 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back5)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front5)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView5)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            6 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back6)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front6)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView6)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            7 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back7)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front7)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView7)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }
                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            8 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back8)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front8)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView8)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            9 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back9)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front9)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView9)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            10 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back10)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front10)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView10)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            11 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back11)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front11)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView11)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            12 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back12)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front12)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView12)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            13 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back13)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front13)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView13)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            14 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back14)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front14)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView14)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            15 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back15)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front15)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView15)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            16 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back16)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front16)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView16)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            17 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back17)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front17)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView17)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            18 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back18)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front18)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView18)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            19 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back19)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front19)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView19)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            20 -> {
                // Lock Card Or Open Card
                var imageView_back: ImageView = findViewById<ImageView>(R.id.img_back20)
                var imageView_front: ImageView = findViewById<ImageView>(R.id.img_front20)
                // Flip Card
                var easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView20)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide == true) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (easyFlipView.isBackSide == false) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
        }
    }

    // Truong hop can lock toan bo card
    // Truong hop ket thuc game, Flip Card
    fun processVisibleAllCard() {
        for (pos in 1..Constants.HARD_NO_OF_CARDS) {

            processOpenLockVisibleCard(pos, Constants.MODE_VISIBLE_CARD)
        }
    }

    fun processLockAllCard() {
        for (pos in 1..Constants.HARD_NO_OF_CARDS) {

            processOpenLockVisibleCard(pos, Constants.MODE_VISIBLE_CARD)
        }
    }

    private fun addImageData() {

        // Card 1
        findViewById<TextView>(R.id.tv_number).setText(cards[0])
        findViewById<ImageView>(R.id.img_back).setImageResource(cards[0])

        //Card 2
        findViewById<TextView>(R.id.tv_number2).setText(cards[1])
        findViewById<ImageView>(R.id.img_back2).setImageResource(cards[1])

        // Card 3
        findViewById<TextView>(R.id.tv_number3).setText(cards[2])
        findViewById<ImageView>(R.id.img_back3).setImageResource(cards[2])

        //Card 4
        findViewById<TextView>(R.id.tv_number4).setText(cards[3])
        findViewById<ImageView>(R.id.img_back4).setImageResource(cards[3])

        // Card 5
        findViewById<TextView>(R.id.tv_number5).setText(cards[4])
        findViewById<ImageView>(R.id.img_back5).setImageResource(cards[4])

        // Card 6
        findViewById<TextView>(R.id.tv_number6).setText(cards[5])
        findViewById<ImageView>(R.id.img_back6).setImageResource(cards[5])

        // Card 7
        findViewById<TextView>(R.id.tv_number7).setText(cards[6])
        findViewById<ImageView>(R.id.img_back7).setImageResource(cards[6])

        // Card 8
        findViewById<TextView>(R.id.tv_number8).setText(cards[7])
        findViewById<ImageView>(R.id.img_back8).setImageResource(cards[7])

        // Card 9
        findViewById<TextView>(R.id.tv_number9).setText(cards[8])
        findViewById<ImageView>(R.id.img_back9).setImageResource(cards[8])

        // Card 10
        findViewById<TextView>(R.id.tv_number10).setText(cards[9])
        findViewById<ImageView>(R.id.img_back10).setImageResource(cards[9])

        // Card 11
        findViewById<TextView>(R.id.tv_number11).setText(cards[10])
        findViewById<ImageView>(R.id.img_back11).setImageResource(cards[10])

        //Card 12
        findViewById<TextView>(R.id.tv_number12).setText(cards[11])
        findViewById<ImageView>(R.id.img_back12).setImageResource(cards[11])

        // Card 13
        findViewById<TextView>(R.id.tv_number13).setText(cards[12])
        findViewById<ImageView>(R.id.img_back13).setImageResource(cards[12])

        //Card 14
        findViewById<TextView>(R.id.tv_number14).setText(cards[13])
        findViewById<ImageView>(R.id.img_back14).setImageResource(cards[13])

        // Card 15
        findViewById<TextView>(R.id.tv_number15).setText(cards[14])
        findViewById<ImageView>(R.id.img_back15).setImageResource(cards[14])

        // Card 16
        findViewById<TextView>(R.id.tv_number16).setText(cards[15])
        findViewById<ImageView>(R.id.img_back16).setImageResource(cards[15])

        // Card 17
        findViewById<TextView>(R.id.tv_number17).setText(cards[16])
        findViewById<ImageView>(R.id.img_back17).setImageResource(cards[16])

        // Card 18
        findViewById<TextView>(R.id.tv_number18).setText(cards[17])
        findViewById<ImageView>(R.id.img_back18).setImageResource(cards[17])

        // Card 19
        findViewById<TextView>(R.id.tv_number19).setText(cards[18])
        findViewById<ImageView>(R.id.img_back19).setImageResource(cards[18])

        // Card 20
        findViewById<TextView>(R.id.tv_number20).setText(cards[19])
        findViewById<ImageView>(R.id.img_back20).setImageResource(cards[19])
    }

    private fun formatTime(millis: Int): String {
        val minutes = millis / 60
        val seconds = millis % 60

        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);

    }
}
