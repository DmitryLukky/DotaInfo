package com.wexberry.dotainfo.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wexberry.dotainfo.AdaptersRecyclerView.DotaAdapter
import com.wexberry.dotainfo.R
import com.wexberry.dotainfo.databinding.FragmentRetrofitBinding
import com.wexberry.dotainfo.network.DotaApiClient
import com.wexberry.dotainfo.network.dataModels.Heroes
import com.wexberry.dotainfo.room.HeroesDao
import com.wexberry.dotainfo.room.HeroesRepository
import com.wexberry.dotainfo.room.HeroesRoom
import com.wexberry.dotainfo.room.HeroesRoomDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RetrofitFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mBinding: FragmentRetrofitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentRetrofitBinding.inflate(inflater, container, false).also {

        mBinding = it

        initFields()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFunc()
    }

    private fun initFields() {
        createRecyclerView()
        //mBinding = FragmentRetrofitBinding.inflate(layoutInflater)
    }

    private fun initFunc() {
        getAllHeroes()
        btnClick()
    }

    private fun createRecyclerView() {
        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun getAllHeroes() {
        val apiClient = DotaApiClient.apiClient.getAllHeroes()

        apiClient
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val heroes = it
                    Log.d(TAG, heroes.toString())

                    // Передаем результат в adapter и отображаем элементы
                    recyclerView.adapter = DotaAdapter(heroes, R.layout.list_item_heroes)
                },
                { error ->
                    // Логируем ошибку
                    Log.d(TAG, error.toString())
                }
            ).isDisposed
    }

    private fun btnClick() {
        mBinding.btnGoToRoom.setOnClickListener {
            findNavController().navigate(R.id.roomFragment)
        }

        mBinding.btnSaveToRoom.setOnClickListener {
//            var heroes: List<HeroesRoom> = heroesList
//            HeroesRepository().insert(heroes)
        }
    }

    companion object {
        val TAG = "MainActivity"
    }
}