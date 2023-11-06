package com.bignerdranch.android.greenspot

import android.app.Application

class GreenSpotApplication : Application() {
    override fun onCreate() { // This class is a subclass of Application. It is the database for the app.
        super.onCreate()
        PlantRepository.initialize(this)
    }


}