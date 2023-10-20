package com.example.usersappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.usersappcompose.data.UsersPageSource
import com.example.usersappcompose.data.db.AppDatabase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.data.db.DatabaseRepositoryImpl
import com.example.usersappcompose.data.db.UserDao
import com.example.usersappcompose.data.network.Api
import com.example.usersappcompose.data.network.NetworkRepository
import com.example.usersappcompose.data.network.NetworkRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_table.sqlite").build()
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUsersPageSource(api: Api): UsersPageSource {
        return UsersPageSource(api)
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface Binds {

    @Binds
    @Singleton
    fun bindDatabaseRepository(impl: DatabaseRepositoryImpl): DatabaseRepository

    @Binds
    @Singleton
    fun bindNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository
}