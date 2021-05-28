package com.chanj.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel
 * 1. View의 데이터를 들고 있으며, 비지니스 로직을 처리한다.
 * 2. Rotation 등의 동작 시, 날라가는 데이터에 대한 처리를 따로 하지 않아도 된다.
 * 3. Context를 가지지 말아야한다. 이는 구조를 명확하게 구분하고 Unit Test를 용이하게 한다. ref)AndroidViewModel은 context를 얻을 수 있다.
 */
class MainViewModel: ViewModel() {
    /**
     * ViewModel이 생성될 때 처음 실행됩니다.(생성자보다 먼저 실행됩니다)
     */
    init {
        Log.i("MainViewModel", "MainViewModel created!")
    }

    /**
     * ViewModel은 Activity/Fragment의 LifeCycle에 맞춰 동작하며, 오로지 onDestroy()시에 호출되 onCleared()만을 갖습니다.
     * 이로써, Memory Leak을 방지합니다.
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    /**
     * ViewModel은 Key로 엮을 수 있고 다른 Activity에서 재사용가능하다.
     */
//    override fun <T : Any?> setTagIfAbsent(key: String?, newValue: T): T {
//        return super.setTagIfAbsent(key, newValue)
//    }

//    override fun <T : Any?> getTag(key: String?): T {
//        return super.getTag(key)
//    }
}