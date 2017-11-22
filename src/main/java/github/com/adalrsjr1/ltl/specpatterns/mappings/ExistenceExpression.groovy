package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class ExistenceExpression {
	ExpressionVariable p
	
	ExistenceExpression(ExpressionVariable p) {
		this.p = p
	}
	
	ExpressionBuilder becomesTrue() {
		new ExpressionBuilder(ExpressionPattern.EXISTENCE, p)
	}
}
