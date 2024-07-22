@file:Suppress("UnstableApiUsage")

pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.apply {
            mavenCentral()
            gradlePluginPortal()
            google {
                content {
                    includeGroupByRegex(".*google.*")
                    includeGroupByRegex(".*android.*")
                }
            }
        }
    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        mavenCentral()
        google {
            content {
                includeGroupByRegex(".*google.*")
                includeGroupByRegex(".*android.*")
            }
        }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven {
            setUrl("https://jitpack.io")
            content {
                includeGroupByRegex("com.github.*")
            }
        }
    }
}
include(":app")
include(":richtext")
include(":okhttpimagedownloader")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "RichText-Androidx"
