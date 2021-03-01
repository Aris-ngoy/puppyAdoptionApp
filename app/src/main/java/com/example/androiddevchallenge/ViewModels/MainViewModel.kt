package com.example.androiddevchallenge.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val puppies : MutableState<List<String>> = mutableStateOf(ArrayList())

    val query =  mutableStateOf("")

    val loading = mutableStateOf(false)

    fun onQueryChange(value : String){
        query.value = value
    }
}