package com.example.thesimpsons.data

import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    suspend fun getAllCharacters() : SimpsonsList
}