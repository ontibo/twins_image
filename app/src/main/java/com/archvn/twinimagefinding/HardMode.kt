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
    var higtScore: String = Constants.STR_BLANK


    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        setContentView(R.layout.hard_mode)

        shuffle(CARDS, Constants.HARD_NO_OF_CARDS)
        shuffle(CARDS, Constants.HARD_NO_OF_CARDS) // double shuffle

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

        /* CARD 13 */
        val img_view_front13: ImageView = findViewById<ImageView>(R.id.img_front13)
        img_view_front13.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number13)

            doStuff(13, strPosition.getText().toString())
        }

        /* CARD 14 */
        val img_view_front14: ImageView = findViewById<ImageView>(R.id.img_front14)
        img_view_front14.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number14)

            doStuff(14, strPosition.getText().toString())
        }

        /* CARD 15 */
        val img_view_front15: ImageView = findViewById<ImageView>(R.id.img_front15)
        img_view_front15.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number15)

            doStuff(15, strPosition.getText().toString())
        }

        /* CARD 16 */
        val img_view_front16: ImageView = findViewById<ImageView>(R.id.img_front16)
        img_view_front16.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number16)

            doStuff(16, strPosition.getText().toString())
        }

        /* CARD 17 */
        val img_view_front17: ImageView = findViewById<ImageView>(R.id.img_front17)
        img_view_front17.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number17)

            doStuff(17, strPosition.getText().toString())
        }

        /* CARD 18 */
        val img_view_front18: ImageView = findViewById<ImageView>(R.id.img_front18)
        img_view_front18.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number18)

            doStuff(18, strPosition.getText().toString())
        }

        /* CARD 19 */
        val img_view_front19: ImageView = findViewById<ImageView>(R.id.img_front19)
        img_view_front19.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number19)

            doStuff(19, strPosition.getText().toString())
        }

        /* CARD 20 */
        val img_view_front20: ImageView = findViewById<ImageView>(R.id.img_front20)
        img_view_front20.setOnClickListener{
            var strPosition:TextView = findViewById<TextView>(R.id.tv_number20)

            doStuff(20, strPosition.getText().toString())
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
            turn++
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
        checkEnd()
    }

    fun checkEnd () {

        higtScore = "00:" + text_view.text.toString()
        if ((Constants.HARD_NO_OF_CARDS / 2) == turn) {

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
            createAlertEndGame(Constants.MESSAGE_WIN)
        }
    }

    // Create Alert
    // WIN: FLIP ALL CARD IN TIME
    // LOSE: FULL TIME
    fun createAlertEndGame (message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message + "\n")
        builder.setCancelable(false)

        builder.setPositiveButton(Constants.BUTTON_HOME_TXT) { dialog, which ->
            //            Toast.makeText(applicationContext,
//                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(Constants.BUTTON_EXIT_TXT) { dialog, which ->
            finish()
        }

        val alertDialog = builder.create()
        alertDialog.show()
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
            13 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back13)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front13)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView13)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            14 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back14)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front14)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView14)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            15 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back15)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front15)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView15)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            16 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back16)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front16)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView16)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            17 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back17)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front17)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView17)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            18 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back18)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front18)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView18)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            19 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back19)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front19)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView19)
                processLockCard(easyFlipView, imageView_front, imageView_back, flipTheView, isEnabled, flipEnabled)
            }
            20 -> {
                // Lock Card Or Open Card
                var imageView_back : ImageView = findViewById<ImageView>(R.id.img_back20)
                imageView_back.isEnabled= isEnabled
                var imageView_front : ImageView = findViewById<ImageView>(R.id.img_front20)

                // Flip Card
                var easyFlipView : EasyFlipView = findViewById<EasyFlipView>(R.id.easyFlipView20)
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
        timer(Constants.HARD_TIME, Constants.TIMER_INTERVAL).start()
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
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = "${millisUntilFinished/1000}"

                if (isCancelled){
                    text_view.text = "${text_view.text}"
                    cancel()
                }else{
                    text_view.text = timeRemaining
                }
            }

            override fun onFinish() {
                isCancelled = true
                // Lock All Card
                processLockAllCard()
                // Notification GAME OVER
                createAlertEndGame(Constants.MESSAGE_LOSE)
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
