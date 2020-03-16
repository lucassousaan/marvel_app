package com.lucassousa.heroes.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.google.android.material.card.MaterialCardView
import com.google.gson.GsonBuilder
import com.lucassousa.heroes.R
import com.lucassousa.heroes.model.ResultsCharactersModel
import com.lucassousa.heroes.view.activities.CharacterActivity

class CharactersPageAdapter internal constructor(
    private val context: Context,
    private val activity: AppCompatActivity
) :
    PagedListAdapter<ResultsCharactersModel, CharactersPageAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsCharactersModel>() {
            override fun areItemsTheSame(
                oldItem: ResultsCharactersModel,
                newItem: ResultsCharactersModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultsCharactersModel,
                newItem: ResultsCharactersModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersPageAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false)
    )

    override fun onBindViewHolder(holder: CharactersPageAdapter.ViewHolder, position: Int) {
        val result = getItem(position)

        if (result != null) {
            holder.name.text = result.name
            holder.description.text = result.description

            holder.img.load("${result.thumbnail.path}.${result.thumbnail.extension}") {
                crossfade(true)
            }

            holder.card.setOnClickListener {
                val activityOptions: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        Pair(holder.img, "detail:header:image"),
                        Pair(holder.name, "detail:header:title"),
                        Pair(holder.description, "detail:header:description")
                    )

                val intent = Intent(context, CharacterActivity::class.java)
                intent.putExtra(
                    "character",
                    GsonBuilder().setPrettyPrinting().create().toJson(result)
                )

                ActivityCompat.startActivity(
                    context,
                    intent,
                    activityOptions.toBundle()
                )
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var name: TextView
        var description: TextView
        var card: MaterialCardView

        init {
            img = itemView.findViewById(R.id.iv_cover)
            name = itemView.findViewById(R.id.tv_title_name)
            description = itemView.findViewById(R.id.tv_infos)
            card = itemView.findViewById(R.id.card_item)
        }
    }
}