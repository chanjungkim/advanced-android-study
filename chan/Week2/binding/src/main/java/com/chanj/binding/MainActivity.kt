package com.chanj.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.chanj.binding.databinding.ActivityMain3Binding

/**
 * UI Controller
 */
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()
    lateinit var binding : ActivityMain3Binding // XML이름에 따라서 자동 생성

//    lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         setContentView(R.layout.activity_main1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)
        binding.viewModel = viewModel // XML에 data에 세팅한 값과 연결
        init()
    }

    private fun init(){
        initView()
        initAction()
//        setUpObserve()
    }

    private fun initView(){
        // View Binding: View만 들고 있고 값을 직접 변경하진 않는다.
//        tvName = findViewById<TextView>(R.id.tv_name)

        // Data Binding: View에 직접 Data를 연결한다. Data가 바뀌면 자동으로 View에 적용이 된다.
        // XML에 작성
    }

    private fun initAction(){
    }

    private fun setUpObserve (){
        // Useless
//        viewModel.name.observe(this, Observer { name ->
//            tvName.text = name
//        })
    }
}