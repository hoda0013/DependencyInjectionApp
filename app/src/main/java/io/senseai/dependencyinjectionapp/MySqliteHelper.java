package io.senseai.dependencyinjectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bigern on 12/26/15.
 */
public class MySqliteHelper extends SQLiteOpenHelper implements DatabaseAccess{

    private static final String DATABASE_NAME = "io.senseai.MysqliteHelper.databasename";
    private static final int DATABASE_VERSION = 1;

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static MySqliteHelper mInstance = null;
    private SQLiteDatabase db;

    //TODO: Should this be synchronized? I feel like it should be since you truly only want a single instance even in a multi-threaded env.
    public static synchronized MySqliteHelper getInstance(Context context) {
        if (mInstance == null) {
            return new MySqliteHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ModeTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing since this is the first version
    }

    private synchronized SQLiteDatabase openDatabase() {
        final int i = mOpenCounter.incrementAndGet();
        if (i == 1) {
            db = getWritableDatabase();
        }
        return db;
    }

    private synchronized void closeDatabase() {
        final int i = mOpenCounter.decrementAndGet();
        if (i == 0) {
            db.close();
        }
    }

    @Override
    public Mode getMode() {
        Mode m = null;
        String sql = "SELECT * FROM " + ModeTable.TABLE_MODE;
        Cursor c = null;
        try {
            c = db.rawQuery(sql, null);
            if (c.moveToFirst()) {
                String modeName = c.getString(c.getColumnIndex(ModeTable.COLUMN_MODE));
                if (modeName != null) {
                    m = Mode.fromName(modeName);
                }
            }
        } finally {
            if (c != null) {
                c.close();
            }
            closeDatabase();
        }
        return m;
    }

    @Override
    public void setMode(OperatingMode mode) {
        db = openDatabase();
        ContentValues modeContentvalues = new ContentValues();
        makeModeContentValues(modeContentvalues, mode);
        db.insert(ModeTable.TABLE_MODE, null, modeContentvalues);
        closeDatabase();
    }

    private void makeModeContentValues(ContentValues contentValues, OperatingMode operatingMode) {
        contentValues.put(ModeTable.TABLE_MODE, operatingMode.getMode().name());
    }
}
