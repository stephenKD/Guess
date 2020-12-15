package com.stephen.guess.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stephen.guess.data.Event
import com.stephen.guess.data.EventResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SnookerViewModel : ViewModel() {
    val event = MutableLiveData<List<Event>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            event.postValue(Gson().fromJson(data, EventResult::class.java))
        }
    }
}