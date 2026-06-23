package com.example.interviewprepapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.feature_login.ui.LoginScreen
import com.example.interviewprepapp.navigation.AppNavGraph
import kotlinx.coroutines.delay


@Composable
fun App() {

    AppNavGraph()
}