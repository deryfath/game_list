package com.cobamvvm.project.module.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.convertStringDateAnotherFormat
import com.cobamvvm.project.data.model.Result
import com.cobamvvm.project.module.DetailActivity
import kotlinx.android.extensions.LayoutContainer
import java.util.*


class MainAdapter (private var items:MutableList<Result>, private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view=holder.itemView
        val data=items[position]

        view.let {
            it.visibility = View.VISIBLE

            (it.findViewById(R.id.tv_title) as TextView).text = data.name
            (it.findViewById(R.id.tv_release) as TextView).text = data.released.convertStringDateAnotherFormat()
            (it.findViewById(R.id.tv_rating) as TextView).text = data.rating_top.toString()
           Glide.with(context).load(data.background_image).into(it.findViewById(R.id.iv_avatar) as ImageView)

        }

        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("game_id",data.id)
            context.startActivity(intent)
        }

    }

    fun addItems(list:MutableList<Result>){
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    }
}
