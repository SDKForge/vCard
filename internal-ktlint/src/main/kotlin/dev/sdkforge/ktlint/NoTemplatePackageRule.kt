@file:Suppress("ktlint:standard:wrapping", "ktlint:standard:class-signature")

package dev.sdkforge.ktlint

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.ElementType.BINARY_EXPRESSION
import com.pinterest.ktlint.rule.engine.core.api.ElementType.REFERENCE_EXPRESSION
import com.pinterest.ktlint.rule.engine.core.api.ElementType.STRING_TEMPLATE
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class NoTemplatePackageRule : Rule(
    ruleId = RuleId("$SDKFORGE_RULE_SET_ID:no-template-package"),
    about = About(
        maintainer = "azazellj",
        repositoryUrl = "https://github.com/SDKForge/template-sdk",
        issueTrackerUrl = "https://github.com/SDKForge/template-sdk/issues",
    ),
),
    RuleAutocorrectApproveHandler {

    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        if (node.elementType != BINARY_EXPRESSION) {
            return
        }

        val left = node.findChildByType(REFERENCE_EXPRESSION) ?: return
        val right = node.findChildByType(STRING_TEMPLATE) ?: return

        if (left.text != "namespace") return

        if (right.text.contains(".template.")) {
            emit(node.startOffset, "Using '${right.text.trim('"')}' which is a template package.", false)
        }
    }
}
