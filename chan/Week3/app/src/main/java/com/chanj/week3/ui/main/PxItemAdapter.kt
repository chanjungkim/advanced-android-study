package com.chanj.week3.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chanj.week3.R
import com.chanj.week3.data.network.model.PxItem

class PxItemAdapter: RecyclerView.Adapter<PxItemAdapter.PxItemViewHolder>() {
    val items = ArrayList<PxItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PxItemViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_px, parent, false)
        return PxItemViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PxItemViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.prdtnm
        holder.tvRowNo.text = item.rowno
        holder.tvSellMonth.text = item.sellmonth
        holder.tvSeltNstd.text = item.sellmonth
        holder.tvSellYear.text = item.sellyear
    }

    override fun getItemCount(): Int = items.size

    inner class PxItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvSellMonth: TextView = itemView.findViewById(R.id.tv_sell_month)
        var tvRowNo: TextView = itemView.findViewById(R.id.tv_row_no)
        var tvSeltNstd: TextView = itemView.findViewById(R.id.tv_selt_nstd)
        var tvSellYear: TextView = itemView.findViewById(R.id.tv_sell_year)
    }
}