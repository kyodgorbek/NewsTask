package com.yodgorbek.newstask.di


import android.content.Context
import com.yodgorbek.newstask.domain.repository.NewsRepository
import com.yodgorbek.newstask.domain.use_case.BBCNewsResponseUseCase
import com.yodgorbek.newstask.domain.use_case.DetailNewsUseCase
import com.yodgorbek.newstask.domain.utils.Constants
import com.yodgorbek.newstask.data.internet.NewsInterface
import com.yodgorbek.newstask.presentation.viewmodel.BBCNewsViewModel
import com.yodgorbek.newstask.presentation.viewmodel.DetailNewsViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Modules {

    val viewModels = module {
        viewModel { BBCNewsViewModel(get()) }
        viewModel { DetailNewsViewModel(get()) }

    }

    val apiModule = module {

        factory { NewsRepository(get()) }
        factory { BBCNewsResponseUseCase(get()) }
        factory { DetailNewsUseCase(get()) }

        single<NewsInterface> {
            provideRetrofit(get<OkHttpClient>())
        }

        factory<OkHttpClient> {
            provideOkHttpClient(get<Cache>())
        }

        factory<Cache> {
            provideCache(get<Context>())
        }
    }
}


fun provideCache(context: Context): Cache {
    val cacheSize: Long = 10 * 1024 * 1024
    return Cache(context.cacheDir, cacheSize)
}

fun provideOkHttpClient(cache: Cache): OkHttpClient {
    val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(logger)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): NewsInterface {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsInterface::class.java)
}