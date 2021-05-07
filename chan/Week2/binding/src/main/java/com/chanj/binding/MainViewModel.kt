package com.chanj.binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    val nameList = arrayListOf<String>("빈센조", "홍길동", "김태희", "둘리")

    init {
        _name.value = "빈센조"
    }

    fun shuffleName() {
        nameList.shuffle()
        _name.value = nameList[0]
    }
}