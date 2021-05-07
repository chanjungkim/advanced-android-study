package com.chanj.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name
    val nameList = arrayListOf<String>("빈센조", "홍길동", "김태희", "둘리")

    private val _nextCnt = MutableLiveData<Int>()
    val nextCnt: LiveData<Int> = _nextCnt

    init {
        _name.value = "빈센조"
        _nextCnt.value = 5
    }

    fun shuffleName(){
        nameList.shuffle()
        _name.value = nameList[0]

        _nextCnt.value = _nextCnt.value!! - 1
    }
}