package com.dchya24.submission1.repository

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.dchya24.submission1.helper.database
import com.dchya24.submission1.models.MatchDiscover
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteRepository(private val context: Context){
    private val favoriteHandler: FavoriteHandler = FavoriteHandler()

    fun addToFavorite(match: MatchDiscover): FavoriteHandler{
        try{
            context.database.use {
                insert(MatchDiscover.TABLE_FAVORITE,
                    MatchDiscover.EVENT_ID to match.id,
                    MatchDiscover.EVENT_NAME to match.eventName,
                    MatchDiscover.EVENT_DATE to match.date,
                    MatchDiscover.EVENT_TIME to match.time,
                    MatchDiscover.SPORT_TYPE to match.strSport,
                    MatchDiscover.HOME_ID to match.homeTeamId,
                    MatchDiscover.HOME_NAME to match.homeTeam,
                    MatchDiscover.HOME_SCORE to match.homeScore,
                    MatchDiscover.AWAY_ID to match.awayTeamId,
                    MatchDiscover.AWAY_NAME to match.awayTeam,
                    MatchDiscover.AWAY_SCORE to match.awayScore)
            }
            favoriteHandler.isInserted = true
        }catch(e: SQLiteConstraintException){
            favoriteHandler.error = e.message!!
        }
        return favoriteHandler
    }

    fun getAllFavoriteMatch(): FavoriteHandler{
        try{
            context.database.use {
                val result = select(MatchDiscover.TABLE_FAVORITE)

                favoriteHandler.listFavoriteData = result.parseList(classParser())
            }
        }catch(e: SQLiteConstraintException){
            favoriteHandler.error = e.message!!
        }
        return favoriteHandler
    }

    fun getFavoriteMatch(id: Int): FavoriteHandler{
        Log.d("FavoriteRepository", "Terpanggil")
        try{
            context.database.use {
                val result = select(MatchDiscover.TABLE_FAVORITE)
                    .whereArgs("(${MatchDiscover.EVENT_ID} = {id})",
                        "id" to id)
                favoriteHandler.listFavoriteData = result.parseList(classParser())
                Log.d("FavoriteRepository", "Berhasil")
            }
        }catch(e: SQLiteConstraintException){
            Log.d("FavoriteRepository", "Gagal")
            favoriteHandler.error = e.message!!
        }
        return favoriteHandler
    }

    fun deleteFavoriteMatch(id: Int): FavoriteHandler{
        try{
            context.database.use {
                favoriteHandler.isDeleted = delete(MatchDiscover.TABLE_FAVORITE,
                    "${MatchDiscover.EVENT_ID} = {id}",
                    "id" to id)
            }
        }catch (e: SQLiteConstraintException){
            favoriteHandler.error = e.message!!
        }
        return favoriteHandler
    }

    class FavoriteHandler{
        var isInserted: Boolean = false
        var isDeleted: Int = 0
        var listFavoriteData: List<MatchDiscover> = listOf()
        var error: String = ""
    }

}