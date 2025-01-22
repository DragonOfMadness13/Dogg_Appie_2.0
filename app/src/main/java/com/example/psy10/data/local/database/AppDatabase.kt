package com.example.psy10.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DogEntity::class, UserEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dogDao(): DogEntityDao
    abstract fun userDao(): UserEntityDao
}


