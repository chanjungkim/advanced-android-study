package com.chanj.week3.data.network

import com.chanj.week3.common.Constants
import com.chanj.week3.common.Constants.BASE_URL
import retrofit2.Retrofit

object ServiceGenerator {
    fun <S> createService(serviceClass: Class<S>, baseUrl: String): S {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(serviceClass)
    }
}