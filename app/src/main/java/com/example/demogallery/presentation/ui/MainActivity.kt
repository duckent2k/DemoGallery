package com.example.demogallery.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.demogallery.R
import com.example.demogallery.SharedPreferenceManager
import com.example.demogallery.databinding.ActivityMainBinding
import com.example.demogallery.presentation.viewmodel.SharedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedViewModel: SharedViewModel

    private val themeTitleList = arrayOf("Light", "Dark", "System")
    private val gridList = arrayOf("1", "2", "3", "4")
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        val navHostFragment = NavHostFragment.create(R.navigation.my_nav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()

        binding.ivSetting.setOnClickListener {
            showDialogTheme()
        }

        binding.ivGrid.setOnClickListener {
            showDialogGrid()
        }
    }

    private fun showDialogTheme() {
        sharedPreferenceManager = SharedPreferenceManager(this)
        var checkedTheme = sharedPreferenceManager.theme

        val themeDialog = MaterialAlertDialogBuilder(this)
            .setTitle("Theme")
            .setPositiveButton("OK") { _, _ ->
                sharedPreferenceManager.theme = checkedTheme
                AppCompatDelegate.setDefaultNightMode(sharedPreferenceManager.themeFlag[checkedTheme])

            }
            .setNegativeButton("Cancel", null)
            .setSingleChoiceItems(themeTitleList, checkedTheme) { _, which ->
                checkedTheme = which
            }
            .setCancelable(false)
        themeDialog.show()
    }

    private fun showDialogGrid() {
        sharedPreferenceManager = SharedPreferenceManager(this)
        var checkedGrid = sharedPreferenceManager.grid

        val gridDialog = MaterialAlertDialogBuilder(this)
            .setTitle("Grid")
            .setPositiveButton("OK") { _, _ ->
                sharedPreferenceManager.grid = checkedGrid
                sharedViewModel.updateData(sharedPreferenceManager.grid)
            }
            .setSingleChoiceItems(gridList, checkedGrid - 1) { _, which ->
                checkedGrid = which + 1
            }
            .setNegativeButton("Cancel", null)
            .setCancelable(false)
        gridDialog.show()
    }
}