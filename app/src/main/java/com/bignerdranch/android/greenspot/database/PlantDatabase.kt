package com.bignerdranch.android.greenspot.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.greenspot.Plant


@Database(entities = [Plant::class], version = 2) // This class is a subclass of RoomDatabase. It is the database for the app.
@TypeConverters(PlantTypeConverters::class)  // This class is a subclass of RoomDatabase. It is the database for the app.
abstract class PlantDatabase : RoomDatabase() { //
    abstract fun plantDao(): PlantDao
}