// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{

    dependencies{
        classpath ("com.android.tools.build:gradle:8.1.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        val nav_version = "2.7.0-alpha01"
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}


plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false

}