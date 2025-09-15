package com.example.thesimpsons.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesimpsons.data.Repository
import com.example.thesimpsons.data.Simpson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpsonsViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
    private val _allSimpsons = MutableStateFlow<List<Simpson>>(emptyList())
    val allSimpson : StateFlow<List<Simpson>> = _allSimpsons


    init {
        fetchAllSimpsons()
    }

    private fun fetchAllSimpsons() {
        viewModelScope.launch {
            try {
                _allSimpsons.value = repository.getAllCharacters()
            }catch (e: Exception){
                Log.e("CharacterList","Data cant fetch",e)
            }
        }
    }
}