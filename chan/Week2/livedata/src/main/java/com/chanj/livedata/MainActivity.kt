package com.chanj.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

/**
 * UI Controller
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    val viewModel: MainViewModel by viewModels()
    lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        initView()
        initAction()
        setUpObserve()
    }

    private fun initView(){
        tvName = findViewById<TextView>(R.id.tv_name)
    }

    private fun initAction(){
        tvName.setOnClickListener(this)
    }

    private fun setUpObserve (){
        viewModel.name.observe(this, Observer { name ->
            tvName.text = name
        })

        viewModel.nextCnt.observe(this, Observer { cnt ->
            if(cnt == 0){
                startActivity(Intent(this, FragmentActivity::class.java))
                finish()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_name->{
                viewModel.shuffleName()
            }
        }
    }
}