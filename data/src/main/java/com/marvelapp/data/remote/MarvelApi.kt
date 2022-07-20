package com.marvelapp.data.remote

import com.marvelapp.data.model.MarvelCharacterEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MarvelCharacterEntity

    @GET("/v1/public/characters/{id}")
    suspend fun getMarvelCharacterDetails(
        @Path("id") id: Long
    ): MarvelCharacterEntity

}