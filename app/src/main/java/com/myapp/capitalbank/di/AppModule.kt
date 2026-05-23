package com.myapp.capitalbank.di

import android.content.Context
import androidx.room.Room
import com.myapp.capitalbank.data.local.BankDao
import com.myapp.capitalbank.data.local.BankDatabase
import com.myapp.capitalbank.data.remote.BankApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency Injection module providing singleton instances for the data layer.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BankDatabase {
        return Room.databaseBuilder(
            context,
            BankDatabase::class.java,
            "bank_db"
        ).build()
    }

    @Provides
    fun provideBankDao(database: BankDatabase): BankDao {
        return database.bankDao()
    }

    @Provides
    @Singleton
    fun provideBankApiService(): BankApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Dummy URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankApiService::class.java)
    }
}
