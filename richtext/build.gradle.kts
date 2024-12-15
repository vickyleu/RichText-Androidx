@file:Suppress("UnstableApiUsage")


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

group = "com.zzhoujay.richtext"
version = "3.0.1"
kotlin{
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}
android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "com.zzhoujay.richtext"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        externalNativeBuild {
            cmake {

                arguments(
                    "-Dcdep-dependencies_DIR=${file("./cdep/.cdep/modules").relativeTo(projectDir.resolve("src/main/cpp")).path}",
                    "-DANDROID_STL=c++_shared"
                )
                // 指定ABI
                abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
            }
        }
    }
    lint {
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }


    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
 // It only needs to work when compiling for the first time, and it does not need to be used the rest of the time.
val runcdep by tasks.registering(Exec::class) {
    commandLine("./cdep/cdep")
}

tasks.getByName("preBuild").dependsOn(runcdep)

dependencies {
    //compile fileTree(include: ['*.jar'], dir: 'libs')
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
//    implementation(libs.html)
    implementation(projects.html.htmlSpanner)
    implementation(libs.markdown)
    implementation(libs.disklrucache)
    implementation(libs.androidx.annotation)
}