package com.bignerdranch.android.greenspot.database

import androidx.room.TypeConverter
import java.util.Date

class PlantTypeConverters { // This class is a subclass of RoomDatabase. It is the database for the app.
    @TypeConverter
    fun fromDate(date: Date): Long{ // Converts a Date object to a Long object.
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date { // Converts a Long object to a Date object.
        return Date(millisSinceEpoch)
    }
}