plugins{
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

group = "com.zzhoujay.okhttpimagedownloader"
version = "1.0.2"

kotlin{
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}
android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "com.zzhoujay.okhttpimagedownloader"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
    implementation(libs.okhttp)
    implementation(projects.richtext)
}