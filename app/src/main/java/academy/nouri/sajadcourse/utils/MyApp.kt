package academy.nouri.sajadcourse.utils

import android.app.Application
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
                            .setDefaultFontPath("fonts/IranSans.ttf")
                            .build()
                    )
                )
                .build()
        )
    }
}