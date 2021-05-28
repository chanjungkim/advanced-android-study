package com.chanj.week3.data.network

import com.chanj.week3.data.network.model.PxItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MilitaryService {
    @GET("{key}/json/DS_MND_PX_PARD_PRDT_INFO/{startIndex}/{endIndex}")
    suspend fun getPopularPxItemList(@Path("key") key: String,
                          @Path("startIndex") startIndex: Int,
                          @Path("endIndex") endIndex: Int): Response<ArrayList<PxItem>>

    /**
     * https://openapi.mnd.go.kr/3536313630323032353331353732303736/json/DS_MND_PX_PARD_PRDT_INFO/1/5/
     * KEY	STRING(필수)	인증키	OpenAPI에서 발급된 인증키
    TYPE	STRING(필수)	요청파일 타입	xml : xml, xml파일 : xmlf,
    엑셀파일 : xls, json파일 : json
    SERVICE	STRING(필수)	서비스명	DS_TB_MND_YYPCTRBY_ENLSTMN_SAL
    START_INDEX	INTEGER(필수)	요청시작위치	정수 입력
    END_INDEX
     */
    @GET("{key}/json/{startIndex}//{endIndex}")
    fun getSalaryInfoList(@Path("key") key: String,
                          @Path("startIndex") startIndex: Int,
                          @Path("endIndex") endIndex: Int): Call<ArrayList<Object>>
}