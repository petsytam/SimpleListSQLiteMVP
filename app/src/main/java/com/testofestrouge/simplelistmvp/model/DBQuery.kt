package com.testofestrouge.simplelistmvp.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.IOException

class DBQuery(context: Context) {

    private val dbHelper: DBHelper =
        DBHelper(context)
    private var sqliteDB: SQLiteDatabase? = null

    init {

        try {
            dbHelper.createDatabase()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isDatabaseCreated(): Boolean {
        return dbHelper.checkDatabaseExist()
    }

    fun getListFromDatabse(): ArrayList<CityDataModel> {
        sqliteDB = dbHelper.writableDatabase
        val arrayList = arrayListOf<CityDataModel>()
        val querySelect = "SELECT * FROM cities"
        val cursor = sqliteDB!!.rawQuery(querySelect, null)
        cursor?.moveToFirst()
        var count = 0
        //while (!cursor.isAfterLast) {
        while (count++ < 100) {
            arrayList.add(
                CityDataModel(
                    cursor.getString(ID),
                    cursor.getString(COUNTRY),
                    cursor.getString(CITY),
                    cursor.getInt(POPULATION)
                )
            )
            cursor.moveToNext()
        }
        cursor.close()
        sqliteDB!!.close()
        return arrayList
    }

    fun getListCityPaging(limit: Int, offset: Int): ArrayList<CityDataModel>
    {
        val db = dbHelper.writableDatabase
        val listCity = arrayListOf<CityDataModel>()
        val query = "SELECT * FROM cities LIMIT $limit OFFSET $offset"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast)
        {
            listCity.add(CityDataModel(cursor.getString(ID),
                cursor.getString(COUNTRY),
                cursor.getString(CITY),
                cursor.getInt(POPULATION)))
            cursor.moveToNext()
        }
        cursor.close()
        db.close()
        return listCity
    }

    @Throws(SQLiteConstraintException::class)
    fun addNewCity(city: CityDataModel): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(COLUM_ID, city.id)
        values.put(COLUM_COUNTRY, city.country)
        values.put(COLUM_CITY, city.city)
        values.put(COLUM_POPULATION, city.population)
        Log.d("---SQLite---","addNewCity: "+city.id)
        db.insert(TABLE_NAME, null, values)
        db.close()
        return true
    }

    companion object {
        const val ID                = 0
        const val COUNTRY           = 1
        const val CITY              = 2
        const val POPULATION        = 3

        const val TABLE_NAME        = "cities"
        const val COLUM_ID          = "id"
        const val COLUM_COUNTRY     = "country"
        const val COLUM_CITY        = "city"
        const val COLUM_POPULATION  = "population"
    }


}