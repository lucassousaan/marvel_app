package com.lucassousa.heroes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucassousa.heroes.R
import com.lucassousa.heroes.view.adapters.CharactersPageAdapter
import com.lucassousa.heroes.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_characters.view.*

class CharactersFragment : Fragment() {

    lateinit var viewModel: CharacterViewModel
    lateinit var charactersAdapter: CharactersPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        view.rv_character_list.layoutManager = LinearLayoutManager(context!!)
        view.rv_character_list.setHasFixedSize(true)
        charactersAdapter = CharactersPageAdapter(context!!, activity as AppCompatActivity)
        getCharacters(view)
        return view
    }

    private fun getCharacters(view: View) {
        viewModel = ViewModelProviders.of(this)[CharacterViewModel::class.java]
        viewModel.resultPagedList.observe(this, Observer {
            if (it != null) {
                charactersAdapter.submitList(it)
            }
        })
        view.rv_character_list.adapter = charactersAdapter
    }

}
