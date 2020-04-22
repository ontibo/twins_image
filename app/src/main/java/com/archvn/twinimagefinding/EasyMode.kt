package com.archvn.twinimagefinding

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wajahatkarim3.easyflipview.EasyFlipView
import kotlinx.android.synthetic.main.easy_mode.*
import java.util.*
import kotlin.collections.ArrayList

class EasyMode : AppCompatActivity() {

    private var cards: ArrayList<Int> = ArrayList<Int>()
    private var cardsVisible: ArrayList<Int> = ArrayList<Int>()
    private var CARDS = intArrayOf(
        R.drawable.card1,
        R.drawable.card1,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6
    )

    private var firstCard: String = Constants.STR_BLANK
    private var secondCard: String = Constants.STR_BLANK
    private var clickFirst: Int = Constants.CLICK_FIRST
    private var clickSecond: Int = Constants.CLICK_FIRST
    private var cardNumber: Int = Constants.CARD_NUMBER_ONE
    private var turn: Int = 0
    private var remainingTime: Long = 0
    private var b: Bundle? = null
    private var progressBar: ProgressBar? = null

    private var isPaused = false
    private var isBackKeyPressed = false
    private var isCancelled = false
    private var isEnd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        setContentView(R.layout.easy_mode)
        b = Bundle()
        b!!.putInt("level", Constants.LEVEL_EASY)
        progressBar =  findViewById(R.id.progressBar)

        shuffle(CARDS, Constants.EASY_NO_OF_CARDS)
        shuffle(CARDS, Constants.EASY_NO_OF_CARDS) // double shuffle

        for (card in CARDS) {
            cards.add(card)
        }
        addImageData()

        object : CountDownTimer(Constants.EASY_TIME, Constants.PROGRESS_BAR_TIMER_INTERVAL) {
            var progress = 0L
            override fun onTick(millisUntilFinished: Long) {
                if (isPaused || isCancelled) {
                    cancel()
                } else {
                    timer.text =
                        ActionUtils.formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                    remainingTime = millisUntilFinished
                    progress = Constants.EASY_TIME - millisUntilFinished
                    ActionUtils.setProgress(progressBar, progress,Constants.EASY_TIME)

                    if (turn == (Constants.EASY_NO_OF_CARDS)) {
                        b!!.putString("Data", "win")
                        val time: Long = Constants.EASY_TIME.minus(millisUntilFinished)
                            .div(Constants.TIMER_INTERVAL)
                        b!!.putInt("Time", time.toInt())
                        cancel()
                        this.onFinish()
                    }
                }
            }

            override fun onFinish() {
                if (turn < (Constants.EASY_NO_OF_CARDS)) {
                    b!!.putString("Data", "lost")
                    val time: Long = Constants.EASY_TIME.div(Constants.TIMER_INTERVAL)
                    b!!.putInt("Time", time.toInt())
                    ActionUtils.setProgress(progressBar, progress,Constants.EASY_TIME)
                }
                // Lock All Card
                processLockAllCard()
                ActionUtils.setProgress(progressBar,progress,Constants.EASY_TIME)
                // Notification GAME OVER
                createAlertEndGame(b!!)
            }
        }.start()

        /* CARD 1 */
        val imgViewFront: ImageView = findViewById(R.id.img_front)
        imgViewFront.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number)

            doStuff(1, strPosition.text.toString())
        }

        /* CARD 2 */
        val imgViewFront2: ImageView = findViewById(R.id.img_front2)
        imgViewFront2.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number2)

            doStuff(2, strPosition.text.toString())
        }

        /* CARD 3 */
        val imgViewFront3: ImageView = findViewById(R.id.img_front3)
        imgViewFront3.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number3)

            doStuff(3, strPosition.text.toString())
        }

        /* CARD 4 */
        val imgViewFront4: ImageView = findViewById(R.id.img_front4)
        imgViewFront4.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number4)

            doStuff(4, strPosition.text.toString())
        }

        /* CARD 5 */
        val imgViewFront5: ImageView = findViewById(R.id.img_front5)
        imgViewFront5.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number5)

            doStuff(5, strPosition.text.toString())
        }

        /* CARD 6 */
        val imgViewFront6: ImageView = findViewById(R.id.img_front6)
        imgViewFront6.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number6)

            doStuff(6, strPosition.text.toString())
        }

        /* CARD 7 */
        val imgViewFront7: ImageView = findViewById(R.id.img_front7)
        imgViewFront7.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number7)

            doStuff(7, strPosition.text.toString())
        }

        /* CARD 8 */
        val imgViewFront8: ImageView = findViewById(R.id.img_front8)
        imgViewFront8.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number8)

            doStuff(8, strPosition.text.toString())
        }

        /* CARD 9 */
        val imgViewFront9: ImageView = findViewById(R.id.img_front9)
        imgViewFront9.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number9)

            doStuff(9, strPosition.text.toString())
        }

        /* CARD 10 */
        val imgViewFront10: ImageView = findViewById(R.id.img_front10)
        imgViewFront10.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number10)

            doStuff(10, strPosition.text.toString())
        }

        /* CARD 11 */
        val imgViewFront11: ImageView = findViewById(R.id.img_front11)
        imgViewFront11.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number11)

            doStuff(11, strPosition.text.toString())
        }

        /* CARD 12 */
        val imgViewFront12: ImageView = findViewById(R.id.img_front12)
        imgViewFront12.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number12)

            doStuff(12, strPosition.text.toString())
        }
    }

    override fun onBackPressed() {
        isPaused = true
        isBackKeyPressed = true
        val pause =
            AlertDialog.Builder(this)
        pause.setTitle("Game paused")
        pause.setMessage("Do you want to quit ?")
        pause.setCancelable(false)

        pause.setPositiveButton(
            "Resume"
        ) { _, _ ->
            isPaused = false
            isBackKeyPressed = false
            object : CountDownTimer(remainingTime, Constants.PROGRESS_BAR_TIMER_INTERVAL) {
                var progress = 0L
                override fun onTick(millisUntilFinished: Long) {
                    if (isPaused || isCancelled) {
                        cancel()
                    } else {
                        timer.text =
                            ActionUtils.formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                        remainingTime = millisUntilFinished
                        progress = Constants.EASY_TIME - millisUntilFinished
                        ActionUtils.setProgress(progressBar, progress,Constants.EASY_TIME)
                        if (turn == (Constants.EASY_NO_OF_CARDS)) {
                            b!!.putString("Data", "win")
                            val time: Long = Constants.EASY_TIME.minus(millisUntilFinished)
                                .div(Constants.TIMER_INTERVAL)
                            b!!.putInt("Time", time.toInt())
                            cancel()
                            this.onFinish()
                        }
                    }
                }

                override fun onFinish() {
                    if (turn < (Constants.EASY_NO_OF_CARDS)) {
                        b!!.putString("Data", "lost")
                        val time: Long = Constants.EASY_TIME.div(Constants.TIMER_INTERVAL)
                        b!!.putInt("Time", time.toInt())
                        ActionUtils.setProgress(progressBar,progress,Constants.EASY_TIME)
                    }
                    // Lock All Card
                    processLockAllCard()
                    // Notification game result
                    createAlertEndGame(b!!)
                }
            }.start()
        }
        pause.setNegativeButton(
            "Quit"
        ) { _, _ ->
            isCancelled = true
            finish()
            startActivity(Intent(this,Home::class.java))
        }
        pause.show()
    }

    override fun onUserLeaveHint() {
        isPaused = true
        if (!isEnd && !isBackKeyPressed) {
            val pause =
                AlertDialog.Builder(this)
            pause.setTitle("Game paused")
            pause.setMessage("Do you want to quit ?")
            pause.setCancelable(false)
            pause.setPositiveButton(
                "Resume"
            ) { _, _ ->
                isPaused = false
                object : CountDownTimer(remainingTime, Constants.PROGRESS_BAR_TIMER_INTERVAL) {
                    var progress = 0L
                    override fun onTick(millisUntilFinished: Long) {
                        if (isPaused || isCancelled) {
                            cancel()
                        } else {
                            timer.text =
                                ActionUtils.formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                            remainingTime = millisUntilFinished
                            progress = Constants.EASY_TIME - millisUntilFinished
                            ActionUtils.setProgress(progressBar, progress,Constants.EASY_TIME)
                            if (turn == (Constants.EASY_NO_OF_CARDS)) {
                                b!!.putString("Data", "win")
                                val time: Long = Constants.EASY_TIME.minus(millisUntilFinished)
                                    .div(Constants.TIMER_INTERVAL)
                                b!!.putInt("Time", time.toInt())
                                cancel()
                                this.onFinish()
                            }
                        }
                    }

                    override fun onFinish() {
                        if (turn < (Constants.EASY_NO_OF_CARDS)) {
                            b!!.putString("Data", "lost")
                            val time: Long = Constants.EASY_TIME.div(Constants.TIMER_INTERVAL)
                            b!!.putInt("Time", time.toInt())
                            ActionUtils.setProgress(progressBar,progress,Constants.EASY_TIME)
                        }
                        // Lock All Card
                        processLockAllCard()
                        // Notification game result
                        createAlertEndGame(b!!)
                    }
                }.start()
            }
            pause.setNegativeButton(
                "Quit"
            ) { _, _ ->
                isCancelled = true
                finish()
                startActivity(Intent(this,Home::class.java))
            }
            pause.show()
        }
    }

    private fun shuffle(cards: IntArray, n: Int) {
        val random = Random()
        for (i in 0 until n) {
            val r: Int = random.nextInt(n - i)
            val temp = cards[r]
            cards[r] = cards[i]
            cards[i] = temp
        }
    }

    private fun doStuff(posCard: Int, strPosition: String) {

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
            for (pos in 1..Constants.EASY_NO_OF_CARDS) {

                processOpenLockVisibleCard(pos, Constants.MODE_LOCK_CARD_NO_FLIP)
            }

            // Hander de tam dung man hinh de flip card va tinh toan
            Handler().postDelayed({
                calculate()
            }, Constants.TIME_HANDLER)
        }
    }

    // Function kiem tra 2 card giong nhau
    private fun calculate() {

        // Truong hop 2 card giong nhau
        if (firstCard == secondCard) {

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
        for (pos in 1..Constants.EASY_NO_OF_CARDS) {
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
    @SuppressLint("InflateParams", "SetTextI18n")
    fun createAlertEndGame(b: Bundle) {
        val pref = getSharedPreferences(Constants.PREF_FILE, Constants.PRIVATE_MODE)
        val editor = pref.edit()

        val bestEasyScore = pref.getInt(Constants.BEST_HIGH_EASY_MODE, 30)

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.result_dialog, null)
        dialogView.fitsSystemWindows
        builder.setView(dialogView)
        builder.setCancelable(false)

        if (b.getString("Data") == "win") {
            if (Integer.valueOf(b["Time"].toString()) < bestEasyScore) {
                editor.putInt(
                    Constants.BEST_HIGH_EASY_MODE,
                    Integer.valueOf(b["Time"].toString())
                ).apply()
                dialogView.findViewById<TextView>(R.id.newhigh).text =
                    getString(R.string.new_high_score)
                dialogView.findViewById<TextView>(R.id.newhigh).setTextColor(Color.RED)
            }

            dialogView.findViewById<TextView>(R.id.status).text = Constants.MESSAGE_WIN
            dialogView.findViewById<TextView>(R.id.status).setTextColor(Color.BLUE)
            dialogView.findViewById<TextView>(R.id.status).textSize = 40F
            dialogView.findViewById<TextView>(R.id.time).text =
                getString(R.string.your_time) + " " + ActionUtils.formatTime(b["Time"].toString().toInt())
            if (15 >= Integer.valueOf(b["Time"].toString())) {
                editor.putBoolean(
                    "MEDIUM_IS_UNLOCKED",
                    true
                ).apply()
            }
        } else {
            dialogView.findViewById<TextView>(R.id.status).text = Constants.MESSAGE_LOSE
            dialogView.findViewById<TextView>(R.id.status).setTextColor(Color.RED)
            dialogView.findViewById<TextView>(R.id.status).textSize = 40F
            dialogView.findViewById<TextView>(R.id.time).text =
                getString(R.string.your_time) + " " + ActionUtils.formatTime(b["Time"].toString().toInt())
        }
        builder.setNegativeButton("Close") { _, _ ->
            finish()
            startActivity(Intent(this,Home::class.java))
        }
        val dialog = builder.create()
        dialog.show()
        isEnd = true
    }


    // Kiem tra CARD ton tai trong list Match
    private fun checkCardVisible(positionCurrent: Int): Boolean {
        for (pos in 0 until cardsVisible.size) {
            if (positionCurrent == cardsVisible[pos]) {
                return true
            }
        }
        return false
    }

    // Kiem tra ket thuc game
    private fun checkEnd() {

        if ((Constants.EASY_NO_OF_CARDS) == turn) {
            processVisibleAllCard()
        }
    }

    // Function OPEN, LOCK, VISIBLE CARD BY POSITION AND MODE_OPEN, MODE_LOCK, MODE_VISIBLE
    private fun processOpenLockVisibleCard(position: Int, modeCard: Int) {

        var isEnabled = false
        when (position) {
            1 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                // 1 - MODE_OPEN_CARD
                // 2 - Back Side k duoc hien thi -> easyFlipView.isBackSide = true
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                // 1 - MODE_LOCK_CARD
                // 2 - Back Side duoc hien thi -> easyFlipView.isBackSide = false
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            2 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back2)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front2)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView2)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            3 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back3)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front3)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView3)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            4 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back4)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front4)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView4)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            5 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back5)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front5)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView5)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            6 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back6)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front6)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView6)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled

            }
            7 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back7)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front7)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView7)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            8 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back8)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front8)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView8)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            9 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back9)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front9)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView9)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            10 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back10)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front10)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView10)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            11 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back11)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front11)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView11)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }

                imageView_back.isEnabled = isEnabled
                imageView_front.isEnabled = isEnabled
                easyFlipView.isEnabled = isEnabled
            }
            12 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back12)
                val imageView_front: ImageView = findViewById<ImageView>(R.id.img_front12)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView12)

                // Dieu kien: FLIP CARD - MODE OPEN CARD
                if (Constants.MODE_OPEN_CARD == modeCard) {
                    isEnabled = true
                    if (easyFlipView.isBackSide) {
                        easyFlipView.flipTheView()
                    }
                }
                // Dieu kien: FLIP CARD - MODE LOCK CARD / MODE_VISIBLE_CARD
                if (Constants.MODE_LOCK_CARD == modeCard) {
                    if (!easyFlipView.isBackSide) {
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
    private fun processVisibleAllCard() {
        for (pos in 1..Constants.EASY_NO_OF_CARDS) {

            processOpenLockVisibleCard(pos, Constants.MODE_VISIBLE_CARD)
        }
    }

    fun processLockAllCard() {
        for (pos in 1..Constants.EASY_NO_OF_CARDS) {
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
    }
}
