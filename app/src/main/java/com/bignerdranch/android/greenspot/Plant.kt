package com.bignerdranch.android.greenspot

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID
@Entity
data class Plant( // TODO ADD IMAGE GPS LOCATION AND CHANGE SOLVE TO PLACE
    @PrimaryKey val id: UUID,
    val title: String,
    val date: Date,
    val isSolved: Boolean // TODO CHANGE TO PLACE

)
