package com.example.games

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boardgames")
data class BoardGame(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageURL: String? = null,
    val description: String
)