package com.archvn.twinimagefinding

object Constants {

    // SharedPreferences
    const val PREF_FILE = "PrefsFile"
    const val BEST_HIGH_HARD_MODE = "HARD_MODE"
    const val BEST_HIGH_MEDIUM_MODE = "MEDIUM_MODE"
    const val BEST_HIGH_EASY_MODE = "EASY_MODE"
    const val PRIVATE_MODE = 0

    // NUMBER_CARD
    const val EASY_NO_OF_CARDS = 12
    const val MEDIUM_NO_OF_CARDS = 16
    const val HARD_NO_OF_CARDS = 20

    // TIME HANDLER - FLIP CARD
    const val TIME_HANDLER: Long = 300

    // BEGIN FLIP CARD - CHECK CARD
    const val CARD_NUMBER_ONE = 1
    const val CARD_NUMBER_TWO = 2

    // MODE (STATUS) CARD
    // 4 MODE: OPEN - LOCK - VISIBLE - LOCK(NO FLIP)
    const val MODE_LOCK_CARD = 0
    const val MODE_LOCK_CARD_NO_FLIP = 3
    const val MODE_OPEN_CARD = 1
    const val MODE_VISIBLE_CARD = 2

    // VARIABLE
    const val STR_BLANK = ""
    const val CLICK_FIRST = -1

    // MESSAGE ALERT
    const val MESSAGE_WIN = "CONGRATULATION!\n YOU WIN"
    const val MESSAGE_LOSE = "GAME OVER!"

    // BUTTON
    const val BUTTON_HOME_TXT = "HOME"
    const val BUTTON_EXIT_TXT = "EXIT"
    const val LEVEL_EASY = 0
    const val LEVEL_MEDIUM = 1
    const val LEVEL_HARD = 2

    // TIME PLAY
    const val EASY_TIME: Long = 30000
    const val MEDIUM_TIME: Long = 45000
    const val HARD_TIME: Long = 60000
    const val TIMER_INTERVAL: Long = 1000
    const val PROGRESS_BAR_TIMER_INTERVAL: Long = 10
}