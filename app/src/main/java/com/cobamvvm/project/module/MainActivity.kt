package com.cobamvvm.project.module

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.EndlessScrollListener
import com.cobamvvm.project.Utils.observe
import com.cobamvvm.project.data.model.Result
import com.cobamvvm.project.module.adapter.MainAdapter
import com.cobamvvm.project.module.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter
    private var currentPage = 1;
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        viewModel.start()
        pb_main.visibility = View.GONE

        viewModel.getGameList(currentPage)

        et_search_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString().length==0){
                    viewModel.getGameList(currentPage)
                }else{
                    viewModel.getSearchGameList(s.toString())
                }

            }
        })

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            viewModel.getGameList(currentPage)
            pb_load.visibility = View.VISIBLE
        }

        rv_game.addOnScrollListener(loadScrollData())

        observe(viewModel.gameList, {
            pb_load.visibility = View.GONE
            rv_game.visibility = View.VISIBLE
            rv_game_search.visibility = View.GONE

            if(it!!.size>0){
                rv_game.visibility = View.VISIBLE
                if (currentPage==1){
                    println("PAGE 1")
                    adapter = MainAdapter(it,this)
                    val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                    rv_game.setLayoutManager(mLayoutManager)
                    rv_game.adapter = adapter
                }else{
                    adapter.addItems(it)
                }

            }else{
                rv_game.visibility = View.GONE
            }

        })

        observe(viewModel.gameListSearch,{
            pb_load.visibility = View.GONE
            rv_game.visibility = View.GONE
            rv_game_search.visibility = View.VISIBLE

            adapter = MainAdapter(it ?: mutableListOf(),this)
            val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            rv_game_search.setLayoutManager(mLayoutManager)
            rv_game_search.adapter = adapter

        })

        observe(viewModel.isDataLoading,{
            if (it == true){
                println("LOADING")
                pb_main.visibility = View.VISIBLE
            }else{
                handler.postDelayed(Runnable {
                    pb_main.visibility = View.GONE

                }, 3000)
                println("DONE")
            }
        })
    }

    private fun loadScrollData():EndlessScrollListener{

        return object : EndlessScrollListener(){
            override fun onLoadMore() {
                currentPage++
                viewModel.getGameList(currentPage)
            }
        }
    }


}
