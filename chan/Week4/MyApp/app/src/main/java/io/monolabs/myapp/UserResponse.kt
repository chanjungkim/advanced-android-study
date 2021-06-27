package io.monolabs.myapp

data class UserResponse(
    val name: String,
    val age: Int,
    val gender: String,
    val success: Boolean
) {

    fun getUserForm() = UserForm(name, gender)

    fun getUser() = User(name, age, gender)
}