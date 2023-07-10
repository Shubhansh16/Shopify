package com.example.ecommerce.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides               //used to declare a dependency in a module
    @Singleton
    fun provideFirebaseAuth()= FirebaseAuth.getInstance()
}