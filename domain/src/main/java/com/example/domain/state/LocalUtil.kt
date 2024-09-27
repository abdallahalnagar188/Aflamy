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

    // Load the saved language and apply it
    fun loadLocal(activity: Activity) {
        val language = sharedPreferences.getString(LANGUAGE, "ar") ?: "ar"
        updateResources(activity, language)
    }

    // Get the currently saved language
    fun getLang(): String? {
        return sharedPreferences.getString(LANGUAGE, "en")
    }

    // Check if the current language is English
    fun isEnglish(): Boolean {
        return sharedPreferences.getString(LANGUAGE, "ar") == "en"
    }

    // Set the locale and apply it to the activity
    fun setLocal(activity: Activity, language: String) {
        sharedPreferences.edit().putString(LANGUAGE, language).apply()
        updateResources(activity, language)
    }

    // Update the app's resources and layout direction
    private fun updateResources(activity: Activity, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)
       // config.setLayoutDirection(locale) // Ensure RTL/LTR is applied based on the locale

        activity.baseContext.resources.updateConfiguration(config, activity.baseContext.resources.displayMetrics)
        activity.recreate() // Recreate activity to apply the new locale and layout direction
    }
}
