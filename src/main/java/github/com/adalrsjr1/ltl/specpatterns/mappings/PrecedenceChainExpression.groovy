package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class PrecedenceChainExpression {
	ExpressionVariable s
	ExpressionVariable t
	
	PrecedenceChainExpression(ExpressionVariable s) {
		this.s = s
	}
	
	PrecedenceChainExpression(ExpressionVariable s, ExpressionVariable t) {
		this.s = s
		this.t = t
	}
	
	// s, t preceds p
	ExpressionBuilder precedes(ExpressionVariable p) {
		new ExpressionBuilder(ExpressionPattern.PRECEDENCE_CHAIN_ONE, [s,t,p])
	}
	
	// s preceds p, t
	ExpressionBuilder precedes(ExpressionVariable p, ExpressionVariable t) {
		new ExpressionBuilder(ExpressionPattern.PRECEDENCE_CHAIN_TWO, [p,s,t])
	}
}
