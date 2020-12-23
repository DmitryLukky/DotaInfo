package com.wexberry.dotainfo.ui.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.AdaptersRecyclerView.DotaAdapter
import com.wexberry.dotainfo.AdaptersRecyclerView.RoomDotaAdapter
import com.wexberry.dotainfo.R
import com.wexberry.dotainfo.databinding.FragmentRoomBinding
import com.wexberry.dotainfo.room.HeroesRoom
import com.wexberry.dotainfo.room.HeroesViewModel
import kotlin.coroutines.coroutineContext


class RoomFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mBinding: FragmentRoomBinding
    private lateinit var viewModel: HeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRoomBinding.inflate(inflater, container, false).also {

        mBinding = it

        initFields()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFunc()
    }

    private fun initFields() {
        viewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)
        createRecyclerView()
    }

    private fun initFunc() {
        getHeroesFromRoom()
        btnClick()
    }

    private fun createRecyclerView() {
        recyclerView = mBinding.roomRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun getHeroesFromRoom() {
        var allHeroes: List<HeroesRoom>? = viewModel.allHeroes.value

        if (allHeroes != null) {
            recyclerView.adapter = RoomDotaAdapter(allHeroes, R.layout.list_item_heroes)
        }
    }

    private fun btnClick() {
        mBinding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.retrofitFragment)
        }
    }
}