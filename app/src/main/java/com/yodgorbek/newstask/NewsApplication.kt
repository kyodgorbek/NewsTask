package com.yodgorbek.newstask
import android.app.Application
import com.yodgorbek.newstask.di.Modules.apiModule

import com.yodgorbek.newstask.di.Modules.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@NewsApplication)

            modules(listOf(viewModels, apiModule))
        }
    }

}