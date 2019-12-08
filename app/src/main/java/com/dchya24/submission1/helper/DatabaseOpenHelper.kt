package com.dchya24.submission1.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dchya24.submission1.models.MatchDiscover
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "favoriteteam.db", null, 1){
    companion object{
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if(instance == null){
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            MatchDiscover.TABLE_FAVORITE, true,
            MatchDiscover.ID to INTEGER +  PRIMARY_KEY +  AUTOINCREMENT,
            MatchDiscover.EVENT_ID to INTEGER + UNIQUE,
            MatchDiscover.EVENT_NAME to TEXT,
            MatchDiscover.EVENT_DATE to TEXT,
            MatchDiscover.EVENT_TIME to TEXT,
            MatchDiscover.SPORT_TYPE to TEXT,
            MatchDiscover.HOME_ID to INTEGER,
            MatchDiscover.HOME_NAME to TEXT,
            MatchDiscover.HOME_SCORE to TEXT,
            MatchDiscover.AWAY_ID to INTEGER,
            MatchDiscover.AWAY_NAME to TEXT,
            MatchDiscover.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchDiscover.TABLE_FAVORITE, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)