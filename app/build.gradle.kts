plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")

//    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tyj.fetchrewardscodingexercise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tyj.fetchrewardscodingexercise"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
//        create("customDebugType") {
//            isDebuggable = true
//            signingConfig = signingConfigs.getByName("debug")
//        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
    }


//    dataBinding {
//        android.buildFeatures.dataBinding = true
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation ("androidx.compose.material:material:1.7.0-alpha04")
    implementation ("androidx.compose.material:material-icons-extended")

    implementation ("com.google.accompanist:accompanist-permissions:0.30.0")

    // Coroutine
    val coroutinesVersion: String = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
//    implementation("com.google.dagger:hilt-android:2.40.5")
//    kapt ("com.google.dagger:hilt-android-compiler:2.40.5")
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Location Services
    // implementation ("com.google.android.gms:play-services-location:21.0.1")


    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")



    /*
    // KotlinX Serialization
    //implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

     */

    // Compose dependencies
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // FlowRow
    // implementation ("com.google.accompanist:accompanist-flowlayout:0.17.0")

    /*
    // Paging 3.0
    val paging_version = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
    // implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha14")

     */

    /*
    // Room Database
    val roomVersion: String = "2.5.2"


    // Room and Lifecycle dependencies
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    // kotlin extensions for coroutine support with room
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation ("androidx.room:room-paging:$roomVersion")

     */
    val activityVersion: String = "1.8.2"
    // kotlin extensions for coroutine support with activities
    implementation("androidx.activity:activity-ktx:$activityVersion")

    // Coil
    //implementation("io.coil-kt:coil-compose:1.3.2")

    /*
    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    //ksp("com.github.bumptech.glide:ksp:4.11.0")
    //implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.15.1")
    //ksp("com.github.bumptech.glide:ksp:4.15.1")

     */

    /*
    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

     */

    /*
    // Firebase Firestore
//    implementation("com.google.firebase:firebase-firestore:24.9.0")
    implementation("com.google.firebase:firebase-firestore:24.10.3")

    // Firebase Storage KTX
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")



    // Firebase Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

     */


    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")
}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}