package com.chanj.livedata

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class BlankFragment : Fragment(), View.OnClickListener {
    private val viewModel: BlankViewModel by viewModels()
    lateinit var tvCityName: TextView
    var mView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_blank, container, false)
        init()
        return mView
    }

    private fun init(){
        initView()
        initData()
        initAction()
        setUpObserve()
    }

    private fun initView(){
        tvCityName = mView!!.findViewById(R.id.tv_city)
    }

    private fun initData(){
    }

    private fun initAction(){
        tvCityName.setOnClickListener(this)
    }

    private fun setUpObserve(){
        viewModel.city.observe(requireActivity(), Observer { city ->
            tvCityName.text = city
        })

        viewModel.backCnt.observe(requireActivity(), Observer{ cnt ->
            if(cnt == 0){
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_city->{
                viewModel.shuffleCity()
            }
        }
    }
}