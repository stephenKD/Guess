package com.stephen.guess

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GuessViewModel : ViewModel() {

    private val TAG = GuessViewModel::class.java.simpleName
    var secret: Int = 0
    var count: Int = 0

    var counter = MutableLiveData<Int>()
    var result = MutableLiveData<GameResult>()

    init {
        counter.value = 0
        reset()
    }

    fun guess(num: Int) {
        count++
        counter.value = count
        val gameResult = when (num - secret) {
            0 -> GameResult.BINGO
            in 1..Int.MAX_VALUE -> GameResult.SMALLER
            else -> GameResult.BIGGER
        }
        result.value = gameResult
        Log.d(TAG, "number:$num   count:$count")
    }

    fun reset() {
        secret = Random().nextInt(10) + 1
        count = 0
        Log.d(TAG, "secret:$secret")
    }
}

enum class GameResult {
    BIGGER, SMALLER, BINGO
}