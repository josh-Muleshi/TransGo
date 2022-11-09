package cd.transgo.transgo.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cd.transgo.transgo.MainViewModel
import cd.transgo.transgo.presentation.auth.AuthScreen
import cd.transgo.transgo.presentation.home.HomeScreen
import cd.transgo.transgo.presentation.splash.SplashScreen
import cd.transgo.transgo.presentation.translator.TranslatorScreen

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Splash.route){
            SplashScreen(navController)
        }
        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController, viewModel)
        }
        composable(route = Screen.Translator.route){
            TranslatorScreen(navController, viewModel)
        }
    }
}
