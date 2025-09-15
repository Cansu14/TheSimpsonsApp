package com.example.thesimpsons.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getAllCharacters() = apiService.getAllCharacters().results
}