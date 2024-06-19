// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:2.7.7")


    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    kotlin("plugin.serialization") version "1.9.24"
    id ("androidx.room")version "2.6.1" apply false
    kotlin("jvm") version "1.9.24"



//    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}