import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.orderize.orderize"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.orderize.orderize"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val orderizeBaseUrlTag = "ORDERIZE_BASE_URL"
        var orderizeBaseUrl = ""
        try {
            val props = Properties()
            props.load(FileInputStream(File("$rootDir/local.properties")))
            orderizeBaseUrl = props[orderizeBaseUrlTag].toString()
        } catch (ignored: IOException) {
            orderizeBaseUrl = System.getenv(orderizeBaseUrlTag)
        }

        if (orderizeBaseUrl.isBlank()) {
            throw GradleException("ORDERIZE_BASE_URL não encontrada")
        }
        buildConfigField("String", orderizeBaseUrlTag, "\"$orderizeBaseUrl\"")
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
        compose = true
        buildConfig = true
    }
}

dependencies {

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

    //navigation
    implementation(libs.androidx.navigation.compose)

    //retrofit
    implementation(libs.retrofit)

    //GSON
    implementation (libs.gson)

    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
//    implementation("io.insert-koin:koin-androidx-viewmodel:4.0.0")

    implementation(libs.kotlinx.serialization.json)

}