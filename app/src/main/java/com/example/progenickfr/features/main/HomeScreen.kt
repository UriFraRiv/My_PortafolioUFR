package com.example.progenickfr.features.main

import androidx.compose.runtime.getValue  // <--- AGREGA ESTA
import androidx.compose.runtime.setValue  // <--- AGREGA ESTA

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.progenickfr.R
import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(
//    viewModel: PerfilViewModel = viewModel(),
//    navigatetoProfile: () -> Unit,
//    navigatetoAttendance: () -> Unit,
//    navigatetoCount: () -> Unit,
//    navigatetoMachine: () -> Unit
//) {
//    val slectdScren = listOf(
//        navigatetoProfile,
//        navigatetoAttendance,
//        navigatetoCount,
//        navigatetoMachine)
//    val selectScreen = listOf(
//        "Inicio Perfil",
//        "Asistencia",
//        "Conteo",
//        "Maquinas"
//    )
//    val drawerIcons = listOf(
//        Icons.Default.AccountCircle,
//        Icons.Default.AccountBox,
//        Icons.Default.CheckCircle,
//        Icons.Default.Notifications,
//            )
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            // Fondo rojo
//            ModalDrawerSheet(
//                modifier = Modifier.background(Color.Red)
//            ) {
//                selectScreen.forEachIndexed { index, item ->
//                    NavigationDrawerItem(
//                        label = {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween, // texto a la izquierda, icono a la derecha
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(item, color = Color.White)
//                                Icon(
//                                    imageVector = drawerIcons[index], // aquí puedes cambiar el icono
//                                    contentDescription = null,
//                                    tint = Color.White
//                                )
//                            }
//                        },
//                        selected = viewModel.selectedDrawerIndex.value == index,
//                        onClick = {
//                            viewModel.selectedDrawerIndex.value = index
//                            scope.launch {
//                                slectdScren[index]()
//                            }
//                        },
//                        colors = NavigationDrawerItemDefaults.colors(
//                            selectedContainerColor = Color(0xFFB71C1C), // rojo más oscuro al seleccionar
//                            unselectedContainerColor = Color.Transparent,
//                            selectedTextColor = Color.White,
//                            unselectedTextColor = Color.White
//                        ),
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    )
//                }
//            }
//        }
//    ) {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text("Perfil") },
//                    navigationIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Menu,
//                            contentDescription = "Menu",
//                            modifier = Modifier.clickable {
//                                scope.launch { drawerState.open() }
//                            }
//                        )
//                    }
//                )
//            }
//        ) { paddingValues ->
//            Column(
//                modifier = Modifier.Companion
//                    .fillMaxSize()
//                    .background(Color.Companion.Black)
//                    .padding(paddingValues),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.Companion.CenterHorizontally
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.ic_progenicktransp),
//                    contentDescription = "", modifier = Modifier.Companion.size(300.dp)
//                )
//
//                Spacer(Modifier.Companion.height(20.dp))
//                Text(
//                    text = "name",
//                    color = Color.Companion.White,
//                    fontSize = 25.sp,
//                )
//                Spacer(Modifier.Companion.height(10.dp))
//
//                Text(
//                    text = "Puesto", fontSize = 20.sp,
//                    color = Color.Companion.White,
//                )
//
//                Spacer(Modifier.Companion.height(10.dp))
//                Text(
//                    "lugar de procedencia", fontSize = 20.sp,
//                    color = Color.Companion.White,
//                )
//
//                Spacer(Modifier.Companion.height(10.dp))
//                Text(
//                    "No.nomina", fontSize = 20.sp,
//                    color = Color.Companion.White,
//                )
//
//                Spacer(Modifier.Companion.height(10.dp))
//                Row(
//                    verticalAlignment = Alignment.Companion.CenterVertically
//                ) {
//                    Text(
//                        text = "¿?",
//                        fontSize = 20.sp
//                    )
//                    Icon(
//                        imageVector = Icons.Default.Create,
//                        contentDescription = "Editar",
//                        modifier = Modifier.Companion
//                            .padding(start = 8.dp) // separación del texto
//                            .clickable {
//                                // Aquí pones lo que hará el ícono
//                            }
//                    )
//                }
//
//                Spacer(Modifier.Companion.height(20.dp))
//
//
//                Button(
//                    onClick = {},
//                    modifier = Modifier.Companion.fillMaxWidth(0.5f),
//
//                    )
//                { Text("Editar Datos") }
//
//            }
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    userId: String,
    navigatetoProfile: () -> Unit,
    navigatetoAttendance: () -> Unit,
    navigatetoCount: () -> Unit,
    navigatetoMachine: () -> Unit
) {
    // 1. Cargamos datos cuando entramos
    LaunchedEffect(userId) {
        if (userId.isNotEmpty()) {
            viewModel.cargarDatos(userId)
        }
    }

    val user = viewModel.userProfile
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // --- LISTAS PARA EL DRAWER (Deben estar aquí si no son globales) ---
    val slectdScren = listOf(navigatetoProfile, navigatetoAttendance, navigatetoCount, navigatetoMachine)
    val selectScreen = listOf("Inicio Perfil", "Asistencia", "Material", "Maquinas")
    val drawerIcons = listOf(Icons.Default.AccountCircle, Icons.Default.AccountBox, Icons.Default.CheckCircle, Icons.Default.Notifications)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                // Cambiado para que el contenedor sea rojo
                drawerContainerColor = Color.Red
            ) {
                Spacer(Modifier.height(12.dp))
                selectScreen.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item, color = Color.White)
                                Icon(imageVector = drawerIcons[index], contentDescription = null, tint = Color.White)
                            }
                        },
                        // CORRECCIÓN: Usamos .intValue para mutableIntStateOf
                        selected = viewModel.selectedDrawerIndex.intValue == index,
                        onClick = {
                            viewModel.selectedDrawerIndex.intValue = index
                            scope.launch {
                                drawerState.close()
                                slectdScren[index]()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFFB71C1C),
                            unselectedContainerColor = Color.Transparent,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Perfil de Usuario") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_progenicktransp),
                    contentDescription = "Logo",
                    modifier = Modifier.size(300.dp)
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = user?.name ?: "Cargando...",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = user?.position ?: "Sin puesto",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Turno: ${user?.shift ?: "N/A"}",
                    fontSize = 20.sp,
                    color = Color.White,
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "No. Nómina: ${user?.nomina}",
                    fontSize = 18.sp,
                    color = Color.White,
                )

                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = { /* Acción para editar */ },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Editar Datos", color = Color.White)
                }
            }
        }
    }
}