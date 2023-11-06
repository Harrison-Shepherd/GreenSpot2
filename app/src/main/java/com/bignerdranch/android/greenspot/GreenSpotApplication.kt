package com.bignerdranch.android.greenspot

import android.app.Application

class GreenSpotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PlantRepository.initialize(this)
    }
}