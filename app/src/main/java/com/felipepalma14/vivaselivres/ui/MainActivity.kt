package com.felipepalma14.vivaselivres.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.felipepalma14.vivaselivres.R
import com.felipepalma14.vivaselivres.databinding.ActivityMainBinding
import com.felipepalma14.vivaselivres.extension.gone
import com.felipepalma14.vivaselivres.extension.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupView()

    }

    private fun setupView() {
        try {
            navController = Navigation.findNavController(this,
                R.id.nav_host_fragment
            )
            navController.addOnDestinationChangedListener { _, destination, _ ->
                onDestinationChanged(destination.id)
            }
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.destination_fragment_login))
            binding.toolbar.setupWithNavController(navController, appBarConfiguration)
            binding.bottomNav.setOnNavigationItemSelectedListener(onBottomNavigationListener)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun onDestinationChanged(currentDestination: Int) {
        try {
            Timber.d("onDestinationChanged $currentDestination")
            showView(false)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showView(isShow: Boolean) {
        when(isShow){
            true -> {
                binding.bottomNav.show()
                binding.toolbar.show()
            }else -> {
                binding.bottomNav.gone()
                binding.toolbar.gone()
            }
        }

    }

    private val onBottomNavigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_menu_profile -> {
                    true
                }
                R.id.nav_menu_messages -> {
                    true
                }
                R.id.nav_menu_alerts -> {
                    true
                }
                R.id.nav_menu_about_us -> {
                    true
                }
                else -> {
                    true
                }


            }

        }

}