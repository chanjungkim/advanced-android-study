package com.chanj.week3.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chanj.week3.BuildConfig
import com.chanj.week3.common.Constants
import com.chanj.week3.data.network.MilitaryService
import com.chanj.week3.data.network.ServiceGenerator
import com.chanj.week3.data.network.model.PxItem
import com.chanj.week3.repository.MilitaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val repository: MilitaryRepository
):ViewModel() {
    private val _itemList = MutableLiveData<ArrayList<PxItem>>()
    var itemList: LiveData<ArrayList<PxItem>> = _itemList

    private val _toastMsg = MutableLiveData<String>()
    var toastMsg: LiveData<String> = _toastMsg

    init {
        _itemList.value = ArrayList<PxItem>()
        fetchItems()
    }

    private fun fetchItems()=viewModelScope.launch{
        val api = ServiceGenerator.createService(MilitaryService::class.java, Constants.BASE_URL)

        try{
            val response = async(Dispatchers.IO) {
                repository.fetchPxItems(BuildConfig.MILITARY_API_KEY, _itemList.value!!.size, _itemList.value!!.size+15)
            }.await()

            if(response.isSuccessful){
                val result = response.body()
                _itemList.value!!.addAll(result!!)
                _itemList.value = _itemList.value
                Log.d("px-list", result.toString())
            }else{
                val msg = "${response.code()}: ${response.errorBody()?.string()}"
                _toastMsg.value = msg
            }
        }catch (e: Exception){
            _toastMsg.value = e.toString()
            e.printStackTrace()
        }
    }
}