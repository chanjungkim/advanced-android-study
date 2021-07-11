package org.koreanlab.myapp.repository

import org.koreanlab.myapp.repository.data.UserForm
import org.koreanlab.myapp.repository.remote.MockUserApi
import org.koreanlab.myapp.repository.room.MockUserDao

/**
 * API 서버, DB control
 *
 * request param 생성
 *
 */
class UserRepository(
    private val userDao: MockUserDao,
    private val userApi: MockUserApi
) {
    suspend fun getUser(userId: String): UserForm {
        val userResponse = userApi.userResponse(userId)
        userDao.addUser(userResponse.getUser())
        return userResponse.getUserForm()
    }
}