@file:Suppress("ktlint:standard:wrapping", "ktlint:standard:class-signature")

package dev.sdkforge.ktlint

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class NoTemplateImportRule : Rule(
    ruleId = RuleId("$SDKFORGE_RULE_SET_ID:no-template-import"),
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
        if (node.elementType == KtStubElementTypes.IMPORT_DIRECTIVE) {
            val importDirective = node.psi as KtImportDirective
            val path = importDirective.importPath?.pathStr
            if (path != null && path.contains("template")) {
                emit(node.startOffset, "Importing '$path' which is an template import.", false)
            }
        }
    }
}
