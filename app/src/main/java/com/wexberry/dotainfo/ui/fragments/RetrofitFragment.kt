package com.wexberry.dotainfo.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.AdaptersRecyclerView.DotaAdapter
import com.wexberry.dotainfo.R
import com.wexberry.dotainfo.databinding.FragmentRetrofitBinding
import com.wexberry.dotainfo.network.DotaApiClient
import com.wexberry.dotainfo.network.dataModels.Heroes
import com.wexberry.dotainfo.room.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers


class RetrofitFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mBinding: FragmentRetrofitBinding
    private lateinit var viewModel: HeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRetrofitBinding.inflate(inflater, container, false).also {

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
        //mBinding = FragmentRetrofitBinding.inflate(layoutInflater)
    }

    private fun initFunc() {
        btnClick()
    }

    private fun createRecyclerView() {
        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun addContentToRecyclerView() {
        viewModel.heroesRetrofit?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                recyclerView.adapter = DotaAdapter(it, R.layout.list_item_heroes)
            } else {
                Toast.makeText(context, "Загрузка неудалась", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertToRoom() {
        viewModel.heroesToRoom?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.insert(it)
            } else {
                Toast.makeText(context, "Загрузите героев", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun btnClick() {
        mBinding.btnGoToRoom.setOnClickListener {
            findNavController().navigate(R.id.roomFragment)
        }

        mBinding.btnSaveToRoom.setOnClickListener {
            insertToRoom()
            findNavController().navigate(R.id.roomFragment)
        }

        mBinding.btnGetAllHeroes.setOnClickListener {
            viewModel.getAllHeroes()
            addContentToRecyclerView()
        }
    }

    companion object {
        //val disposed: Disposables = Disposables()
        val TAG = "MainActivity"
    }
}