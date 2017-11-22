package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class PrecedenceExpression {
	ExpressionVariable s
	
	PrecedenceExpression(ExpressionVariable s) {
		this.s = s
	}
	
	ExpressionBuilder precedes(ExpressionVariable p) {
		new ExpressionBuilder(ExpressionPattern.PRECEDENCE, [s,p])
	}
}
