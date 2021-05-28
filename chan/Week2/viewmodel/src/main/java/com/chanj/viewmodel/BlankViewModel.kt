package com.chanj.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class BlankViewModel: ViewModel() {
    init {
        Log.i("BlankViewModel", "BlankViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("BlankViewModel", "BlankViewModel destroyed!")
    }
}