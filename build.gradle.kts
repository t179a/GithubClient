buildscript {
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.multiplatform") version "1.7.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20" apply false
    id("app.cash.molecule") version "0.6.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply false
    id("io.github.gmazzo.test.aggregation.coverage") version "1.1.0"
    // and/or
    id("io.github.gmazzo.test.aggregation.results") version "1.1.0"
}