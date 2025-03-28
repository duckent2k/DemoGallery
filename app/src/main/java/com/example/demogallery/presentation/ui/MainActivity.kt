package com.example.demogallery.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.example.demogallery.R
import com.example.demogallery.SharedPreferenceManager
import com.example.demogallery.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val themeTitleList = arrayOf("Light", "Dark", "System")
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = NavHostFragment.create(R.navigation.my_nav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()

        binding.ivSetting.setOnClickListener {
            showDialogTheme()
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
}