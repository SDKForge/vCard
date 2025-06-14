package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class KMPLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
            }

            configure<KotlinMultiplatformExtension> {
                androidTarget {
                    tasks.withType(KotlinJvmCompile::class).configureEach {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_17)
                        }
                    }
                }

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64(),
                ).forEach {
                    it.binaries.framework {
                        baseName = target.name
                        isStatic = true
                    }
                }
            }
        }
    }
}


