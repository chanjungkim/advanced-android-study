package org.koreanlab.myapp.components.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.koreanlab.myapp.components.ui.main.MainViewModel
import com.koreanlab.myapp.databinding.ItemMemoBinding
import org.koreanlab.myapp.repository.data.Memo
import java.util.*

class MemoAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Memo, MemoAdapter.MemoViewHolder>(TaskDiffCallback()) {
    var removeListener: RemoveListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMemoBinding.inflate(layoutInflater, parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item, position)

        holder.binding.btnRemove.setOnClickListener {
            removeListener?.onRemove(position)
            notifyItemChanged(position)
        }
    }

    inner class MemoViewHolder constructor(val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, memo: Memo, position: Int) {
            binding.vm = viewModel
            binding.tvTitle.text = memo.title+position
            binding.tvContent.text = memo.content
            binding.tvDate.text = "${Date(memo.date).year+1900}/${Date(memo.date).month+1}/${Date(memo.date).date}"
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem == newItem
    }
}