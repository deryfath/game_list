package com.cobamvvm.project.data.local.dao

import androidx.room.*
import com.cobamvvm.project.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie):Int

    @Query("DELETE FROM movie")
    fun deleteAll()

}