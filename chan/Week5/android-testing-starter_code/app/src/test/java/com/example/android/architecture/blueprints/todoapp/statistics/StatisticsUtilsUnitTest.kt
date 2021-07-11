package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class StatisticsUtilsUnitTest : TestCase() {
//    @Test
//    fun testGetActiveAndCompletedStats() {
//
//    }

    // getActiveAndCompletedStats_noCompleted_returnsHundredZero
    @Test
    fun testGetActiveAndCompletedStats() {
        // =======================================================
        // TODO: 1. 데이터 준비

        // TODO: 2. 메소드 호출

        // TODO: 3. 반환 결과 체크

        // Case1: =====================================================
        // Create an active task
        var tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true)
        )

        // Call your function
        var result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)

        // Using org.hamcrest.Matchers.`is`
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))

        // Case2: ====================================================
        // Create two activeTasks, three inactiveTasks
        tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
        )

        result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(40f))
        assertThat(result.completedTasksPercent, `is`(60f))

        // Case3: ====================================================
        // Create empty Task list
        tasks = listOf<Task>()

        result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}