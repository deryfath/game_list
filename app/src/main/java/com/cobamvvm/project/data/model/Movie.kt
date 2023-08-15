package com.cobamvvm.project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie")
data class Movie(
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: String,
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int

):Serializable {

    @SerializedName("adult")
    var adultTable: Boolean? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("genre_ids")
    var genreIds: String? = null
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var idMovie: Int? = null
    @SerializedName("original_language")
    var originalLanguage: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overviewMovie: String? = null
    @SerializedName("popularity")
    var popularityMovie: Double? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("title")
    var titleMovie: String? = null
    @SerializedName("video")
    var videoMovie: Boolean? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    var voteCount: Int? = null
}