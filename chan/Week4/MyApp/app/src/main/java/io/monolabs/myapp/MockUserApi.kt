package io.monolabs.myapp

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class MockUserApi {
    suspend fun userResponse(userId: String): UserResponse = coroutineScope {
        delay(1000)

        UserResponse("홍길동", 30, "남", true)
    }
}