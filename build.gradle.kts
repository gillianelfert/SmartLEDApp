buildscript {
    val agp_version by extra("8.1.0-beta03")
    val agp_version1 by extra("8.1.0-beta03")
    val agp_version2 by extra("8.1.0-beta03")
    val agp_version3 by extra("8.1.0-beta03")
    val agp_version4 by extra("8.1.0-beta03")

    repositories{
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta04" apply false
}