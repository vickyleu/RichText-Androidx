@file:Suppress("UnstableApiUsage")

include(":composeapp")


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
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
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
        maven { setUrl("https://repo.spring.io/plugins-release/")
            content {
                includeGroupByRegex("com.zzhoujay.*")
            }
        }
        maven {
            setUrl("https://jitpack.io")
            content {
                includeGroupByRegex("com.github.*")
            }
        }
        maven {
            url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
            isAllowInsecureProtocol = true
        }
        maven {
            val properties = java.util.Properties().apply {
                runCatching { rootProject.projectDir.resolve("local.properties") }
                    .getOrNull()
                    .takeIf { it?.exists() ?: false }
                    ?.reader()
                    ?.use(::load)
            }
            val environment: Map<String, String?> = System.getenv()
            extra["githubToken"] = properties["github.token"] as? String
                ?: environment["GITHUB_TOKEN"] ?: ""

            url = uri("https://maven.pkg.github.com/vickyleu/Html")
            credentials {
                username = "vickyleu"
                password = extra["githubToken"]?.toString()
            }
            content {
                excludeGroupByRegex("com.finogeeks.*")
                excludeGroupByRegex("org.jogamp.*")
                excludeGroupByRegex("org.jetbrains.compose.*")
                excludeGroupByRegex("(?!com|cn).github.(?!vickyleu).*")
            }
        }
    }
}
include(":app")
include(":richtext")
include(":okhttpimagedownloader")

include(":Html").apply {
    project(":Html").projectDir = file("../Html")
}
include("Html:htmlSpanner")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "RichText-Androidx"
