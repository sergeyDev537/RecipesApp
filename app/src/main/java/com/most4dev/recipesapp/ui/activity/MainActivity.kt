package com.most4dev.recipesapp.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.most4dev.recipesapp.R
import com.most4dev.recipesapp.databinding.ActivityMainBinding
import com.most4dev.recipesapp.ui.toolbar.TypeToolbar
import com.most4dev.recipesapp.ui.toolbar.UpdateToolbar
import com.most4dev.recipesapp.utils.checkPermission
import com.most4dev.recipesapp.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), UpdateToolbar {

    private val viewModel: MainViewModel by viewModel()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navigationViewBottom: BottomNavigationView by lazy {
        binding.bottomNavigationView
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        setupNavigation()
        viewModel.apply {
            setObserves()
        }
    }

    private fun MainViewModel.setObserves() {
        profile.observe(this@MainActivity) {
            showLocationToolbar(
                currentCity = it.city,
                currentDate = it.date
            )
        }
    }

    private fun setupNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_bottom_home,
                R.id.nav_bottom_search,
                R.id.nav_bottom_cart,
                R.id.nav_bottom_account
            )
        )
        navigationViewBottom.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun showLocationToolbar(currentCity: String, currentDate: String) {
        binding.toolbar.root.apply {
            title = currentCity
            isTitleCentered = false
            subtitle = currentDate
            navigationIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_location)
            setNavigationOnClickListener {

            }
        }
    }

    private fun showCategoryToolbar(titleToolbar: String) {
        binding.toolbar.root.apply {
            title = titleToolbar
            isTitleCentered = true
            subtitle = null
            navigationIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_back)
            setNavigationOnClickListener {
                navController.popBackStack()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun updateTypeToolbar(typeToolbar: TypeToolbar, title: String?, subTitle: String?) {
        when (typeToolbar) {
            TypeToolbar.LOCATION_TOOLBAR -> {
                showLocationToolbar("", "")
                checkPermission(resultPermissionLauncher) {
                    getLocation()
                }
            }

            TypeToolbar.CATEGORY_TOOLBAR -> {
                title?.let {
                    showCategoryToolbar(it)
                } ?: showCategoryToolbar(getString(R.string.dishes))

            }
        }
    }

    private val resultPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->
            var permissionBoolean = false

            permissions.entries.forEach {
                if (it.value) {
                    permissionBoolean = true
                } else {
                    permissionBoolean = false
                    binding.root.showSnackbar(
                        getString(R.string.permission_denied) + it.key
                    )
                    return@forEach
                }
            }

            if (permissionBoolean) {
                getLocation()
            }
        }

    private fun getLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewModel.getDataUser(fusedLocationClient)
    }

}