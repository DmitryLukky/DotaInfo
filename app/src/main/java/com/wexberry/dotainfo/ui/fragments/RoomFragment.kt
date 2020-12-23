package com.wexberry.dotainfo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.R
import com.wexberry.dotainfo.databinding.FragmentRoomBinding


class RoomFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mBinding: FragmentRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentRoomBinding.inflate(inflater, container, false).also {

        mBinding = it

        initFields()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFunc()
    }

    private fun initFields() {
        createRecyclerView()
    }

    private fun initFunc() {
        btnClick()
    }

    private fun createRecyclerView() {
        recyclerView = mBinding.roomRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun btnClick() {
        mBinding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.retrofitFragment)
        }
    }
}