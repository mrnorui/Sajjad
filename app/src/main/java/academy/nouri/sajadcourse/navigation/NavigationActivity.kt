package academy.nouri.sajadcourse.navigation

import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.databinding.ActivityNavigationBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class NavigationActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNavigationBinding

    //Other
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}