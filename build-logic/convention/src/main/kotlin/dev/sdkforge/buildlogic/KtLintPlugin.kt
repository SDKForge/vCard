package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.Bundling
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.language.base.plugins.LifecycleBasePlugin

/**
 * Gradle plugin that configures ktlint for Kotlin code style enforcement.
 *
 * This plugin provides two main tasks:
 * - `ktlintCheck`: Analyzes Kotlin code for style violations
 * - `ktlintFormat`: Automatically formats Kotlin code according to style rules
 *
 * ## Features
 *
 * - Automatically configures ktlint CLI dependency
 * - Integrates with internal ktlint rules for forked projects
 * - Generates HTML reports for code style analysis
 * - Supports both `.kt` and `.kts` files
 * - Respects `.editorconfig` settings
 * - Compatible with Java 24+ (suppresses Unsafe warnings)
 *
 * ## Usage
 *
 * ```kotlin
 * plugins {
 *     id("dev.sdkforge.buildlogic.ktlint")
 * }
 * ```
 *
 * ## Tasks
 *
 * ### ktlintCheck
 * Checks Kotlin code style without modifying files.
 * ```bash
 * ./gradlew ktlintCheck
 * ```
 *
 * ### ktlintFormat
 * Checks and automatically formats Kotlin code.
 * ```bash
 * ./gradlew ktlintFormat
 * ```
 *
 * ## Configuration
 *
 * The plugin automatically:
 * - Creates a `ktlint` configuration with the ktlint CLI
 * - Sets up HTML reporting to `build/ktlint/ktlint.html`
 * - Configures file patterns to include all Kotlin source files
 * - Excludes build directories and template files
 *
 * ## Dependencies
 *
 * - **ktlint-cli**: 1.7.1
 * - **internal-ktlint**: Custom rules for forked projects (if applicable)
 *
 * ## Reports
 *
 * HTML reports are generated in `build/ktlint/ktlint.html` and include:
 * - Style violation details
 * - File locations
 * - Rule descriptions
 * - Fix suggestions
 *
 * @see [ktlint Documentation](https://pinterest.github.io/ktlint/)
 * @see [ktlint CLI Usage](https://pinterest.github.io/ktlint/install/cli/#command-line-usage)
 */
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

            // Configure HTML report output location
            val reportFile = target.layout.buildDirectory.dir("ktlint").get().file("ktlint.html").asFile

            // Register ktlintCheck task for style validation
            tasks.register<JavaExec>("ktlintCheck") {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Check Kotlin code style without modifying files"
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
                args(
                    "src/**/*.kt",                                  // Include all Kotlin source files
                    "**.kts",                                               // Include all Kotlin script files
                    "!**/build/**",                                         // Exclude build directories
                    "!**/shared-template/build.gradle.kts",                 // Exclude template file
                    "--editorconfig", "${rootDir.path}/.editorconfig",      // Use project editorconfig
                    "--reporter", "html,output=${reportFile.absolutePath}", // Generate HTML report
                )
            }

            // Register ktlintFormat task for automatic formatting
            tasks.register<JavaExec>("ktlintFormat") {
                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Check Kotlin code style and automatically format code"
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                // Suppress "sun.misc.Unsafe::objectFieldOffset" warning on Java 24+
                // See: https://github.com/pinterest/ktlint/issues/2973
                jvmArgs("--sun-misc-unsafe-memory-access=allow")
                // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
                args(
                    "-F",                                           // Format flag for automatic formatting
                    "src/**/*.kt",                                          // Include all Kotlin source files
                    "**.kts",                                               // Include all Kotlin script files
                    "!**/build/**",                                         // Exclude build directories
                    "!**/shared-template/build.gradle.kts",                 // Exclude template file
                    "--editorconfig", "${rootDir.path}/.editorconfig",      // Use project editorconfig
                    "--reporter", "html,output=${reportFile.absolutePath}", // Generate HTML report
                )
            }
        }
    }
}

