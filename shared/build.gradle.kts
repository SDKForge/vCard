plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.binaryCompatibilityValidator)
    alias(libs.plugins.dokka)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

            export(projects.sharedCore)
            export(projects.sharedData)
            export(projects.sharedDi)
            export(projects.sharedDomain)
            export(projects.sharedNetwork)
            export(projects.sharedUi)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.sharedCore)
                api(projects.sharedData)
                api(projects.sharedDi)
                api(projects.sharedDomain)
                api(projects.sharedNetwork)
                api(projects.sharedUi)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "dev.sdkforge.template"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
