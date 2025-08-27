package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.Bundling
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.language.base.plugins.LifecycleBasePlugin

class KtLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val ktlint = configurations.create("ktlint")

            dependencies {
                ktlint("com.pinterest.ktlint:ktlint-cli:1.7.1") {
                    attributes {
                        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
                    }
                }

                // apply only to forked projects
                if (rootProject.name != "SDKForgeTemplate") {
                    dependencies {
                        ktlint(project(":internal-ktlint"))
                    }
                }
            }

            val reportFile = target.layout.buildDirectory.dir("ktlint").get().file("ktlint.html").asFile

            tasks.register<JavaExec>("ktlintCheck") {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Check Kotlin code style"
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
                args(
                    "src/**/*.kt",
                    "**.kts",
                    "!**/build/**",
                    "!**/shared-template/build.gradle.kts",
                    "--editorconfig", "${rootDir.path}/.editorconfig",
                    "--reporter", "html,output=${reportFile.absolutePath}",
                )
            }

            tasks.register<JavaExec>("ktlintFormat") {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Check Kotlin code style and format"
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                // Suppress "sun.misc.Unsafe::objectFieldOffset" on Java24 (warning) (https://github.com/pinterest/ktlint/issues/2973)
                jvmArgs("--sun-misc-unsafe-memory-access=allow") // Java 24+
                // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
                args(
                    "-F",
                    "src/**/*.kt",
                    "**.kts",
                    "!**/build/**",
                    "!**/shared-template/build.gradle.kts",
                    "--editorconfig", "${rootDir.path}/.editorconfig",
                    "--reporter", "html,output=${reportFile.absolutePath}",
                )
            }
        }
    }
}

