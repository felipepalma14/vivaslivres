package com.felipepalma14.vivaselivres.di

import com.felipepalma14.data.repository.LoginRepository
import com.felipepalma14.data.repository.LoginRepositoryImp
import com.felipepalma14.domain.usecase.LoginUseCase
import com.felipepalma14.domain.usecase.LoginUseCaseImp
import com.felipepalma14.vivaselivres.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MessageCollection

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GroupCollection

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun privateLoginRepository(
    ): LoginRepository {
        return LoginRepositoryImp()
    }

    @Singleton
    @Provides
    fun privateLoginUseCase(repository : LoginRepository): LoginUseCase{
        return LoginUseCaseImp(repository)
    }
}