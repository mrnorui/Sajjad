package academy.nouri.mvp.ui.main

import academy.nouri.mvp.R
import academy.nouri.mvp.databinding.ActivityMainBinding
import academy.nouri.mvp.ui.base.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : BaseActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Navigation
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
        }
    }
}