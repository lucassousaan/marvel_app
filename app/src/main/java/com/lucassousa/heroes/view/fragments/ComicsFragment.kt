package com.lucassousa.heroes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucassousa.heroes.BuildConfig
import com.lucassousa.heroes.R
import com.lucassousa.heroes.view.adapters.ComicsPageAdapter
import com.lucassousa.heroes.viewmodel.MarvelViewModel
import kotlinx.android.synthetic.main.fragment_comics.view.*

class ComicsFragment : Fragment() {

    lateinit var viewModel: MarvelViewModel
    lateinit var comicsAdapter: ComicsPageAdapter

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comics, container, false)
        view.rv_comic_list.layoutManager = LinearLayoutManager(context!!)
        view.rv_comic_list.setHasFixedSize(true)
        comicsAdapter = ComicsPageAdapter(context!!)
        getComics(view)
        return view
    }

    private fun getComics(view: View) {
        viewModel = ViewModelProviders.of(this)[MarvelViewModel::class.java]
        viewModel.resultPagedList.observe(this, Observer {
            if (it != null) {
                comicsAdapter.submitList(it)
            }
        })

        view.rv_comic_list.adapter = comicsAdapter
    }

}
