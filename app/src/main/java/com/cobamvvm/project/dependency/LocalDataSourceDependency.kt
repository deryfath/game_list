package com.cobamvvm.project.dependency

import com.cobamvvm.project.data.local.DataBase
import com.cobamvvm.project.data.local.LocalDataSource
import org.koin.dsl.module.applicationContext

val localDataSourceDependency = applicationContext {

    bean { LocalDataSource(get()) }
    bean { DataBase.getInstance(get()) }
}