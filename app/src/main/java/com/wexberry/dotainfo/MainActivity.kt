package com.wexberry.dotainfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wexberry.dotainfo.AdaptersRecyclerView.DotaAdapter
import com.wexberry.dotainfo.network.DotaApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    //lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initFields()
        //initFunc()
    }

    private fun initFields() {
        createRecyclerView()
    }

    private fun initFunc() {
        getAllHeroes()
    }

    private fun createRecyclerView() {
        //recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        //recyclerView.layoutManager = LinearLayoutManager(this)
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
                    //recyclerView.adapter = DotaAdapter(heroes, R.layout.list_item_heroes)
                },
                { error ->
                    // Логируем ошибку
                    Log.d(TAG, error.toString())
                }
            ).isDisposed
    }

    companion object {
        val TAG = "MainActivity"
    }
}