package dev.sdkforge.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DefaultBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun measure() {
        benchmarkRule.measureRepeated {
            // TODO: add measurables here
        }
    }
}
