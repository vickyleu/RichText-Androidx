plugins{
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}


android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "zhou.demo"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        versionCode =  1
        versionName = "1.0"
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(projects.richtext)
    testImplementation(libs.junit)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.html)
//    implementation(projects.html.htmlSpanner)
}