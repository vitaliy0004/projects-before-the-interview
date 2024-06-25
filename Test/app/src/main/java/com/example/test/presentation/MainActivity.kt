package com.example.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.test.App
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.di.AppComponent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var appComponent: AppComponent
    private val viewModule: ViewModuleActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent = (applicationContext as App).appComponent
        appComponent.inject(this)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        viewModule.navController = navHost.navController
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController = viewModule.navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragmentAirTickets -> {
                    if (viewModule.bottomNavigation != 1) {
                        viewModule.bottomNavigation = 1
                        viewModule.navController.navigate(viewModule.bottomAirTickets)
                    }
                    true
                }

                R.id.fragmentHotels -> {
                    if (viewModule.bottomNavigation != 2) {
                        viewModule.bottomNavigation = 2
                        viewModule.navController.navigate(R.id.hotelsFragment)
                    }
                    true
                }

                R.id.fragmentBrieflySpeaking -> {
                    if (viewModule.bottomNavigation != 3) {
                        viewModule.bottomNavigation = 3
                        viewModule.navController.navigate(R.id.brieflySpeakingFragment)
                    }
                    true
                }

                R.id.fragmentSubscriptions -> {
                    if (viewModule.bottomNavigation != 4) {
                        viewModule.bottomNavigation = 4
                        viewModule.navController.navigate(R.id.subscriptionsFragment)
                    }
                    true
                }

                else -> {
                    if (viewModule.bottomNavigation != 5) {
                        viewModule.bottomNavigation = 5
                        viewModule.navController.navigate(R.id.profileFragment)
                    }
                    true
                }
            }
        }
    }
}