package org.koreanlab.myapp.components.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.koreanlab.myapp.getOrAwaitValue
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koreanlab.myapp.repository.UserRepository
import org.koreanlab.myapp.repository.data.Memo
import org.koreanlab.myapp.repository.remote.MockUserApi
import org.koreanlab.myapp.repository.room.MockUserDao
import java.util.*

/**
 * - ViewModel은 Android Framework이나 OS에 연관되어있으면 안 된다.
 * - Activity나 Fragment를 생성하지 않을 것이다.
 */
@RunWith(AndroidJUnit4::class)
class MainViewModelTest : TestCase() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testAddMemo() {
        val fakeRepository = UserRepository(MockUserDao(), MockUserApi())
        val mainViewModel = MainViewModel(fakeRepository)

        val memo = Memo("title", "content", Date().time)

        with(mainViewModel) {
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
        }

        val result = mainViewModel.memoList.getOrAwaitValue()
        assertEquals(4, result.size)
    }

    @Test
    fun testRemoveMemo() {
        val fakeRepository = UserRepository(MockUserDao(), MockUserApi())
        val mainViewModel = MainViewModel(fakeRepository)

        val memo = Memo("title", "content", Date().time)

        with(mainViewModel) {
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            removeMemo(0)
            removeMemo(0)
        }

        val result = mainViewModel.memoList.getOrAwaitValue()
        assertEquals(2, result.size)
    }

    @Test
    fun testRemoveLastMemo() {
        val fakeRepository = UserRepository(MockUserDao(), MockUserApi())
        val mainViewModel = MainViewModel(fakeRepository)

        val memo = Memo("title", "content", Date().time)

        with(mainViewModel) {
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            removeLastMemo()
            removeLastMemo()
        }

        val result = mainViewModel.memoList.getOrAwaitValue()
        assertEquals(2, result.size)
    }

    @Test
    fun testClearMemo() {
        val fakeRepository = UserRepository(MockUserDao(), MockUserApi())
        val mainViewModel = MainViewModel(fakeRepository)

        val memo = Memo("title", "content", Date().time)

        with(mainViewModel) {
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            addMemo(memo)
            removeLastMemo()
            removeLastMemo()
            clearMemo()
        }

        val result = mainViewModel.memoList.getOrAwaitValue()
        assertEquals(0, result.size)
    }
}