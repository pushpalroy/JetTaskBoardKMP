package com.jettaskboard.multiplatform.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            dataSourceModule,
            repositoryModule,
            preferencesModule,
            useCaseModule
        )
    }
}
