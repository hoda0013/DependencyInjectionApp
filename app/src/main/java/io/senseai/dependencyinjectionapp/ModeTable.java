package io.senseai.dependencyinjectionapp;

/**
 * Created by bigern on 12/26/15.
 */
public class ModeTable {
    public static final String TABLE_MODE = "table_mode";


    public static final String COLUMN_MODE = "mode";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_RANDOM_NUMBER = "random_number";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_MODE
            + "("
            + COLUMN_MODE + " TEXT, "
            + COLUMN_TIMESTAMP + " INTEGER, "
            + COLUMN_RANDOM_NUMBER + " REAL"
            + ")";
}
