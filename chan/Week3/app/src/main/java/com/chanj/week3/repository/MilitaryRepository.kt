package com.chanj.week3.repository

import com.chanj.week3.data.network.MilitaryService
import com.chanj.week3.data.network.model.PxItem
import retrofit2.Response
import java.util.ArrayList

/**
 * 참고: https://github.com/JC-Choo/SampleApplication/tree/master/mvvmarchitecture
 */
class MilitaryRepository(val service: MilitaryService) {
    suspend fun fetchPxItems(key: String, startIdx: Int, endIdx: Int): Response<ArrayList<PxItem>> {
        return service.getPopularPxItemList(key, startIdx, endIdx)
    }
}