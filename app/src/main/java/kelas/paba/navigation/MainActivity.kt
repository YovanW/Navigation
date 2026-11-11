package kelas.paba.navigation

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kelas.paba.navigation.ui.theme.NavigationTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.RippleConfiguration
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kelas.paba.navigation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding!!.toolbar) { v, insets ->
            val systemBard = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = systemBard.top
            v.layoutParams = params
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding!!.navView){v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, systemBars.bottom)
            insets
        }

        binding?.apply{
            setSupportActionBar(toolbar)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as NavHostFragment
        navController = navHostFragment.navController
        binding!!.navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
    
    override fun onDestroy(){
        super.onDestroy()
        binding = null
    }
}