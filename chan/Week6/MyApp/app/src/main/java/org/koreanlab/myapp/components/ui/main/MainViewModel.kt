package org.koreanlab.myapp.components.ui.main

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.koreanlab.myapp.repository.UserRepository
import org.koreanlab.myapp.repository.data.Memo
import org.koreanlab.myapp.repository.data.UserForm
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    val user:LiveData<UserForm> = liveData {
        val result = repository.getUser("1111")
        emit(result)
    }

    val title:LiveData<String> = liveData {
        val result = repository.getUser("1111")
        emit("${result.name}, Do not forget!")
    }

    private val _memoList = MutableLiveData<List<Memo>>()
    val memoList: LiveData<List<Memo>>
        get() = _memoList

    private val _isVisible = MutableLiveData<Boolean>()
    val isVisible: LiveData<Boolean>
        get() = _isVisible

    init {
        _memoList.value = listOf<Memo>()
        _isVisible.value = false
    }

    fun addMemo(memo: Memo) = viewModelScope.launch{
        _memoList.value = _memoList.value!!.toMutableList().apply {
            add(_memoList.value!!.size, memo)
        }.toList()
    }

    fun removeMemo(position: Int){
        _memoList.value = _memoList.value!!.toMutableList().apply {
            Log.d("position", "${position}")
            try{
                removeAt(position)
            }catch (e: Exception){
                e.printStackTrace()
                removeLastMemo()
            }
        }.toList()
    }

    fun removeLastMemo(){
        _memoList.value = _memoList.value!!.toMutableList().apply {
            removeAt(_memoList.value!!.lastIndex)
        }.toList()
    }

    fun clearMemo(){
        _memoList.value = emptyList<Memo>()
    }

    fun toggleVisible(){
        _isVisible.value = !_isVisible.value!!
    }
}