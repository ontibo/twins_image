package com.archvn.twinimagefinding;

public final class Constants {
    public static final String PREF_NAME = "HighScore";
    public static final String EASY_HIGH_KEY = "easy_high";
    public static final String HARD_HIGH_KEY = "hard_high";

    // SharedPreferences
    public static final String BEST_TIME = "BEST_TIME";
    public static final String BEST_HIGH_HARD_MODE = "HARD_MODE";
    public static final String BEST_HIGH_MEDIUM_MODE = "MEDIUM_MODE";
    public static final String BEST_HIGH_EASY_MODE = "EASY_MODE";

    // NUMBER_CARD
    public static final int EASY_NO_OF_CARDS = 12;
    public static final int MEDIUM_NO_OF_CARDS = 16;
    public static final int HARD_NO_OF_CARDS = 20;

    // TIME HANDLER - FLIP CARD
    public static final long TIME_HANDLER = 400;

    // BEGIN FLIP CARD - CHECK CARD
    public static final int CARD_NUMBER_ONE = 1;
    public static final int CARD_NUMBER_TWO = 2;

    // MODE (STATUS) CARD
    // 3 MODE: OPEN - LOCK - VISIBLE
    public static final int MODE_LOCK_CARD = 0;
    public static final int MODE_OPEN_CARD = 1;
    public static final int MODE_VISIBLE_CARD = 2;


    // VARIABLE
    public static final String STR_BLANK = "";
    public static final int CLICK_FIRST = -1;

    // MESSAGE ALERT
    public static final String MESSAGE_WIN = "CONGRATULATION!\n YOU WIN";
    public static final String MESSAGE_LOSE = "GAME OVER!";

    // BUTTON
    public static final String BUTTON_HOME_TXT = "HOME";
    public static final String BUTTON_EXIT_TXT = "EXIT";


    public static final int LEVEL_EASY = 0;
    public static final int LEVEL_MEDIUM = 1;
    public static final int LEVEL_HARD = 2;

    // TIME PLAY
    public static final long EASY_TIME = 30000;
    public static final long MEDIUM_TIME = 45000;
    public static final long HARD_TIME = 60000;
    public static final long TIMER_INTERVAL = 1000;

}
