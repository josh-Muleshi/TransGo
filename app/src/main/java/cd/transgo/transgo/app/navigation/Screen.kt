package cd.transgo.transgo.app.navigation

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Auth : Screen("auth")
    object Translator : Screen("translator")
    object Setting : Screen("setting")
}


