package dev.sdkforge.buildlogic

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure

/**
 * Gradle convention plugin for configuring Maven publishing to GitHub Packages.
 *
 * This plugin automates the setup of Maven publishing for Kotlin Multiplatform projects,
 * specifically targeting GitHub Packages as the distribution repository. It handles
 * authentication, repository configuration, and project metadata automatically.
 *
 * ## Features
 *
 * - **Automatic Maven Publishing**: Applies the maven-publish plugin
 * - **GitHub Packages Integration**: Configures publishing to GitHub's Maven registry
 * - **Environment-Based Configuration**: Uses Gradle properties and environment variables
 * - **Secure Credentials**: Supports both Gradle properties and environment variables
 * - **Standardized Metadata**: Automatically sets group and version from properties
 *
 * ## Usage
 *
 * ```kotlin
 * plugins {
 *     id("dev.sdkforge.buildlogic.library.publishing")
 * }
 * ```
 *
 * ## Configuration
 *
 * The plugin requires the following Gradle properties in `gradle.properties`:
 *
 * ```properties
 * publishing.group=dev.sdkforge.template
 * publishing.version=0.0.1
 * publishing.owner=SDKForge
 * publishing.repository=template-sdk
 * ```
 *
 * ## Authentication
 *
 * Credentials can be provided in two ways:
 *
 * ### Option 1: Gradle Properties
 * ```properties
 * publishing.user=your-github-username
 * publishing.key=your-github-token
 * ```
 *
 * ### Option 2: Environment Variables
 * ```bash
 * export USERNAME=your-github-username
 * export TOKEN=your-github-token
 * ```
 *
 * ## Repository Structure
 *
 * Published artifacts will be available at:
 * ```
 * https://maven.pkg.github.com/{owner}/{repository}/dev/sdkforge/template/{module}/{version}/
 * ```
 *
 * ## Security Notes
 *
 * - **Never commit tokens** to version control
 * - Use environment variables in CI/CD pipelines
 * - Rotate GitHub tokens regularly
 * - Use minimal required permissions for tokens
 *
 * ## Example CI/CD Usage
 *
 * ```yaml
 * # GitHub Actions
 * - name: Publish to GitHub Packages
 *   env:
 *     USERNAME: ${{ github.actor }}
 *     TOKEN: ${{ secrets.GITHUB_TOKEN }}
 *   run: ./gradlew publish
 * ```
 *
 * @see [Maven Publish Plugin](https://docs.gradle.org/current/userguide/publishing_maven.html)
 * @see [GitHub Packages Documentation](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-maven-registry)
 * @see [GitHub Token Creation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
 */
class PublishingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("maven-publish")
                apply("com.vanniktech.maven.publish")
            }

            val group = providers.gradleProperty("publishing.group").get()
            val version = providers.gradleProperty("publishing.version").get()
            val owner = providers.gradleProperty("publishing.owner").get()
            val repository = providers.gradleProperty("publishing.repository").get()
            val description = providers.gradleProperty("publishing.description").get()

            setGroup(group)
            setVersion(version)

            configure<PublishingExtension> {
                repositories {
                    maven {
                        name = "GitHub"
                        url = uri("https://maven.pkg.github.com/$owner/$repository")
                        credentials {
                            username = providers.gradleProperty("publishing.user").orNull?.toString() ?: System.getenv("USERNAME")
                            password = providers.gradleProperty("publishing.key").orNull?.toString() ?: System.getenv("TOKEN")
                        }
                    }
                }
            }

            configure<MavenPublishBaseExtension> {
                publishToMavenCentral()
                signAllPublications()

                coordinates(
                    groupId = group,
                    artifactId = target.name.replace("shared", repository),
                    version = version,
                )

                pom {
                    name.set(repository)
                    this.description.set(description)

                    inceptionYear.set("2024")

                    url.set("https://github.com/$owner/$repository")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/$owner/$repository/blob/main/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("azazellj")
                            name.set("Volodymyr Nevmerzhytskyi")
                            url.set("https://github.com/azazellj")
                        }
                    }
                    scm {
                        url.set("https://github.com/$owner/$repository")
                        connection.set("scm:git:git://github.com/$owner/$repository.git")
                        developerConnection.set("scm:git:ssh://git@github.com/$owner/$repository.git")
                    }
                }
            }
        }
    }
}
