package com.lucassousa.heroes.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.lucassousa.heroes.R
import com.lucassousa.heroes.entities.CharacterEntity
import com.lucassousa.heroes.interfaces.RemoveFromList
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoritesAdapter(
    private val items: List<CharacterEntity>,
    private val context: Context,
    listener: RemoveFromList
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var customButtonListener: RemoveFromList = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.image.load(item.imageUrl) {
            crossfade(true)
        }
        holder.name.text = item.name
        holder.bt_delete.setOnClickListener {
            customButtonListener.removeCharacter(item, position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.iv_creator
        val name = itemView.tv_name
        val bt_delete = itemView.bt_remove
    }
}