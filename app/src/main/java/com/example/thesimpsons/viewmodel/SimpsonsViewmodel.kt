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
    private val repository: Repository,
) : ViewModel() {
    private val _allSimpsons = MutableStateFlow<List<Simpson>>(emptyList())
    val allSimpson: StateFlow<List<Simpson>> = _allSimpsons

    private val _simpson = MutableStateFlow<Simpson?>(null)
    val simpson: StateFlow<Simpson?> = _simpson

    init {
        fetchAllSimpsons()
    }

    private fun fetchAllSimpsons() {
        viewModelScope.launch {
            try {
                _allSimpsons.value = repository.getAllCharacters()
            } catch (e: Exception) {
                Log.e("CharacterList", "Data cant fetch", e)
            }
        }
    }

    fun fetchSimpson(id: Int?) {
        viewModelScope.launch {
            try {
                _simpson.value = repository.getCharacter(id ?: 1)
            } catch (e: Exception) {
                Log.e("Character", "Data cant fetch", e)
            }
        }
    }
}