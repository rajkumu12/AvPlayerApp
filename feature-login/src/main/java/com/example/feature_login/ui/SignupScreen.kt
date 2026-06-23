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
import com.example.core_ui.DarkBackground
import com.example.core_ui.PurplePrimary
import com.example.core_ui.R.*
import com.example.core_ui.TextFieldBg
import com.example.core_ui.TextGray
import com.example.core_ui.TextWhite
import com.example.core_ui.TopLayerBg1
import com.example.core_ui.ui.components.MusicButton
import com.example.core_ui.ui.components.MusicTextField

@Composable
fun SignupScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var lastName by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable {
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
                text ="Welcome To YMusic",
                color = TextWhite,
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text ="SignUp To Continue",
                color = TextGray,
                fontSize = 14.sp,
                style = TextStyle(fontWeight = FontWeight.Medium)
            )
            Spacer(
                modifier = Modifier.height(28.dp))
            MusicTextField(
                value = firstName,
                hint = "Enter Your First Name",
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))
            MusicTextField(
                value = lastName,
                hint = "Enter Your last Name",
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))
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
            MusicTextField(
                value = confirmPassword,
                hint = "Enter Your Confirm Password",
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            MusicButton(
                text = "Login",
                onClick = {
                    Toast
                        .makeText(
                            context,
                            "Login Clicked",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
            Spacer(
                modifier = Modifier.height(28.dp))
            AuthFooterText(
                text1 = "Already have an account ? ",
                text2 = "Login Now",
                onRegisterClicks = {

                    onBackClick()
                }
            )
        }
    }
}