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
import com.lucassousa.heroes.model.ResultModel

class ComicsPageAdapter internal constructor(private val context: Context) :
    PagedListAdapter<ResultModel, ComicsPageAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultModel>() {
            override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsPageAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_list_view,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ComicsPageAdapter.ViewHolder, position: Int) {
        val result = getItem(position)

        if (result != null) {
            holder.title.text = result.title
            if (result.creators.available == 1) {
                holder.creators.text = result.creators.items[0].name
            } else if (result.creators.available > 1) {
                val creators: MutableList<String> = mutableListOf()
                var names = ""
                for (creator in result.creators.items) {
                    creators.add(creator.name)
                }
                var i = 0
                while (i < creators.size) {
                    names += if (i == 0) {
                        creators[i]
                    } else {
                        ", ${creators[i]}"
                    }
                    i++
                }
                holder.creators.text = names
            }
            holder.cover.load("${result.thumbnail!!.path}.${result.thumbnail!!.extension}") {
                crossfade(true)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cover: ImageView
        var title: TextView
        var creators: TextView

        init {
            cover = itemView.findViewById(R.id.iv_cover)
            title = itemView.findViewById(R.id.tv_title_name)
            creators = itemView.findViewById(R.id.tv_infos)
        }
    }
}