package com.example.usersappcompose.di

import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.add_contact_use_case.SaveToDbUseCase
import com.example.domain.use_cases.contact_list_use_case.FilterAndSortContactsUseCase
import com.example.domain.use_cases.contact_list_use_case.GetContactUseCase
import com.example.domain.use_cases.create_user_use_case.SaveCurrentUserUseCase
import com.example.domain.use_cases.detail_user_use_case.DeleteContactUseCase
import com.example.domain.use_cases.detail_user_use_case.GetDetailUserUseCase
import com.example.domain.use_cases.edit_user_use_case.GetUserUseCase
import com.example.domain.use_cases.edit_user_use_case.UpdateUserUseCase
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.core.RouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    fun provideSortByOptionUseCase(databaseRepository: DatabaseRepository): FilterAndSortContactsUseCase {
        return FilterAndSortContactsUseCase(databaseRepository)
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface Binds {
    @Binds
    @Singleton
    fun provideRouter(impl: RouterImpl): Router
}