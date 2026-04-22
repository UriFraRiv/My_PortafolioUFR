package com.example.progenickfr.navigation


import kotlinx.serialization.Serializable

@Serializable object RouteLogin
@Serializable data class RouteAnimation(val userId: String)
@Serializable data class RouteHome(val userId: String)
@Serializable object RouteMachine

@Serializable object MaterialWarehouse

@Serializable object Profile