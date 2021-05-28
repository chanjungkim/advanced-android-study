package com.chanj.week3

import android.app.Application

class MyApplication: Application() {
    companion object {
        @Volatile
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            if (instance == null) {
                synchronized(MyApplication::class) {
                    instance = MyApplication()
                }
            }

            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}