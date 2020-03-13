package com.lucassousa.heroes.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lucassousa.heroes.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigationController()
    }

    private fun setNavigationController() {
        navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        bottomNavigationView = bottom_navigation
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}
