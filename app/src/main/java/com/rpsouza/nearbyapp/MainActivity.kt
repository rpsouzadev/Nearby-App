package com.rpsouza.nearbyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rpsouza.nearbyapp.core.route.AppRoutes
import com.rpsouza.nearbyapp.data.model.market.Market
import com.rpsouza.nearbyapp.ui.screen.home.HomeScreen
import com.rpsouza.nearbyapp.ui.screen.market_details.MarketDetailsScreen
import com.rpsouza.nearbyapp.ui.screen.splash.SplashScreen
import com.rpsouza.nearbyapp.ui.screen.welcome.WelcomeScreen
import com.rpsouza.nearbyapp.ui.theme.NearbyTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme() {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.Splash
                ) {
                    composable<AppRoutes.Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(AppRoutes.Welcome) {
                                    popUpTo(AppRoutes.Splash) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable<AppRoutes.Welcome> {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(AppRoutes.Home) {
                                    popUpTo(AppRoutes.Welcome) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable<AppRoutes.Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { marketSelected ->
                                navController.navigate(marketSelected)
                            }
                        )
                    }
                    composable<Market>() { navBackStackEntry ->
                        val selectedMarket = navBackStackEntry.toRoute<Market>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}