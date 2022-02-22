package com.foobear.square

import android.app.Application
import com.foobear.square.data.SquareApi
import com.foobear.square.data.repo.EmployeeRepository
import com.foobear.square.data.repo.EmployeeRepositoryImpl
import com.foobear.square.network.datasource.EmployeeListDataSource
import com.foobear.square.network.datasource.EmployeeListDataSourceImpl
import com.foobear.square.ui.emplist.EmployeeListViewModel
import com.foobear.square.ui.emplist.EmployeeListViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class SquareApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@SquareApplication))

        bind() from singleton { SquareApi() }

        bind() from provider { EmployeeListViewModelFactory(instance()) }
        bind() from singleton { EmployeeListViewModel(instance()) }

        bind<EmployeeListDataSource>() with singleton {
            EmployeeListDataSourceImpl(instance())
        }

        bind<EmployeeRepository>() with singleton {
            EmployeeRepositoryImpl(instance())
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}