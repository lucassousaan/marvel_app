package com.lucassousa.heroes.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.lucassousa.heroes.R
import com.lucassousa.heroes.model.ResultCreatorModel

class CreatorsPageAdapter internal constructor(private val context: Context) :
    PagedListAdapter<ResultCreatorModel, CreatorsPageAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultCreatorModel>() {
            override fun areItemsTheSame(
                oldItem: ResultCreatorModel,
                newItem: ResultCreatorModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultCreatorModel,
                newItem: ResultCreatorModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatorsPageAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_creator, parent, false)
    )


    override fun onBindViewHolder(holder: CreatorsPageAdapter.ViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.name.text = result.fullName
            holder.img.load("${result.thumbnail.path}.${result.thumbnail.extension}") {
                crossfade(true)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var name: TextView

        init {
            img = itemView.findViewById(R.id.iv_creator)
            name = itemView.findViewById(R.id.tv_name)
        }
    }

}