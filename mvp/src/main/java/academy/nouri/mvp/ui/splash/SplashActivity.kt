package academy.nouri.mvp.ui.splash

import academy.nouri.mvp.BuildConfig
import academy.nouri.mvp.R
import academy.nouri.mvp.databinding.ActivitySplashBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.ui.main.MainActivity
import academy.nouri.mvp.ui.register.RegisterActivity
import academy.nouri.mvp.utils.USER_TOKEN
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import nouri.`in`.goodprefslib.GoodPrefs

class SplashActivity : BaseActivity() {
    //Binding
    private lateinit var binding: ActivitySplashBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Version
        binding.appVersionTxt.text = "${getString(R.string.version)} ${BuildConfig.VERSION_NAME}"
        //Timer
        Handler(Looper.getMainLooper()).postDelayed({
            if (GoodPrefs.getInstance().isKeyExists(USER_TOKEN)) {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                Intent(this, RegisterActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }, 1000)
    }
}