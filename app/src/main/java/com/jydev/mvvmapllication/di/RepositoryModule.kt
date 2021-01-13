package com.jydev.riiidsimapleapllication.di

import com.jydev.riiidsimapleapllication.data.repository.Repository
import com.jydev.riiidsimapleapllication.data.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository>{
        RepositoryImpl(
            source = get()
        )
    }
}