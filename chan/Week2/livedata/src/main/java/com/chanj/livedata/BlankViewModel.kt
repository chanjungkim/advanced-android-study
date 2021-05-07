package com.chanj.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlankViewModel: ViewModel() {
    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    private val _backCnt = MutableLiveData<Int>()
    val backCnt: LiveData<Int> = _backCnt

    val cityList = arrayListOf<String>("LA", "도쿄", "모스크바", "방콕")

    init {
        _city.value = "서울"
        _backCnt.value = 3
    }

    fun shuffleCity(){
        cityList.shuffle()
        _city.value = cityList[0]
        _backCnt.value = _backCnt.value!! - 1
    }
}