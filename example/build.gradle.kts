plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.gaumala.mvi.example"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gaumala.mvi.example"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":lib"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraint)
    implementation(libs.material)

    implementation(libs.groupie)
    implementation(libs.groupie.viewbinding)

    api(libs.autovalue.annotations)
    annotationProcessor(libs.autovalue)
    annotationProcessor(libs.autovalue.parcel)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}