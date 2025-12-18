package com.example.progenickfr.ViewModel.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.progenickfr.R
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navigatetoNewAccount: () -> Unit={},
                navigatetoStartPerfil: () -> Unit={},
)
{
    var Valcorreo by remember { mutableStateOf("") }
    var ValPassword by remember { mutableStateOf("") }
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        showLoading= false
    }
    if (showLoading)
    {val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.manrobt))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ) {
            LottieAnimation(composition = animation)
        }} else {
        Scaffold { pading ->
            Column(

                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 20.dp, horizontal = 10.dp)
                    .padding(pading)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_progenicktransp),
                    contentDescription = "", modifier = Modifier.size(220.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text("!Hola, Gracias por ser parte de nosotros!", fontSize = 25.sp)

                Spacer(modifier = Modifier.height(35.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = Valcorreo,
                    onValueChange = { Valcorreo = it },
                    shape = RoundedCornerShape(25),

                    label = { Text(text = stringResource(id = R.string.login_screen_text_email)) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = ValPassword,
                    onValueChange = { ValPassword = it },
                    shape = RoundedCornerShape(25),

                    label = { Text(text = stringResource(id = R.string.login_screen_text_password)) }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  } // botón Google temporal
                ) {
                    Text("Iniciar sesión con Google")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navigatetoStartPerfil()},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Blue,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.login_screen_text_login))

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.login_screen_text_forgotten_password),
                    Modifier.clickable(onClick = {})
                )
                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navigatetoNewAccount() }) {
                    Text(text = stringResource(R.string.login_screen_text_New_Acoount))
                }
                Spacer(modifier = Modifier.height(10.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )


            }
        }
    }
}