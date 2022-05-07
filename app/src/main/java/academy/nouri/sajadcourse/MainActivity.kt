package academy.nouri.sajadcourse

import academy.nouri.sajadcourse.databinding.ActivityMainBinding
import academy.nouri.sajadcourse.utils.KEY_BG_IMAGE
import academy.nouri.sajadcourse.utils.KEY_USER_EMAIL
import academy.nouri.sajadcourse.utils.KEY_USER_PASSWORD
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private var email = ""
    private var password = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Default action
            checkedViews(createAccountBtn, false, 0.5f)
            //Checked
            termsCheck.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedViews(createAccountBtn, true, 1.0f)
                } else {
                    checkedViews(createAccountBtn, false, 0.5f)
                }
            }
            //Click
            createAccountBtn.setOnClickListener {
                //Email
                if (emailEdt.text.toString().isEmpty()) {
                    emailInp.error = "Email can not be empty"
                } else {
                    emailInp.error = ""
                    email = emailEdt.text.toString()
                }
                //Password
                if (passwordEdt.text.toString().isEmpty()) {
                    passwordInp.error = "Password can not be empty"
                } else {
                    passwordInp.error = ""
                    password = passwordEdt.text.toString()
                }
                //Both
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(KEY_USER_EMAIL, email)
                    intent.putExtra(KEY_USER_PASSWORD, password)
                    intent.putExtra(KEY_BG_IMAGE, R.drawable.my_img)
                    startActivity(intent)
                }
            }
        }
    }

    private fun checkedViews(view: View, isChecked: Boolean, alpha: Float) {
        view.isEnabled = isChecked
        view.isClickable = isChecked
        view.alpha = alpha
    }
}