package com.chanj.week1.data

data class Student(
    val name: String,
    val age: Int,
    val address: String,
    var friends: ArrayList<Student>?
) {
    var hasDog: Boolean = false
    var grade: Int = -1

    override fun toString(): String {
        return super.toString()
    }
}