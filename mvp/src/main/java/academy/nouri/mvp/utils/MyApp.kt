package academy.nouri.mvp.utils

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import nouri.`in`.goodprefslib.GoodPrefs

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //GoodPrefs
        GoodPrefs.init(applicationContext)
        //Calligraphy
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Dosis.ttf")
                            .build()
                    )
                )
                .build()
        )
        //Download
        val config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .setReadTimeout(60000)
            .setConnectTimeout(60000)
            .build()
        PRDownloader.initialize(applicationContext, config)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}