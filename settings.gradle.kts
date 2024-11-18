pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // Thêm kho plugin nếu chưa có
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FlightBookingSystem"
include(":app")
 