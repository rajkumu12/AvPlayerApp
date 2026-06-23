package com.example.feature_login.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.core_ui.PurplePrimary

@Composable
fun AuthFooterText(
    text1: String,
    text2: String,
    onRegisterClicks: () -> Unit

) {

    Text(
        buildAnnotatedString {

            append(text1)

            withLink(
                LinkAnnotation.Clickable(
                    tag = "register",
                    linkInteractionListener = {

                        onRegisterClicks()
                    }
                )
            ) {

                withStyle(
                    style = SpanStyle(
                        color = PurplePrimary,
                        fontWeight = FontWeight.Bold
                    )
                ) {

                    append(text2)
                }
            }
        },
        color = Color.White,
        fontSize = 15.sp
    )
}