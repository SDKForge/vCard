import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPlugin
import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPluginExtension
import kotlinx.kover.gradle.plugin.KoverGradlePlugin
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.binaryCompatibilityValidator).apply(false)
    alias(libs.plugins.dokka).apply(false)
    alias(libs.plugins.dependency.guard).apply(false)
    alias(libs.plugins.kover)
    alias(libs.plugins.ktlint)
}

subprojects {
    apply<KtlintPlugin>()

    ktlint {
        reporters {
            reporter(ReporterType.HTML)
        }
    }

    apply<DependencyGuardPlugin>()

    configure<DependencyGuardPluginExtension> {
        configuration("releaseRuntimeClasspath")
    }

    dependencies {
        apply<KoverGradlePlugin>()
        println("Dynamically used code coverage for ${project.path}")
        kover(project(project.path))
    }

    kover {
        reports {
            filters {
                excludes {
                    // TODO: add rules
                }
            }
        }
    }

    apply<DokkaPlugin>()

    configure<DokkaExtension> {

    }
}
