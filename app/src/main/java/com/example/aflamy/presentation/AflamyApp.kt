package com.example.aflamy.presentation

import android.app.Application
import android.content.Context
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.example.domain.state.LocalUtil
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class AflamyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalUtil.init(this)
    }
}

