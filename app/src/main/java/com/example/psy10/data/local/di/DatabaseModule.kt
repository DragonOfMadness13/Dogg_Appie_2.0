package com.example.psy10.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.psy10.data.local.database.AppDatabase
import com.example.psy10.data.local.database.DogEntityDao
import com.example.psy10.data.local.database.UserEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDogDao(appDatabase: AppDatabase): DogEntityDao {
        return appDatabase.dogDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "DogsDB"
        )
            .fallbackToDestructiveMigration() // To spowoduje wyczyszczenie bazy przy zmianie wersji
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserEntityDao {
        return appDatabase.userDao()
    }
}

