package com.example.games

import android.app.Application

class BoardGameApplication : Application() {
    val database by lazy { BoardGameDatabase .getDatabase( this) }
    val repository by lazy {
        BoardGameRepository( database.boardGameDao()) }
}