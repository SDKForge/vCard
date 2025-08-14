package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

class RootProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val setPreCommitExecutable = rootProject.providers.exec {
                commandLine("./scripts/inject-pre-commit.sh")
            }

            tasks.register("addPreCommitGitHookOnBuild") {
                println("Injecting pre commit git hook...")
                setPreCommitExecutable.standardOutput.asText.get()
                println("Pre commit hook injected.")
            }
        }
    }
}
