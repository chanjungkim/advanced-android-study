package io.monolabs.myapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository): ViewModel() {

    val user:LiveData<UserForm> = liveData {
        emit(repository.getUser("1111"))
    }
}