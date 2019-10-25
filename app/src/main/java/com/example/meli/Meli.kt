package com.example.meli

import android.app.Application
import com.example.meli.data.network.*
import com.example.meli.data.repository.MeliRepository
import com.example.meli.data.repository.MeliRepositoryImpl
import com.example.meli.ui.Result.ResultFragmentViewModelFactory
import com.example.meli.ui.SharedViewModelFactory
import com.example.meli.ui.search.SearchProductViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class Meli: Application(), KodeinAware{
    override val kodein= Kodein.lazy {
        import(androidModule(this@Meli))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { MeliApiService(instance()) }
        bind<ProductResponseDataSource>() with singleton {
            ProductResponseDataSourceImpl(instance())
        }
        bind<MeliRepository>() with singleton {
            MeliRepositoryImpl(instance())
        }
        bind() from provider { SearchProductViewModelFactory(instance())}
        bind() from provider { ResultFragmentViewModelFactory() }
        bind() from provider { SharedViewModelFactory() }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this@Meli)
    }
}