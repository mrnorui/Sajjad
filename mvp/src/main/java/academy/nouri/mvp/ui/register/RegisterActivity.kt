package academy.nouri.mvp.ui.register

import academy.nouri.mvp.R
import academy.nouri.mvp.data.model.register.BodyRegister
import academy.nouri.mvp.data.model.register.ResponseRegister
import academy.nouri.mvp.databinding.ActivityRegisterBinding
import academy.nouri.mvp.ui.base.BaseActivity
import academy.nouri.mvp.ui.main.MainActivity
import academy.nouri.mvp.utils.USER_TOKEN
import academy.nouri.mvp.utils.isNetworkAvailable
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import nouri.`in`.goodprefslib.GoodPrefs

class RegisterActivity : BaseActivity(), RegisterContracts.View {
    //Binding
    private lateinit var binding: ActivityRegisterBinding

    //Other
    private val presenter by lazy { RegisterPresenter(this) }
    private val body = BodyRegister()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            registerBtn.setOnClickListener {
                body.email = emailEdt.text.toString()
                body.password = passwordEdt.text.toString()
                body.name = nameEdt.text.toString()
                //Call presenter
                presenter.callRegisterUser(body)
            }
        }
    }

    override fun loadRegister(data: ResponseRegister) {
        GoodPrefs.getInstance().saveString(USER_TOKEN, data.email)
    }

    override fun gotoMainPage() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun checkNetworkConnection(): Boolean {
        return isNetworkAvailable()
    }

    override fun networkConnectionError() {
        Toast.makeText(this, "اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show()
    }

    override fun responseError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.registerLoading.visibility = View.VISIBLE
        binding.registerBtn.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        binding.registerLoading.visibility = View.GONE
        binding.registerBtn.visibility = View.VISIBLE
    }
}