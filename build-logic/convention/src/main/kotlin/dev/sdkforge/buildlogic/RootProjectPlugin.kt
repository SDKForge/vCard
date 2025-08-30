package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle convention plugin for root project configuration and setup.
 *
 * This plugin handles root-level project configuration, including the setup of
 * pre-commit git hooks and other project-wide initialization tasks. It's designed
 * to be applied only to the root project of a multi-module Gradle build.
 *
 * ## Features
 *
 * - **Pre-commit Hook Setup**: Automatically configures git pre-commit hooks
 *
 * ## Usage
 *
 * This plugin is automatically applied to the root project via the root build script:
 *
 * ```kotlin
 * // In root build.gradle.kts
 * apply(plugin = libs.plugins.build.logic.root.get().pluginId)
 * ```
 *
 * ## Tasks
 *
 * ### addPreCommitGitHookOnBuild
 * Registers a task that sets up pre-commit git hooks during the build process.
 * This task runs the `./scripts/inject-pre-commit.sh` script to configure
 * the git hooks directory.
 *
 *
 * @see [Git Hooks Documentation](https://git-scm.com/docs/githooks)
 * @see [Pre-commit Framework](https://pre-commit.com/)
 * @see [Gradle Plugin Development](https://docs.gradle.org/current/userguide/custom_plugins.html)
 */
class RootProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // This script handles making the pre-commit hook executable and copying it to .git/hooks/
            val setPreCommitExecutable = rootProject.providers.exec {
                commandLine("./scripts/inject-pre-commit.sh")
            }

            // Register a task that sets up pre-commit git hooks during the build process
            tasks.register("addPreCommitGitHookOnBuild") {
                println("Injecting pre commit git hook...")
                // Execute the hook injection script and capture its output
                setPreCommitExecutable.standardOutput.asText.get()
                println("Pre commit hook injected.")
            }
        }
    }
}
