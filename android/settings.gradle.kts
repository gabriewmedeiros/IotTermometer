pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://repo.eclipse.org/content/repositories/paho-snapshots/")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://repo.eclipse.org/content/repositories/paho-snapshots/")
        }
    }
}

rootProject.name = "IotTermometer"
include(":app")
