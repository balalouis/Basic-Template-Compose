package com.basic.template.compose.screen

interface ScreenDestination {
    val route: String
}

object SplashScreen : ScreenDestination {
    override val route: String
        get() = "Splash"
}

object LoginScreen : ScreenDestination {
    override val route: String
        get() = "Login"
}

object RegisterScreen : ScreenDestination {
    override val route: String
        get() = "Register"
}

object UserListScreen : ScreenDestination {
    override val route: String
        get() = "UserList"
}

object UserDetailScreen : ScreenDestination {
    override val route: String
        get() = "Detail"
}

object AboutScreen : ScreenDestination {
    override val route: String
        get() = "About"
}

object SettingsScreen : ScreenDestination {
    override val route: String
        get() = "Settings"
}