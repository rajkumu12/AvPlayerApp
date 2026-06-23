package com.example.interviewprepapp.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_home.ui.HomeScreen
import com.example.feature_login.ui.LoginScreen
import com.example.feature_login.ui.SignupScreen
import com.example.feature_player.ui.PlayerScreen
import com.example.interviewprepapp.SplashScreen
import com.example.interviewprepapp.SplashViewModel
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    val viewModel: SplashViewModel = hiltViewModel()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState(false)



    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable("splash") {

//            LaunchedEffect(Unit) {
//                delay(3000)
//
//                navController.navigate("login") {
//                    popUpTo("splash") {
//                        inclusive = true
//                    }
//                }
//            }

            LaunchedEffect(isLoggedIn) {

                delay(3000)

                if (isLoggedIn) {

                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }

                } else {

                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            }

            SplashScreen()
        }

        composable(
            Routes.LOGIN ,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut()
            },
            popEnterTransition = { fadeIn() },      // ← ADD THIS
            popExitTransition = { fadeOut() }
        ) {

            LoginScreen(
                onRegisterClick = {
                    navController.navigate(Routes.SIGNUP)
                },
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            Routes.SIGNUP,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut()
            },
            popEnterTransition = { fadeIn() },      // ← ADD THIS
            popExitTransition = { fadeOut() }
        ) {

            SignupScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onVideoClick = { video ->

                    navController.navigate(
                        "${Routes.PLAYER}/${Uri.encode(video.contentUri.toString())}"
                    )
                }

            )
        }

//        composable(
//            route = "${Routes.VIDEO_FOLDER}/{folderPath}"
//        ) { backStackEntry ->
//
//            val folderPath = Uri.decode(
//                backStackEntry.arguments
//                    ?.getString("folderPath")
//                    ?: ""
//            )
//
//            VideoFolderScreen(
//                folderPath = folderPath,
//                onVideoClick = { video ->
//
//                }
//            )
//        }
        composable(
            route = "${Routes.PLAYER}/{videoUri}"
        ) { backStackEntry ->

            val videoUri = Uri.parse(
                Uri.decode(
                    backStackEntry.arguments?.getString("videoUri")
                        ?: ""
                )
            )

            PlayerScreen(
                videoUri = videoUri
            )
        }



    }
}