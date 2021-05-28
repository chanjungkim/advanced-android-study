package com.chanj.week3

import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

class ExampleUnitTest {
    val reps = 100
    val sumSize = 999

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun runInThread() {
        println("=== Thread")
        var sum = 0
        val threadList = ArrayList<Thread>()
        var threadCnt = 0

        println("[start] Active Thread = ${Thread.activeCount()}")
        val time = measureTimeMillis {
            repeat(reps) {
                val mThread = Thread {
                    println("start: ${Thread.currentThread().name}")
                    sum++
                    println("end: ${Thread.currentThread().name}")
                }
                mThread.start()
                threadList += mThread
            }

            threadCnt = Thread.activeCount()
            println("[end] Active Thread= ${Thread.activeCount()}")

            threadList.forEach {
                it.join()
            }
        }
        println("Total Time: $time ms")
        println("Average Time: ${time/threadCnt} ms")
        println()
    }

    @Test
    fun runInCoroutine() {
        var sum = 0
        val jobList = ArrayList<Job>()
        var threadCnt = 0

        runBlocking {
            println("[start] Active Thread = ${Thread.activeCount()}")
            val time = measureTimeMillis {
                repeat(reps) {
                    val job = launch(Dispatchers.Default) {
                        println("start: ${Thread.currentThread().name}")
                        sum++
                        println("end: ${Thread.currentThread().name}")
                    }
                    jobList += job
                }

                threadCnt = Thread.activeCount()
                println("[end] Active Thread= ${Thread.activeCount()}")

                jobList.forEach {
                    it.join()
                }
            }
            println("Total Time: $time ms")
            println("Average Time: ${time/threadCnt} ms")
            println()
        }
    }
}