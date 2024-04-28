// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) version "1.9.0" apply false
    kotlin("kapt") version "1.9.23" apply false
}

buildscript{
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.4")
    }
}