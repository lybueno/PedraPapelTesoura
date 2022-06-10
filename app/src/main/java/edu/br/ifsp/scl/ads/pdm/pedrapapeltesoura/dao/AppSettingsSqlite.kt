package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.dao

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model.AppSettings
import java.sql.SQLException

class AppSettingsSqlite(context: Context): AppSettingsDAO {

    companion object {
        private val DB_SETTINGS = "app_settings"
        private val TABLE_SETTINGS = "settings"
        private val COLUMN_ID = "id"
        private val COLUMN_PLAYERS = "players_number"

        val CREATE_TABLE_STMT = "CREATE TABLE IF NOT EXISTS ${TABLE_SETTINGS} (" +
                "${COLUMN_ID} INTEGER NOT NULL PRIMARY KEY," +
                "${COLUMN_PLAYERS} INTEGER NOT NULL);"

    }

    private val appSettingsDB: SQLiteDatabase
    init {
        appSettingsDB = context.openOrCreateDatabase(DB_SETTINGS, MODE_PRIVATE, null)
        try {
            appSettingsDB.execSQL(CREATE_TABLE_STMT)
        } catch(se: SQLException){
            Log.e("AppSettings", se.toString())
        }
    }

    override fun create(appSetting: AppSettings): Long {
        val appSettingsCv = ContentValues()

        appSettingsCv.put(COLUMN_PLAYERS, appSetting.playersNumber)

        return appSettingsDB.insert(TABLE_SETTINGS, null, appSettingsCv)
    }

    override fun update(appSetting: AppSettings): Int {
        val appSettingsCv = ContentValues()

        appSettingsCv.put(COLUMN_PLAYERS, appSetting.playersNumber)

        return appSettingsDB.update(TABLE_SETTINGS, appSettingsCv, "${COLUMN_ID} = 1",
            null
        )
    }

    override fun recover(): AppSettings {
        val cursor = appSettingsDB.rawQuery(
            "SELECT players_number FROM settings", null
        )

        return if(cursor.moveToFirst()){
            val teste = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAYERS))
            with(cursor){
                AppSettings(
                    getInt(getColumnIndexOrThrow(COLUMN_PLAYERS))
                )
            }
        } else {
            AppSettings(isCreated = true);
        }
    }
}