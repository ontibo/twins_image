package com.archvn.twinimagefinding

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.hard_mode.*
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
        setContentView(R.layout.hard_mode)
        b = Bundle()
        b!!.putInt("level", Constants.LEVEL_HARD)
        progressBar =  findViewById(R.id.progressBar)

        shuffle(CARDS, Constants.HARD_NO_OF_CARDS)
        shuffle(CARDS, Constants.HARD_NO_OF_CARDS) // double shuffle

        for (card in CARDS) {
            cards.add(card)
        }
        addImageData()

        object : CountDownTimer(Constants.HARD_TIME, Constants.PROGRESS_BAR_TIMER_INTERVAL) {
            var progress = 0L
            override fun onTick(millisUntilFinished: Long) {
                if (isPaused || isCancelled) {
                    cancel()
                } else {
                    timer.text =
                        ActionUtils.formatTime("${millisUntilFinished / Constants.TIMER_INTERVAL}".toInt())
                    remainingTime = millisUntilFinished
                    progress = Constants.HARD_TIME - millisUntilFinished
                    ActionUtils.setProgress(progressBar, progress,Constants.HARD_TIME)
                    
                    if (turn == (Constants.HARD_NO_OF_CARDS)) {
                        b!!.putString("Data", "win")
                        val time: Long = (Constants.HARD_TIME - millisUntilFinished) / Constants.TIMER_INTERVAL
                        b!!.putInt("Time", time.toInt())
                        cancel()
                        this.onFinish()
                    }
                }
            }

            override fun onFinish() {
                if (turn < (Constants.HARD_NO_OF_CARDS)) {
                    b!!.putString("Data", "lost")
                    val time: Long = Constants.HARD_TIME - Constants.TIMER_INTERVAL
                    b!!.putInt("Time", time.toInt())
                    ActionUtils.setProgress(progressBar, progress,Constants.HARD_TIME)
                }
                // Lock All Card
                processLockAllCard()
                // Notification game result
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

        /* CARD 13 */
        val imgViewFront13: ImageView = findViewById(R.id.img_front13)
        imgViewFront13.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number13)

            doStuff(13, strPosition.text.toString())
        }

        /* CARD 14 */
        val imgViewFront14: ImageView = findViewById(R.id.img_front14)
        imgViewFront14.setOnClickListener {
            val strPosition: TextView = findViewById<TextView>(R.id.tv_number14)

            doStuff(14, strPosition.text.toString())
        }

        /* CARD 15 */
        val imgViewFront15: ImageView = findViewById(R.id.img_front15)
        imgViewFront15.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number15)

            doStuff(15, strPosition.text.toString())
        }

        /* CARD 16 */
        val imgViewFront16: ImageView = findViewById(R.id.img_front16)
        imgViewFront16.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number16)

            doStuff(16, strPosition.text.toString())
        }

        /* CARD 17 */
        val imgViewFront17: ImageView = findViewById(R.id.img_front17)
        imgViewFront17.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number17)

            doStuff(17, strPosition.text.toString())
        }

        /* CARD 18 */
        val imgViewFront18: ImageView = findViewById(R.id.img_front18)
        imgViewFront18.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number18)

            doStuff(18, strPosition.text.toString())
        }

        /* CARD 19 */
        val imgViewFront19: ImageView = findViewById(R.id.img_front19)
        imgViewFront19.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number19)

            doStuff(19, strPosition.text.toString())
        }

        /* CARD 20 */
        val imgViewFront20: ImageView = findViewById(R.id.img_front20)
        imgViewFront20.setOnClickListener {
            val strPosition: TextView = findViewById(R.id.tv_number20)

            doStuff(20, strPosition.text.toString())
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
                        progress = Constants.HARD_TIME - millisUntilFinished
                        ActionUtils.setProgress(progressBar, progress,Constants.HARD_TIME)
                        if (turn == (Constants.HARD_NO_OF_CARDS)) {
                            b!!.putString("Data", "win")
                            val time: Long = (Constants.HARD_TIME - millisUntilFinished) / Constants.TIMER_INTERVAL
                            b!!.putInt("Time", time.toInt())
                            cancel()
                            this.onFinish()
                        }
                    }
                }

                override fun onFinish() {
                    if (turn < (Constants.HARD_NO_OF_CARDS)) {
                        b!!.putString("Data", "lost")
                        val time: Long = Constants.HARD_TIME / Constants.TIMER_INTERVAL
                        b!!.putInt("Time", time.toInt())
                        ActionUtils.setProgress(progressBar,progress,Constants.HARD_TIME)
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
                            progress = Constants.HARD_TIME - millisUntilFinished
                            ActionUtils.setProgress(progressBar, progress,Constants.HARD_TIME)
                            if (turn == (Constants.HARD_NO_OF_CARDS)) {
                                b!!.putString("Data", "win")
                                val time: Long = (Constants.HARD_TIME - millisUntilFinished) / Constants.TIMER_INTERVAL
                                b!!.putInt("Time", time.toInt())
                                cancel()
                                this.onFinish()
                            }
                        }
                    }

                    override fun onFinish() {
                        if (turn < (Constants.HARD_NO_OF_CARDS)) {
                            b!!.putString("Data", "lost")
                            val time: Long = Constants.HARD_TIME / Constants.TIMER_INTERVAL
                            b!!.putInt("Time", time.toInt())
                            ActionUtils.setProgress(progressBar,progress,Constants.HARD_TIME)
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
            for (pos in 1..Constants.HARD_NO_OF_CARDS) {

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
    @SuppressLint("InflateParams", "SetTextI18n")
    fun createAlertEndGame(b: Bundle) {
        val pref: SharedPreferences = getSharedPreferences(Constants.PREF_FILE, PRIVATE_MODE)
        val editor = pref.edit()

        val bestHardScore = pref
            .getInt(Constants.BEST_HIGH_HARD_MODE, (Constants.HARD_TIME/Constants.TIMER_INTERVAL).toInt())

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.result_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)

        if (b.getString("Data") == "win") {

            if (Integer.valueOf(b["Time"].toString()) < bestHardScore) {
                editor.putInt(
                    Constants.BEST_HIGH_HARD_MODE,
                    Integer.valueOf(b["Time"].toString())
                ).apply()
                dialogView.findViewById<TextView>(R.id.newhigh).text = getString(R.string.new_high_score)
                dialogView.findViewById<TextView>(R.id.newhigh).setTextColor(Color.RED)
            }

            dialogView.findViewById<TextView>(R.id.status).text = Constants.MESSAGE_WIN
            dialogView.findViewById<TextView>(R.id.status).setTextColor(Color.BLUE)
            dialogView.findViewById<TextView>(R.id.status).textSize = 40F
            dialogView.findViewById<TextView>(R.id.time).text =
                getString(R.string.your_time) + " " + ActionUtils.formatTime(b["Time"].toString().toInt())
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

        var isEnabled = false
        when (position) {
            1 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back)
                val imageView_front: ImageView = findViewById(R.id.img_front)
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
                val imageView_front: ImageView = findViewById(R.id.img_front2)
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
                val imageView_front: ImageView = findViewById(R.id.img_front3)
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
                val imageView_front: ImageView = findViewById(R.id.img_front4)
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
                val imageView_front: ImageView = findViewById(R.id.img_front5)
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
                val imageView_front: ImageView = findViewById(R.id.img_front6)
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
                val imageView_front: ImageView = findViewById(R.id.img_front7)
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
                val imageView_front: ImageView = findViewById(R.id.img_front8)
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
                val imageView_front: ImageView = findViewById(R.id.img_front9)
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
                val imageView_front: ImageView = findViewById(R.id.img_front10)
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
                val imageView_front: ImageView = findViewById(R.id.img_front11)
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
                val imageView_front: ImageView = findViewById(R.id.img_front12)
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
            13 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back13)
                val imageView_front: ImageView = findViewById(R.id.img_front13)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView13)

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
            14 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back14)
                val imageView_front: ImageView = findViewById(R.id.img_front14)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView14)

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
            15 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back15)
                val imageView_front: ImageView = findViewById(R.id.img_front15)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView15)

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
            16 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back16)
                val imageView_front: ImageView = findViewById(R.id.img_front16)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView16)

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
            17 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back17)
                val imageView_front: ImageView = findViewById(R.id.img_front17)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView17)

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
            18 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back18)
                val imageView_front: ImageView = findViewById(R.id.img_front18)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView18)

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
            19 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back19)
                val imageView_front: ImageView = findViewById(R.id.img_front19)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView19)

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
            20 -> {
                // Lock Card Or Open Card
                val imageView_back: ImageView = findViewById<ImageView>(R.id.img_back20)
                val imageView_front: ImageView = findViewById(R.id.img_front20)
                // Flip Card
                val easyFlipView: EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView20)

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
}
