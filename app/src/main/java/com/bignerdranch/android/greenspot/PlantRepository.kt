package com.bignerdranch.android.greenspot

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.greenspot.database.PlantDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID


private const val DATABASE_NAME = "crime-database" // @TODO CHANGE NAME TO PLANT-DATABASE
class PlantRepository private constructor(context: Context) {

    private val database: PlantDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            PlantDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        
        .build()

    fun getPlants(): Flow<List<Plant>> = database.plantDao().getPlants()

    suspend fun getPlant(id: UUID): Plant = database.plantDao().getPlant(id)

    companion object {
        private var INSTANCE: PlantRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = PlantRepository(context)
            }
        }

        fun get(): PlantRepository {
            return INSTANCE
                ?: throw IllegalStateException("PlantRepository must be initialized")
        }
    }
}