package com.chanj.week3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val reps = 1000
    val sumSize = 999

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun runInThread() {
        var sum = 0
        val threadList = ArrayList<Thread>()

        println("[start] Active Thread = ${Thread.activeCount()}")
        val time = measureTimeMillis {
            repeat(reps) {
                val mThread = Thread {
//                    println("start: ${Thread.currentThread().name}")
                    for (i in 0..sumSize) {
                        sum += i
                    }
//                    println("end: ${Thread.currentThread().name}")
                }
                mThread.start()
                threadList += mThread
            }

            println("[end] Active Thread= ${Thread.activeCount()}")

            threadList.forEach {
                it.join()
            }
        }
        println("Took $time ms\n")
    }

    @Test
    fun runInCoroutine() {
        var sum = 0
        val jobList = ArrayList<Job>()

        runBlocking {
            println("[start] Active Thread = ${Thread.activeCount()}")
            val time = measureTimeMillis {
                repeat(reps) {
                    val job = launch(Dispatchers.Default) {
//                        println("start: ${Thread.currentThread().name}")
                        for (i in 0..sumSize) {
                            sum += i
                        }
//                        println("end: ${Thread.currentThread().name}")
                    }
                    jobList += job
                }

                println("[end] Active Thread= ${Thread.activeCount()}")

                jobList.forEach {
                    it.join()
                }
            }
            println("Time: $time ms\n")
        }
    }
}