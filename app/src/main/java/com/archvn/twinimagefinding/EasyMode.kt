package com.archvn.twinimagefinding

import android.app.AlertDialog
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
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


class EasyMode : AppCompatActivity() {

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
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6
    )

    private var PRIVATE_MODE = 0

    var firstCard: String = Constants.STR_BLANK
    var secondCard: String = Constants.STR_BLANK
    var clickFirst: Int = Constants.CLICK_FIRST
    var clickSecond: Int = Constants.CLICK_FIRST
    var cardNumber: Int = Constants.CARD_NUMBER_ONE
    var turn: Int = 0
    var higtScore: String = Constants.STR_BLANK
    var b : Bundle? = null


    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        setContentView(R.layout.easy_mode)
        b = Bundle()
        b!!.putInt("level", Constants.LEVEL_EASY)

        shuffle(CARDS, Constants.EASY_NO_OF_CARDS)
        shuffle(CARDS, Constants.EASY_NO_OF_CARDS) // double shuffle

        for (card in CARDS) {
            cards!!.add(card)
        }
        addImageData()

        timeStart()

        /* CARD 1 */
        val img_view_front: ImageView = findViewById<ImageView>(R.id.img_front)
        img_view_front.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number)

            doStuff(1, strPosition.getText().toString())
        }

        /* CARD 2 */
        val img_view_front2: ImageView = findViewById<ImageView>(R.id.img_front2)
        img_view_front2.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number2)

            doStuff(2, strPosition.getText().toString())
        }

        /* CARD 3 */
        val img_view_front3: ImageView = findViewById<ImageView>(R.id.img_front3)
        img_view_front3.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number3)

            doStuff(3, strPosition.getText().toString())
        }

        /* CARD 4 */
        val img_view_front4: ImageView = findViewById<ImageView>(R.id.img_front4)
        img_view_front4.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number4)

            doStuff(4, strPosition.getText().toString())
        }

        /* CARD 5 */
        val img_view_front5: ImageView = findViewById<ImageView>(R.id.img_front5)
        img_view_front5.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number5)

            doStuff(5, strPosition.getText().toString())
        }

        /* CARD 6 */
        val img_view_front6: ImageView = findViewById<ImageView>(R.id.img_front6)
        img_view_front6.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number6)

            doStuff(6, strPosition.getText().toString())
        }

        /* CARD 7 */
        val img_view_front7: ImageView = findViewById<ImageView>(R.id.img_front7)
        img_view_front7.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number7)

            doStuff(7, strPosition.getText().toString())
        }

        /* CARD 8 */
        val img_view_front8: ImageView = findViewById<ImageView>(R.id.img_front8)
        img_view_front8.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number8)

            doStuff(8, strPosition.getText().toString())
        }

        /* CARD 9 */
        val img_view_front9: ImageView = findViewById<ImageView>(R.id.img_front9)
        img_view_front9.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number9)

            doStuff(9, strPosition.getText().toString())
        }

        /* CARD 10 */
        val img_view_front10: ImageView = findViewById<ImageView>(R.id.img_front10)
        img_view_front10.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number10)

            doStuff(10, strPosition.getText().toString())
        }

        /* CARD 11 */
        val img_view_front11: ImageView = findViewById<ImageView>(R.id.img_front11)
        img_view_front11.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number11)

            doStuff(11, strPosition.getText().toString())
        }

        /* CARD 12 */
        val img_view_front12: ImageView = findViewById<ImageView>(R.id.img_front12)
        img_view_front12.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number12)

            doStuff(12, strPosition.getText().toString())
        }
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

            // Function lock clickFirst
            setCardInvisible(clickFirst, Constants.MODE_LOCK_CARD)
        }
        // Lan click thu hai
        else if (Constants.CARD_NUMBER_TWO == cardNumber && !Constants.STR_BLANK.equals(firstCard)) {

            secondCard = strPosition                // Set gia tri can so sanh cho card thu 2
            clickSecond = posCard                   // Set vi tri card thu 2

            // Function lock toan bo card (chua co) => thay the bang lock Second Card
            // Function Lock secondCard
            setCardInvisible(clickSecond, Constants.MODE_LOCK_CARD)
            // Comment chay thu lock all card
//            processLockAllCard()


            // Lock or Set Invisible
            // Hander de tam dung man hinh de flip card
            Handler().postDelayed({
                calculate()
            }, Constants.TIME_HANDLER)
        }
    }

    fun calculate () {
        if (firstCard.equals(secondCard)) {
            // Demo la xoa" khoi man hinh 2 car theo clickFirst va clickSecond
            // Hien tai se khoa 2 car theo clickFirst va clickSecond
            setCardInvisible(clickFirst, Constants.MODE_VISIBLE_CARD)
            setCardInvisible(clickSecond, Constants.MODE_VISIBLE_CARD)

            // Add card -> card Visible
            cardsVisible.add(clickFirst)
            cardsVisible.add(clickSecond)
            turn+=2;
        }
        else {
            // Demo set lai toan bo the img_font
            // Hien tai se xem set la set lai toan bo hay se lat lai
            setCardInvisible(clickFirst, Constants.MODE_OPEN_CARD)
            setCardInvisible(clickSecond, Constants.MODE_OPEN_CARD)
        }

        cardNumber = Constants.CARD_NUMBER_ONE
        firstCard = Constants.STR_BLANK
        secondCard = Constants.STR_BLANK
        clickFirst = Constants.CLICK_FIRST
        clickSecond = Constants.CLICK_FIRST

        // Set enable tat ca the img = true
//        processOpenAllCardNotVisible()
        // Kiem tra ket thuc
       // checkEnd()
    }

    fun checkEnd () {

        higtScore = "00:" + text_view.text.toString()
        if ((Constants.HARD_NO_OF_CARDS ) == turn) {

            // Stop Time
            timeStop()
//            Toast.makeText(this@MainActivity, "END GAME: " + higtScore, Toast.LENGTH_SHORT).show()
            // Lock All Card
            processLockAllCard()

            // Save HigtScore
            val sharedPref: SharedPreferences = getSharedPreferences(Constants.BEST_TIME, PRIVATE_MODE)
            sharedPref.edit().putString(Constants.BEST_HIGH_HARD_MODE, higtScore)
            sharedPref.edit().commit()

            // Notification Win Game
            //createAlertEndGame(Constants.MESSAGE_WIN)
        }
    }

    // Create Alert
    // WIN: FLIP ALL CARD IN TIME
    // LOSE: FULL TIME
    fun createAlertEndGame (b: Bundle) {
        var pref : SharedPreferences  = getSharedPreferences(Constants.BEST_TIME, 0)
        var editor = pref.edit()

        var bestEasyScore = pref.getInt(Constants.BEST_HIGH_EASY_MODE, 22)
        var bestHardScore = pref.getInt(Constants.BEST_HIGH_HARD_MODE, 32)

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.result_dialog,null)
        builder.setView(dialogView)
        if (b.getString("Data") == "win") {
            if (Integer.valueOf(b["level"].toString()) === Constants.LEVEL_EASY) {
                if (Integer.valueOf(b["Time"].toString()) < bestEasyScore) {
                    editor.putInt(
                        Constants.BEST_HIGH_EASY_MODE,
                        Integer.valueOf(b["Time"].toString())
                    ).apply()
                    dialogView.findViewById<TextView>(R.id.newhigh).text = "New High Score!"
                }
            } else if (Integer.valueOf(b["level"].toString()) === Constants.LEVEL_HARD) {
                if (Integer.valueOf(b["Time"].toString()) < bestHardScore) {
                    editor.putInt(
                        Constants.BEST_HIGH_HARD_MODE,
                        Integer.valueOf(b["Time"].toString())
                    ).apply()
                    dialogView.findViewById<TextView>(R.id.newhigh).text = "New High Score!"
                }
            }
            dialogView.findViewById<TextView>(R.id.status).text = "You won!"
            dialogView.findViewById<TextView>(R.id.time).text = "Time: " + b["Time"].toString()
        } else {
            dialogView.findViewById<TextView>(R.id.status).text = "You lost!"
            dialogView.findViewById<TextView>(R.id.time).text = "Time: " + b["Time"].toString()
        }
        val dialog = builder.create()
        dialog.show()
    }


    fun processLockCard (easyFlipView: EasyFlipView, mageView_front : ImageView, mageView_back : ImageView,
                         flipTheView: Boolean, isEnabled : Boolean, flipEnabled: Boolean) {
        // MODE_OPEN_CARD
        if (true == flipTheView) {
            easyFlipView.isFlipEnabled = true
            easyFlipView.flipTheView()
        }
        // MODE_LOCK_CARD
        if (true == flipTheView && false == isEnabled) {
            easyFlipView.flipTheView()
            easyFlipView.isFlipEnabled = flipEnabled
        }
        // MODE_VISIBLE_CARD
        else {
            mageView_front.isEnabled = isEnabled
            mageView_back.isEnabled = isEnabled
            easyFlipView.isFlipEnabled = flipEnabled
        }
    }

    // TWING = TRUE
    // => Set Card Invisible by position and mode
    fun setCardInvisible (position: Int, modeCard: Int) {

        var flipEnabled: Boolean = false
        var isEnabled: Boolean = true
        var flipTheView: Boolean = true

        if (Constants.MODE_LOCK_CARD == modeCard) {
            isEnabled = false
        }
        else if (Constants.MODE_OPEN_CARD == modeCard) {
            isEnabled = true
        }
        else if (Constants.MODE_VISIBLE_CARD == modeCard) {
            flipTheView = false
            isEnabled = false
        }

        when (position) {
            1 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            2 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back2)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front2)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView2)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            3 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back3)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front3)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView3)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            4 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back4)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front4)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView4)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            5 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back5)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front5)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView5)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            6 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back6)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front6)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView6)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            7 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back7)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front7)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView7)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            8 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back8)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front8)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView8)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            9 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back9)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front9)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView9)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            10 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back10)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front10)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView10)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            11 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back11)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front11)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView11)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            12 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back12)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front12)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView12)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
        }
    }

    // Truong hop can lock toan bo card
    // Truong hop ket thuc game, Flip Card
    fun processLockAllCard () {
        for (pos in 1 .. 20) {
//                for (pos in 1 .. Constants.HARD_NO_OF_CARDS) {
            setCardInvisible(pos, Constants.MODE_VISIBLE_CARD)
        }
    }


    fun checkPositionInListCardVisible (position: Int) : Boolean {
        for (pos in 0 .. cardsVisible.size - 1) {
//                for (pos in 1 .. Constants.HARD_NO_OF_CARDS) {
            if (position == cardsVisible[pos]) {
                return true
            }
        }
        return false
    }

    // Start game -> Countdown Time
    fun timeStart () {
        // Count down timer start button
        timer(Constants.EASY_TIME, Constants.TIMER_INTERVAL).start()
        isCancelled = false
    }

    // Caculate time end
    fun timeStop () {
        // Count down timer stop/cancel button
        isCancelled = true
//        timer(Constants.HARD_TIME, Constants.TIMER_INTERVAL)
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        val builder: AlertDialog.Builder
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){

                if (isPaused || isCancelled) {
                    cancel()
                } else {
                    text_view.text = "00:" + "${millisUntilFinished/countDownInterval}"
                    if (turn == (Constants.EASY_NO_OF_CARDS)) {
                        b!!.putString("Data", "win")
                        var time : Long = Constants.EASY_TIME.minus(millisUntilFinished).div(countDownInterval)
                        b!!.putInt("Time", time.toInt())
                        cancel()
                        this.onFinish()
                    }
                }
            }

            override fun onFinish() {
                if (turn < (Constants.EASY_NO_OF_CARDS)) {
                    b!!.putString("Data","lost")
                    var time : Long = Constants.EASY_TIME.div(countDownInterval)
                    b!!.putInt("Time", time.toInt())
                }
                // Lock All Card
                processLockAllCard()
                // Notification GAME OVER
                b!!.let { createAlertEndGame(it) }
            }
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
