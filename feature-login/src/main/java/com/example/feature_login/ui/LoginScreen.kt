package com.example.feature_login.ui



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.DarkBackground
import com.example.core_ui.PurplePrimary
import com.example.core_ui.R.*
import com.example.core_ui.TextFieldBg
import com.example.core_ui.TextGray
import com.example.core_ui.TextWhite
import com.example.core_ui.TopLayerBg1
import com.example.core_ui.ui.components.MusicButton
import com.example.core_ui.ui.components.MusicTextField
import com.example.feature_login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
    ) {
    val context = LocalContext.current

    var email by rememberSaveable() {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier.fillMaxSize().background(
            color = DarkBackground
        ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = Modifier.size(70.dp).background(color = TextFieldBg, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(drawable.logo),
                    contentDescription = null,

                    modifier = Modifier
                        .size(45.dp),
                    colorFilter = ColorFilter.tint(
                        PurplePrimary
                    )
                )

            }


            Text(
                text ="Welcome Back",
                color = TextWhite,
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text ="Login To Continue",
                color = TextGray,
                fontSize = 14.sp,
                style = TextStyle(fontWeight = FontWeight.Medium)
            )
            Spacer(
                modifier = Modifier.height(28.dp))

            MusicTextField(
                value = email,
                hint = "Enter Your Email",
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            MusicTextField(
                value = password,
                hint = "Enter Your Password",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.height(18.dp))

            MusicButton(
                text = "Login",
                onClick = {
                    viewModel.onLoginSuccess()

                    onLoginSuccess()
                }
            )
            Spacer(
                modifier = Modifier.height(28.dp))
            AuthFooterText(
                text1 = "New user ? ",
                text2 = "Register Now",
                onRegisterClicks = {

                   onRegisterClick()
                }
            )
        }
    }
}