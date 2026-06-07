import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.3.21"
    id("org.jetbrains.dokka") version "1.8.20"
}

android {
    namespace = "com.example.accountingofstudentretakesapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.accountingofstudentretakesapp"
        minSdk = 26
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
    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation("io.ktor:ktor-client-okhttp:3.0.0")
    implementation("io.ktor:ktor-client-logging:3.0.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    implementation("io.ktor:ktor-client-android:3.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.3")
    implementation("io.ktor:ktor-client-logging:3.0.3")
    implementation("io.ktor:ktor-client-auth:3.0.3")
    implementation("io.ktor:ktor-client-core")

    //implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.navigation:navigation-compose:2.9.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.6.1")
}


tasks.named("dokkaHtml", org.jetbrains.dokka.gradle.DokkaTask::class) {
    outputDirectory.set(layout.buildDirectory.dir("dokka/html").get().asFile) // // куда сохранить HTML
    dokkaSourceSets {
        named("main") {
            moduleName.set("AccountingOfStudentRetakesApp")
            jdkVersion.set(11)
            sourceRoots.from(file("src/main/java"), file("src/main/kotlin")) // // название модуля в доках
            perPackageOption {
                matchingRegex.set("com.example.*") // где искать исходники
                includeNonPublic.set(false)
            }
        }
    }
}

tasks.named("dokkaJavadoc", org.jetbrains.dokka.gradle.DokkaTask::class) {
    outputDirectory.set(layout.buildDirectory.dir("dokka/javadoc").get().asFile)
}

tasks.register<org.gradle.jvm.tasks.Jar>("dokkaJavadocJar") {
    dependsOn("dokkaJavadoc")
    archiveClassifier.set("javadoc")
    from(layout.buildDirectory.dir("dokka/javadoc").get().asFile)
}
