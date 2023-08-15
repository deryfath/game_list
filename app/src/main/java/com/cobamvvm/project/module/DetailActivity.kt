package com.cobamvvm.project.module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.Constants
import com.cobamvvm.project.Utils.convertStringDateAnotherFormat
import com.cobamvvm.project.Utils.observe
import com.cobamvvm.project.data.model.MovieResult
import com.cobamvvm.project.module.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.architecture.ext.viewModel

class DetailActivity : AppCompatActivity() {

    private var gameId = 0L
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        initData()

    }

    private fun initData(){
        gameId = intent.getLongExtra("game_id",0)
        viewModel.getGameDetail(gameId)
        observeData()
    }

    private fun observeData(){
        observe(viewModel.gameDetail, {
            Glide.with(this).load(it?.background_image).into(iv_movie_detail)
            tv_title_detail.text = it?.name_original
            tv_date.text = it?.released.convertStringDateAnotherFormat()
            tv_rating_detail.text = it?.rating.toString()
            tv_desc.text = it?.description
        })
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_detail.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
    }
}
