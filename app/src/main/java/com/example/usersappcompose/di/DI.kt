package com.example.usersappcompose.di

import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.add_contact.use_case.SaveToDbUseCase
import com.example.domain.use_cases.contact_list.use_cases.FilterAndSortContactsUseCase
import com.example.domain.use_cases.contact_list.use_cases.GetContactUseCase
import com.example.domain.use_cases.create_user.use_case.SaveCurrentUserUseCase
import com.example.domain.use_cases.detail_user.use_cases.DeleteContactUseCase
import com.example.domain.use_cases.detail_user.use_cases.GetDetailUserUseCase
import com.example.domain.use_cases.edit_user.use_cases.GetUserUseCase
import com.example.domain.use_cases.edit_user.use_cases.UpdateUserUseCase
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