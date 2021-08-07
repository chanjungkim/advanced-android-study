package org.koreanlab.myapp.repository.remote

import org.koreanlab.myapp.repository.data.User
import org.koreanlab.myapp.repository.data.UserForm

data class UserResponse(
    val name: String,
    val age: Int,
    val gender: String,
    val success: Boolean
) {
    fun getUserForm() = UserForm(name, gender)

    fun getUser() = User(name, age, gender)
}