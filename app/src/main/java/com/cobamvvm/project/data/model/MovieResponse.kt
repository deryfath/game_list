package com.cobamvvm.project.data.model

import com.cobamvvm.project.data.model.Movie
import java.io.Serializable

data class MovieResponse(
    var page: Int,
    var results: MutableList<MovieResult>,
    var total_pages: Int,
    var total_results: Int
)

data class MovieResult(
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Int>,
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
):Serializable