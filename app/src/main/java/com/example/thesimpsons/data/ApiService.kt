package com.example.thesimpsons.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("characters")
    suspend fun getAllCharacters() : SimpsonsList
    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id")id: Int) : Simpson
}