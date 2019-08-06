package com.testofestrouge.simplelistmvp.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream
import java.io.IOException

class DBHelper(private val myContext: Context) : SQLiteOpenHelper(
    myContext,
    DB_NAME, null,
    DATABASE_VERSION
) {

    private var myDataBase: SQLiteDatabase? = null

    private val isDatabaseExist: Boolean
        get() {
            var db: SQLiteDatabase? = null

            try {
                val myPath = DB_PATH + DB_NAME
                db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

            } catch (e: SQLiteException) {
                db = null
            }

            if (db != null) {
                db.close()
            }
            return db != null
        }

    private var checkDataExist = false

    @Synchronized
    override fun close() {
        if (myDataBase != null)
            myDataBase!!.close()
        super.close()
    }

    override fun onCreate(dp: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    @Throws(IOException::class)
    fun createDatabase() {
        var isDBExist = isDatabaseExist
        if (!isDBExist) {
            this.readableDatabase
            try {
                copyDataBase()
                isDBExist = true
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        checkDataExist = isDBExist
    }

    private fun copyDataBase() {

        try {
            val myInput = myContext.assets.open(DB_NAME)
            val outFileName = DB_PATH + DB_NAME
            val myOutput = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int = myInput.read(buffer)
            while (length > 0) {
                myOutput.write(buffer, 0, length)
                length = myInput.read(buffer)
            }

            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun checkDatabaseExist(): Boolean {
        return checkDataExist
    }

    companion object {
        val DB_PATH = "/data/data/com.testofestrouge.simplelistmvp/databases/"
        val DB_NAME = "utopia_cities.db"
        val DATABASE_VERSION = 1
    }
}