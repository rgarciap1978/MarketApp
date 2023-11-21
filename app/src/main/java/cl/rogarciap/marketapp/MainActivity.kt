package cl.rogarciap.marketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import cl.rogarciap.marketapp.views.SettingNavigation
import cl.rogarciap.marketapp.ui.theme.MarketAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SettingNavigation(navController = navController)
            }
        }
    }
}