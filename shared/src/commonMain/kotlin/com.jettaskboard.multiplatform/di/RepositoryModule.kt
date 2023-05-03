package com.jettaskboard.multiplatform.di

import com.jettaskboard.multiplatform.data.repo.PhotoRepoImpl
import com.jettaskboard.multiplatform.data.source.local.preferences.UserPreferences
import com.jettaskboard.multiplatform.data.source.remote.PhotoDataSourceImpl
import com.jettaskboard.multiplatform.domain.repo.PhotoRepo
import org.koin.dsl.module

val repositoryModule = module {

    single<PhotoRepo> { PhotoRepoImpl(get<PhotoDataSourceImpl>()) }
}

val dataSourceModule = module {

    single { PhotoDataSourceImpl(get()) }
}

val preferencesModule = module {

    single { UserPreferences() }
}