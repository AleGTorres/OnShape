plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.onshape"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.onshape"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    // Habilita o View Binding, uma forma moderna de acessar views
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Padrão & UI
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Banco de Dados (Room)
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // Componentes de Arquitetura (ViewModel & LiveData)
    val lifecycle_version = "2.8.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")

    // Navegação (Navigation Component)
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Carregamento de Imagens (Glide)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // ✅ CORREÇÃO IMPORTANTE: Adicionada a dependência que estava faltando.
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // Views Customizadas
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Gráficos (MPAndroidChart)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}