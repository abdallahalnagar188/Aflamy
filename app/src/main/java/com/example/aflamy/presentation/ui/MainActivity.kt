package com.example.aflamy.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aflamy.R
import com.example.aflamy.databinding.ActivityMainBinding
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.domain.state.LocalUtil
import com.example.domain.state.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoadingDialog.init(this)
        StatusBarUtil.init(window)
        LocalUtil.init(this)
//        LocalUtil.loadLocal(this)
        // Inflate layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ensure that you're referencing the correct nav_host_fragment ID
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.mainBn.setupWithNavController(navController)

        setupNavBottomVisibility()


    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupNavBottomVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> hideBottomNav()
                R.id.homeFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.favoriteFragment -> showBottomNav()
                R.id.settingsFragment -> showBottomNav()
                R.id.moviesDetailsFragment -> hideBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.mainBottomAppBar.visibility = View.VISIBLE
        binding.mainBn.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.mainBottomAppBar.visibility = View.GONE
        binding.mainBn.visibility = View.GONE
    }

}
