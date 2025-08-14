package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class NoTemplateImportRuleTest {
    private val wrappingRuleAssertThat = assertThatRule { NoTemplateImportRule() }

    private val code =
        """
        import a.b.c
        import dev.sdkforge.template.app
        """.trimIndent()

    @Test
    fun `no 'template' import rule`() {
        wrappingRuleAssertThat(code).hasLintViolationWithoutAutoCorrect(
            line = 2,
            col = 1,
            detail = "Importing 'dev.sdkforge.template.app' which is an template import.",
        )
    }
}
