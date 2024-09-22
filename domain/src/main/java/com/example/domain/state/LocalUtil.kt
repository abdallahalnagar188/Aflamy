package com.example.domain.state

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

object LocalUtil {
    private lateinit var sharedPreferences: SharedPreferences
    private const val LANGUAGE = "language"

    // Initialize the SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("local", Context.MODE_PRIVATE)
    }

    // Set the language and update the locale
    fun setLocal(activity: Activity, language: String) {
        sharedPreferences.edit().putString(LANGUAGE, language).apply()
        updateResources(activity, language)  // Apply locale changes immediately
    }

    // Load the saved language and apply it
    fun loadLocal(activity: Activity) {
        val language = sharedPreferences.getString(LANGUAGE, "ar") ?: "ar"
        updateResources(activity, language)
    }

    // Get the currently saved language
    fun getLang(): String? {
        return sharedPreferences.getString(LANGUAGE, "ar")
    }

    // Check if the current language is English
    fun isEnglish(): Boolean {
        return sharedPreferences.getString(LANGUAGE, "ar") == "en"
    }

    // Update the locale configuration
    private fun updateResources(activity: Activity, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)
        activity.baseContext.resources.updateConfiguration(config, activity.baseContext.resources.displayMetrics)

        // Optionally, you can recreate the activity to apply the language change immediately
        activity.recreate() // Forces the activity to restart and apply the new locale
    }
}

