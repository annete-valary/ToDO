package com.annete.task.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.annete.task.ui.screens.authentication.forgotPassword.ForgotPasswordScreen
import com.annete.task.ui.screens.authentication.login.LoginScreen
import com.annete.task.ui.screens.authentication.signUp.SignUpScreen
import com.annete.task.ui.screens.home.HomeScreen
import com.annete.task.ui.screens.onboarding.OnboardingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTES.LOGIN.name) {
        composable(ROUTES.LOGIN.name) {
            LoginScreen(
                onLoginClick = { navController.navigate(ROUTES.HOME.name) },
                onSignUpClick = { navController.navigate(ROUTES.SIGNUP.name) },
                onForgotPasswordClick = { navController.navigate(ROUTES.FORGOT_PASSWORD.name) }
            )
        }
        composable(ROUTES.SIGNUP.name) {
            SignUpScreen(
                onSignUpClick = { navController.navigate(ROUTES.HOME.name) },
                onLoginClick = { navController.navigate(ROUTES.LOGIN.name) }
            )
        }
        composable(ROUTES.FORGOT_PASSWORD.name) {
            ForgotPasswordScreen(
                onResetClick = { /* Handle reset logic */ },
                onBackToLoginClick = { navController.navigate(ROUTES.LOGIN.name) }
            )
        }
        composable(ROUTES.HOME.name) {
            HomeScreen()
        }
        composable(ROUTES.ONBOARDING.name) {
            OnboardingScreen()
        }
    }
}
