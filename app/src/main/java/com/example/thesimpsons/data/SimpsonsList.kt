package com.example.thesimpsons.data

data class SimpsonsList (
    val results : List<Simpson>
)

data class Simpson(
    val id: Int,
    val age : Int,
    val name: String,
    val gender: String,
    val occupation : String,
    val status: String,
    val portrait_path : String
)