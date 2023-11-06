package com.bignerdranch.android.greenspot.database

import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.greenspot.Plant
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface PlantDao{
    @Query("SELECT * FROM plant")
    fun getPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plant WHERE id=(:id)")
    suspend fun getPlant(id: UUID) : Plant

}