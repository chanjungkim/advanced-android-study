package com.chanj.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Thread
        GlobalScope.launch(Dispatchers.Main) {
            // TODO: Main Thread 동작
        }

        // 통신 Thread
        GlobalScope.launch(Dispatchers.IO) {
            // TODO: Network, Database, File Write/Read, etc
        }

        // Worker Thread
        GlobalScope.launch(Dispatchers.Default) {
           // TODO: 연산 작업
        }

        // context 변경
        GlobalScope.launch(Dispatchers.Default) {
            launch(Dispatchers.Main) {
                updateUI()
            }
        }

        GlobalScope.launch(Dispatchers.Default) {
            // 쏘고 까먹음
            launch(Dispatchers.Default) {
                val result = coroutineMethod()
            }

            // Return 값 존재
            val result = async(Dispatchers.IO) {
                // ex. api call
                "결과"
            }

            launch(Dispatchers.Main) {
                Toast.makeText(this@CoroutineActivity, "${result.await()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(){
        Toast.makeText(this@CoroutineActivity, "UI updated", Toast.LENGTH_SHORT).show()
    }

    /**
     * Coroutine 안에 호출하려는 method는 suspend로 정의해야함. 리턴값 등의 동작은 동일.
     */
    suspend fun coroutineMethod() = "리턴값"
}