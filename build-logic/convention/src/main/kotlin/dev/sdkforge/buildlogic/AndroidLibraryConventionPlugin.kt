package dev.sdkforge.buildlogic

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            configure<LibraryExtension> {
                configureSDK()
                configureJava()
                configureLint()
            }

            configureKotlin()
        }
    }
}

private fun CommonExtension<*, *, *, *, *, *>.configureSDK() {
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }
}

private fun CommonExtension<*, *, *, *, *, *>.configureJava() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            allWarningsAsErrors.set(false)
            freeCompilerArgs.add(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        }
    }
}

private fun LibraryExtension.configureLint() {
    lint {
        // set to true to turn off analysis progress reporting by lint
        quiet = false
        // if true, stop the gradle build if errors are found
        abortOnError = true
        // set to true to have all release builds run lint on issues with severity=fatal
        // and abort the build (controlled by abortOnError above) if fatal issues are found
        checkReleaseBuilds = true
        // if true, only report errors
        ignoreWarnings = false
        // if true, emit full/absolute paths to files with errors (true by default)
        absolutePaths = true
        // if true, check all issues, including those that are off by default
        checkAllWarnings = true
        // if true, treat all warnings as errors
        warningsAsErrors = true
        // turn off checking the given issue id's
        disable += listOf()
        // turn on the given issue id's
        enable += listOf()
        // check *only* the given issue id's
        checkOnly += listOf()
        // if true, don't include source code lines in the error output
        noLines = true
        // if true, show all locations for an error, do not truncate lists, etc.
        showAll = true
        // whether lint should include full issue explanations in the text error output
        explainIssues = true
        // Fallback lint configuration (default severities, etc.)
        // lintConfig = file("default-lint.xml")
        // if true, generate a text report of issues (false by default)
        textReport = true
        // location to write the output; can be a file or 'stdout' or 'stderr'
        // textOutput 'stdout'
        // textOutput = file("$buildDir/reports/lint-results.txt")
        // if true, generate an XML report for use by for example Jenkins
        xmlReport = true
        // file to write report to (if not specified, defaults to lint-results.xml)
        // xmlOutput = file("$buildDir/reports/lint-report.xml")
        // if true, generate an HTML report (with issue explanations, sourcecode, etc)
        htmlReport = true
        // optional path to HTML report (default will be lint-results.html in the builddir)
        // htmlOutput = file("$buildDir/reports/lint-report.html")
        // if true, generate a SARIF report (OASIS Static Analysis Results Interchange Format)
        sarifReport = true
        // optional path to SARIF report (default will be lint-results.sarif in the builddir)
        // sarifOutput = file("$buildDir/reports/lint-report.html")
        // Set the severity of the given issues to fatal (which means they will be
        // checked during release builds (even if the lint target is not included)
        fatal += listOf()
        // Set the severity of the given issues to error
        error += listOf()
        // Set the severity of the given issues to warning
        warning += listOf()
        // Set the severity of the given issues to ignore (same as disabling the check)
        ignore += listOf()
        // Set the severity of the given issues to informational
        informational += listOf("UseTomlInstead", "NewerVersionAvailable")
        // Use (or create) a baseline file for issues that should not be reported
        // baseline = file("lint-baseline.xml")
        // Normally most lint checks are not run on test sources (except the checks
        // dedicated to looking for mistakes in unit or instrumentation tests, unless
        // ignoreTestSources is true). You can turn on normal lint checking in all
        // sources with the following flag, false by default:
        checkTestSources = true
        // Like checkTestSources, but always skips analyzing tests -- meaning that it
        // also ignores checks that have explicitly asked to look at test sources, such
        // as the unused resource check.
        ignoreTestSources = false
        // Normally lint will skip generated sources, but you can turn it on with this flag
        checkGeneratedSources = true
        // Whether lint should check all dependencies too as part of its analysis.
        // Default is false.
        checkDependencies = true
        // targetSdk version used when generating a lint report for a library.
        // Must be equal or higher than main target SDK. Must be set for libraries only.
        targetSdk = 36
    }
}
