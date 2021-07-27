package ui.smartpro.testtaskwweather.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import ui.smartpro.testtaskwweather.BuildConfig

class ApplicationModule: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ApplicationModule)
//            modules(networkApiModule)
            modules(viewModelModules)
            modules(dataModule)


        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        var instance: ApplicationModule? = null
            private set
    }
}