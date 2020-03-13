package com.lucassousa.heroes.view.activities

import android.os.Bundle
import android.transition.Transition
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import coil.api.load
import com.google.gson.Gson
import com.lucassousa.heroes.R
import com.lucassousa.heroes.entities.CharacterEntity
import com.lucassousa.heroes.model.ResultsCharactersModel
import com.lucassousa.heroes.util.MyApplication
import kotlinx.android.synthetic.main.activity_character.*


class CharacterActivity : AppCompatActivity() {

    val VIEW_NAME_HEADER_IMAGE = "detail:header:image"
    val VIEW_NAME_HEADER_TITLE = "detail:header:title"
    val VIEW_NAME_DESCRIPTION = "detail:header:description"

    private lateinit var item: ResultsCharactersModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val receiveItem = intent.extras!!.getString("character")
        item = Gson().fromJson(receiveItem, ResultsCharactersModel::class.java)

        ViewCompat.setTransitionName(iv_cover, VIEW_NAME_HEADER_IMAGE)
        ViewCompat.setTransitionName(tv_title_name, VIEW_NAME_HEADER_TITLE)
        ViewCompat.setTransitionName(tv_infos, VIEW_NAME_DESCRIPTION)

        loadItem()

        val isFavList = MyApplication.database!!.charactersDao().getCharacterById(item.id)
        var isFav = false

        isFav = if (isFavList.isNotEmpty()) {
            bt_favorite.setImageResource(R.drawable.ic_star)
            true
        } else {
            bt_favorite.setImageResource(R.drawable.ic_star_empty)
            false
        }

        bt_favorite.setOnClickListener {
            val character = CharacterEntity()

            character.id = item.id
            character.name = item.name
            character.imageUrl = "${item.thumbnail.path}.${item.thumbnail.extension}"

            isFav = if (isFav) {
                MyApplication.database!!.charactersDao().delete(character)
                bt_favorite.setImageResource(R.drawable.ic_star_empty)
                false
            } else {
                MyApplication.database!!.charactersDao().insert(character)
                bt_favorite.setImageResource(R.drawable.ic_star)
                true
            }
        }
    }

    private fun loadItem() {
        addTransitionListener()
        tv_title_name.text = item.name
        tv_infos.text = item.description
    }

    private fun loadThumbnail() {
        iv_cover.load("${item.thumbnail.path}.${item.thumbnail.extension}") {
            crossfade(true)
        }
    }

    @RequiresApi(21)
    private fun addTransitionListener(): Boolean {
        val transition: Transition? = window.sharedElementEnterTransition
        if (transition != null) {
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    loadThumbnail()
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition?) {
                }

                override fun onTransitionCancel(transition: Transition) {
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition?) {
                }

                override fun onTransitionResume(transition: Transition?) {
                }
            })
            return true
        }
        return false
    }
}
