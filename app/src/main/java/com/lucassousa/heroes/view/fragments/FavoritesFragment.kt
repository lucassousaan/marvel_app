package com.lucassousa.heroes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucassousa.heroes.R
import com.lucassousa.heroes.entities.CharacterEntity
import com.lucassousa.heroes.interfaces.RemoveFromList
import com.lucassousa.heroes.util.MyApplication
import com.lucassousa.heroes.view.adapters.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment(), RemoveFromList {

    private lateinit var listFavs: MutableList<CharacterEntity>
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFavs = mutableListOf()
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val listItem = MyApplication.database!!.charactersDao().getAllCharacters()
        for (item in listItem) {
            listFavs.add(item)
        }
        adapter = FavoritesAdapter(listFavs, context!!, this)
        recyclerView = view.rv_favorites_list

        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = adapter
        return view
    }

    override fun removeCharacter(item: CharacterEntity, position: Int) {
        MyApplication.database!!.charactersDao().delete(item)
        listFavs.removeAt(position)
        adapter.notifyDataSetChanged()
    }

}
