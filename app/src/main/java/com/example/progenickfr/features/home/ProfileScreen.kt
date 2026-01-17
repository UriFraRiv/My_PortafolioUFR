package com.example.progenickfr.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.progenickfr.R

@Composable
fun Profile() {

    Column(
        modifier = Modifier.Companion.fillMaxSize().background(Color.Companion.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_progenicktransp),
            contentDescription = "", modifier = Modifier.Companion.size(130.dp)
        )

        Spacer(Modifier.Companion.height(20.dp))
        Text(
            text = "",
            color = Color.Companion.Black,
            fontSize = 25.sp
        )
        Spacer(Modifier.Companion.height(10.dp))

        Text(text = "", fontSize = 20.sp)

        Spacer(Modifier.Companion.height(10.dp))
        Text("", fontSize = 20.sp)

        Spacer(Modifier.Companion.height(10.dp))
        Text("", fontSize = 20.sp)

        Spacer(Modifier.Companion.height(10.dp))
        Row(
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                text = "",
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Editar",
                modifier = Modifier.Companion
                    .padding(start = 8.dp) // separación del texto
                    .clickable {
                        // Aquí pones lo que hará el ícono
                    }
            )
        }

        Spacer(Modifier.Companion.height(20.dp))


        Button(
            onClick = {},
            modifier = Modifier.Companion.fillMaxWidth(),

            )
        { Text("Editar Datos") }

    }
}