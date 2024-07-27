plugins{
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

group = "com.zzhoujay.richtext"
version = "3.0.1"

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "com.zzhoujay.richtext"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    lint {
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
//    implementation(libs.html)
    implementation(projects.html.htmlSpanner)
    implementation(libs.markdown)
    implementation(libs.disklrucache)
    implementation(libs.androidx.annotation)
}