package com.jettaskboard.multiplatform.di

import com.jettaskboard.multiplatform.domain.usecase.GetRandomPhotoListUseCase
import com.jettaskboard.multiplatform.domain.usecase.SearchPhotoListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetRandomPhotoListUseCase(get()) }

    single { SearchPhotoListUseCase(get()) }
}
