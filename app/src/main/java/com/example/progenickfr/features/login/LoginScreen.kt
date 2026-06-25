package com.example.progenickfr.features.login

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.progenickfr.R
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    // Inyectamos el ViewModel
    loginViewModel: LoginViewModel,
    navigatetoNewAccount: () -> Unit = {},
    navigatetoHome: (String) -> Unit = {}
) {
    val passwordVisible = remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(2000)
        showLoading = false
    }

    if (showLoading) {
        val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.manrobt))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ) {
            LottieAnimation(composition = animation)
        }
    } else {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_progenicktransp),
                    contentDescription = null,
                    modifier = Modifier.size(220.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text("¡Hola, Gracias por ser parte de nosotros!", fontSize = 25.sp)
                Spacer(modifier = Modifier.height(35.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginViewModel.email,
                    onValueChange = { loginViewModel.email = it },
                    shape = RoundedCornerShape(25),
                    label = { Text(text = stringResource(id = R.string.login_screen_text_email)) }
                )

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it },
                    shape = RoundedCornerShape(25),
                    label = { Text(text = stringResource(id = R.string.login_screen_text_password))
                    },visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),  // 3. Transforma el texto a puntitos solo para la pantalla
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible.value) {
                            Icons.Filled.CheckCircle
                        } else {
                            Icons.Filled.Check
                        }
                        val description =
                            if (passwordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        IconButton(onClick = {
                            passwordVisible.value = !passwordVisible.value
                        })
                        {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        loginViewModel.iniciarSesion { uid ->
                            navigatetoHome(uid)
                        }
                    },
                    enabled = !loginViewModel.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    if (loginViewModel.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    } else {
                        Text(text = stringResource(id = R.string.login_screen_text_login))
                    }
                }
                //error si la validación falla
                loginViewModel.errorMsg?.let {
                    Text(text = it, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = stringResource(R.string.login_screen_text_New_Acoount),
                    modifier = Modifier.clickable { navigatetoNewAccount() },
                    color = Color.Gray
                )
            }
        }
    }
}