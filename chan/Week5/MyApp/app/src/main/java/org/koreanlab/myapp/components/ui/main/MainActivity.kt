package org.koreanlab.myapp.components.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.koreanlab.myapp.R
import com.koreanlab.myapp.databinding.ActivityMainBinding
import org.koreanlab.myapp.components.ui.main.adapter.MemoAdapter
import org.koreanlab.myapp.components.ui.main.adapter.RemoveListener
import org.koreanlab.myapp.repository.data.Memo
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
            setContentView(binding.root)
            it.lifecycleOwner = this
            it.vm = viewModel
        }

        init()
    }

    private fun init(){
        setUpObserver()
        initData()
        initView()
        initAction()
    }

    private fun setUpObserver() {
        with(viewModel){
            memoList.observe(this@MainActivity, androidx.lifecycle.Observer { memoList ->
                Log.d("memo-list", "${memoList.toString()}")
            })
        }
    }

    private fun initData(){
        // TODO: fetch and prepare data
    }

    private fun initView(){
        // TODO: initialize view
        val adapter = MemoAdapter(viewModel)
        adapter.removeListener = object: RemoveListener{
            override fun onRemove(position: Int) {
                viewModel.removeMemo(position)
            }
        }
        binding.rcvMemo.adapter = adapter
    }

    private fun initAction(){
        // TODO: initialize action
        binding.btnAdd.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_add->{
                viewModel.addMemo(Memo("title", "content", Date().time))
            }
            R.id.btn_clear->{
                viewModel.clearMemo()
            }
        }
    }
}