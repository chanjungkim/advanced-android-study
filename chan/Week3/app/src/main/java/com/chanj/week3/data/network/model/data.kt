package com.chanj.week3.data.network.model

import androidx.annotation.Keep

@Keep
data class PxItem(
    val prdtnm: String,
    val sellmonth: String,
    val rowno: String,
    val seltnstd: String,
    val sellyear: String
){
    override fun toString(): String {
        return super.toString()
    }
}
