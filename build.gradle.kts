buildscript {
    val agp_version by extra("8.1.0-beta03")

    repositories{
        google()
        mavenCentral()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta03" apply false
}