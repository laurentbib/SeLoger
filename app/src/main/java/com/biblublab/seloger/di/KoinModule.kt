package com.biblublab.seloger.di

import com.biblublab.data.remote.RealPropertyApi
import com.biblublab.data.repository.RealPropertyMapper
import com.biblublab.data.repository.RealPropertyRepositoryImpl
import com.biblublab.domain.common.AppCoroutineDispatchers
import com.biblublab.domain.repository.RealPropertyRepository
import com.biblublab.domain.usecase.FetchDataUseCase
import com.biblublab.domain.usecase.GetDetailUseCase
import com.biblublab.seloger.features.EntityMapper
import com.biblublab.seloger.features.detail.DetailViewModel
import com.biblublab.seloger.features.main.MainViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module{
    single { provideLoggingInterceptor() }
    single { providesOkHttpClientBuilder(get()) }
    single { providesRetrofit(get()) }
    factory { provideRetrofitApi(get()) }
}

val dispatchersModule = module {
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }
}

val useCaseModule = module{
    factory { FetchDataUseCase(realPropertyRepository = get(), appCoroutineDispatchers = get()) }
    factory { GetDetailUseCase(realPropertyRepository = get(), appCoroutineDispatchers = get()) }
}

val featureModule = module{
    single { RealPropertyMapper() }
    single { EntityMapper(androidContext()) }
    single<RealPropertyRepository> { RealPropertyRepositoryImpl(realPropertyApi = get(), realPropertyMapper = get()) }
    viewModel { MainViewModel(fetchDataUseCase = get(), entityMapper = get()) }
    viewModel { DetailViewModel(getDetailUseCase = get(), entityMapper = get()) }
}

private fun provideLoggingInterceptor() : HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


private fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://gsl-apps-technical-test.dignp.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()


private fun provideRetrofitApi(retrofit: Retrofit) : RealPropertyApi =
    retrofit.create(RealPropertyApi::class.java)

private fun providesOkHttpClientBuilder(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .apply {
            loggingInterceptor.also {
                addInterceptor(it)
            }
        }.build()