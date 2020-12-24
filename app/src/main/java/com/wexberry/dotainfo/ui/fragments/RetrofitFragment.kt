package com.wexberry.dotainfo.ui.fragments

import android.app.Application
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
import com.wexberry.dotainfo.room.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RetrofitFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mBinding: FragmentRetrofitBinding
    private lateinit var viewModel: HeroesViewModel

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
        viewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)
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

        disposed.add {
            // Кладём apiClient
            // и в onDestroy нужно очистить
        }

        apiClient
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val heroes = it
                    Log.d(TAG, heroes.toString())

                    it.forEach {
                        val listRoom = HeroesRoom.heroesToRoom(it)

                        viewModel.insert(listRoom)

                        Log.d(TAG, listRoom.toString())
                    }
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

            saveToRoom(listRoom)

            findNavController().navigate(R.id.roomFragment)
        }
    }

    private fun saveToRoom(heroes: HeroesRoom) {
        viewModel.insert(heroes)
    }

    companion object {
        val disposed: Disposables = Disposables()
        val TAG = "MainActivity"
    }
}