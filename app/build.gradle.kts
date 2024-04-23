
plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.sweet_wave"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sweet_wave"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }

}

dependencies {

//    implementation ("com.android.support:multidex:1.0.3")

    implementation ("com.google.android.play:integrity:1.3.0")


    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-firestore")
    implementation ("com.firebaseui:firebase-ui-auth:3.0.0")
    implementation ("com.firebaseui:firebase-ui-firestore:3.0.0")
    implementation ("com.firebaseui:firebase-ui-storage:6.2.1")


//glid
    implementation ("com.github.bumptech.glide:glide:4.12.0")
//    implementation("androidx.annotation:annotation-jvm:1.7.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

// https://mvnrepository.com/artifact/com.etebarian/meow-bottom-navigation
    implementation("com.etebarian:meow-bottom-navigation:1.2.0")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")      // gif
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
