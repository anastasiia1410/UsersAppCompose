package com.example.usersappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.usersappcompose.data.UsersPageSource
import com.example.usersappcompose.data.db.AppDatabase
import com.example.usersappcompose.data.db.ContactDao
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.data.db.DatabaseRepositoryImpl
import com.example.usersappcompose.data.db.UserDao
import com.example.usersappcompose.data.network.Api
import com.example.usersappcompose.data.network.NetworkRepository
import com.example.usersappcompose.data.network.NetworkRepositoryImpl
import com.example.usersappcompose.ui.screens.add_contact.use_case.SaveToDbUseCase
import com.example.usersappcompose.ui.screens.create_user.use_case.SaveCurrentUserUseCase
import com.example.usersappcompose.ui.screens.detail.use_case.DeleteContactUseCase
import com.example.usersappcompose.ui.screens.detail.use_case.GetDetailUserUseCase
import com.example.usersappcompose.ui.screens.edit_user.use_case.GetUserUseCase
import com.example.usersappcompose.ui.screens.edit_user.use_case.UpdateUserUseCase
import com.example.usersappcompose.ui.screens.list.use_case.GetContactUseCase
import com.example.usersappcompose.ui.screens.list.use_case.FilterAndSortContactsUseCase
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
    fun provideContactDao(appDatabase: AppDatabase): ContactDao {
        return appDatabase.contactDao()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUsersPageSource(api: Api): UsersPageSource {
        return UsersPageSource(api)
    }
}

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideSaveToDbUseCase(databaseRepository: DatabaseRepository): SaveToDbUseCase {
        return SaveToDbUseCase(databaseRepository)
    }

    @Provides
    fun provideSaveCurrentUserUseCase(databaseRepository: DatabaseRepository): SaveCurrentUserUseCase {
        return SaveCurrentUserUseCase(databaseRepository)
    }

    @Provides
    fun provideDeleteContactUseCase(databaseRepository: DatabaseRepository): DeleteContactUseCase {
        return DeleteContactUseCase(databaseRepository)
    }

    @Provides
    fun provideGetDetailUserUseCase(databaseRepository: DatabaseRepository): GetDetailUserUseCase {
        return GetDetailUserUseCase(databaseRepository)
    }

    @Provides
    fun provideGetUserUseCase(databaseRepository: DatabaseRepository): GetUserUseCase {
        return GetUserUseCase(databaseRepository)
    }

    @Provides
    fun provideUpdateUserUseCase(databaseRepository: DatabaseRepository): UpdateUserUseCase {
        return UpdateUserUseCase(databaseRepository)
    }

    @Provides
    fun provideGetContactUseCase(databaseRepository: DatabaseRepository): GetContactUseCase {
        return GetContactUseCase(databaseRepository)
    }

    @Provides
    fun provideSortByOptionUseCase(databaseRepository: DatabaseRepository) : FilterAndSortContactsUseCase {
        return FilterAndSortContactsUseCase(databaseRepository)
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