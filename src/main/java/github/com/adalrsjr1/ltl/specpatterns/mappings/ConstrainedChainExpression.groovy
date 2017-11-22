package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class ConstrainedChainExpression {
	ExpressionVariable s
	ExpressionVariable t
	ExpressionVariable z
	
	ConstrainedChainExpression(ExpressionVariable s, ExpressionVariable t) {
		this.s = s
		this.t = t
	}
	
	ConstrainedChainExpression without(ExpressionVariable z) {
		this.z = z
		return this
	}
	
	ExpressionBuilder respondsTo(ExpressionVariable p) {
		new ExpressionBuilder(ExpressionPattern.CONSTRAINED_CHAIN , [s,t,z,p])
	}
	
}
