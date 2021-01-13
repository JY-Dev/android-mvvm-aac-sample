package com.jydev.riiidsimapleapllication.di

import com.jydev.riiidsimapleapllication.data.datasource.RemoteDataSource
import com.jydev.riiidsimapleapllication.data.datasource.RemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            service = get()
        )
    }
}