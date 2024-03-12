// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    application
    id("com.android.application") version "8.3.0" apply false
    id("com.android.library") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    kotlin("jvm") version "1.8.20" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "1.8.20"
    //id("com.android.application") version "8.1.0" apply false
    //id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    //id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    //id("com.android.library") version "7.1.1" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}