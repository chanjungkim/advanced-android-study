package org.koreanlab.myapp.repository.room

import org.koreanlab.myapp.repository.data.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class MockUserDao {
    suspend fun addUser(user: User) = coroutineScope {
        delay(1000)
        // add user
    }
}