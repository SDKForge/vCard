package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure

class PublishingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("maven-publish")
            }

            group = providers.gradleProperty("publishing.group").get()
            version = providers.gradleProperty("publishing.version").get()

            configure<PublishingExtension> {
                repositories {
                    maven {
                        val owner = providers.gradleProperty("publishing.owner").get()
                        val repository = providers.gradleProperty("publishing.repository").get()

                        name = "GitHub"
                        url = uri("https://maven.pkg.github.com/$owner/$repository")
                        credentials {
                            username = providers.gradleProperty("publishing.user").orNull?.toString() ?: System.getenv("USERNAME")
                            password = providers.gradleProperty("publishing.key").orNull?.toString() ?: System.getenv("TOKEN")
                        }
                    }
                }
            }
        }
    }
}
