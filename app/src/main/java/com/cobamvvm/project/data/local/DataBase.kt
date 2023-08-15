package com.cobamvvm.project.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cobamvvm.project.data.local.dao.MovieDao
import com.cobamvvm.project.data.model.Movie

@Database(entities = arrayOf(Movie::class),version = 1,exportSchema = false)
abstract class DataBase :RoomDatabase() {

    abstract fun movieDao():MovieDao

    companion object{
        private var objectInstance:DataBase? = null

        private val lock = Any()

        fun getInstance(context:Context):DataBase{
            synchronized(lock){
                if(objectInstance==null){
                    objectInstance = Room.databaseBuilder(context.applicationContext,DataBase::class.java,"myDatabase.db").build()

                }

                return objectInstance!!
            }
        }
    }

}