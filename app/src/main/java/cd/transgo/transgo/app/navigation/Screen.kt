package cd.transgo.transgo.app.navigation

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Auth : Screen("auth")
    object Result : Screen("addPost")
    object Setting : Screen("setting")
}


