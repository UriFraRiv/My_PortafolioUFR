plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.progenickfr"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.progenickfr"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //New Dependencias

    // --- FIREBASE (Importante para MVVM) ---
    // Usamos el BoM para que las versiones de Firebase no choquen entre sí
    implementation(platform(libs.androidx.compose.bom)) // BOM de Compose
    implementation(platform(libs.firebase.bom))        // BOM de Firebase

    implementation(libs.firebase.auth)          // Autenticación (Login)
    implementation(libs.firebase.firestore)     // Base de datos (Users/Reports)


    //Lottie-Animación
    implementation("com.airbnb.android:lottie-compose:6.6.2")

    //NavHosto
    implementation("androidx.navigation:navigation-compose:2.8.2")

    //Serializable/JSON
    implementation(libs.kotlinx.serialization.json)



///Default System
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}