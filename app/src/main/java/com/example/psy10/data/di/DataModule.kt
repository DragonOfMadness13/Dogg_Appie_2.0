package com.example.psy10.data.di

import com.example.psy10.data.DefaultDogRepository
import com.example.psy10.data.DefaultUserRepository
import com.example.psy10.data.DogRepository
import com.example.psy10.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsDogRepository(
        dogRepository: DefaultDogRepository
    ): DogRepository


    @Singleton
    @Binds
    fun bindsUserRepository(
        userRepository: DefaultUserRepository
    ): UserRepository
}