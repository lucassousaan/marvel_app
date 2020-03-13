package com.lucassousa.heroes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucassousa.heroes.R
import com.lucassousa.heroes.view.adapters.CreatorsPageAdapter
import com.lucassousa.heroes.viewmodel.CreatorViewModel
import kotlinx.android.synthetic.main.fragment_creators.view.*

class CreatorsFragment : Fragment() {

    lateinit var viewModel: CreatorViewModel
    lateinit var creatorAdapter: CreatorsPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_creators, container, false)
        view.rv_creators_list.layoutManager = LinearLayoutManager(context)
        view.rv_creators_list.setHasFixedSize(true)
        creatorAdapter = CreatorsPageAdapter(context!!)

        getCreators(view)
        return view
    }

    private fun getCreators(view: View) {
        viewModel = ViewModelProviders.of(this)[CreatorViewModel::class.java]
        viewModel.resultPagedList.observe(this, Observer {
            if (it != null) {
                creatorAdapter.submitList(it)
            }
        })

        view.rv_creators_list.adapter = creatorAdapter
    }

}
