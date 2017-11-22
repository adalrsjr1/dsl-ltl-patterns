package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class ResponseChainExpression {
	ExpressionVariable s
	ExpressionVariable t
	ExpressionVariable p
	
	ResponseChainExpression(ExpressionVariable p) {
		this.p = p
	}
	
	ResponseChainExpression(ExpressionVariable s, ExpressionVariable t) {
		this.s = s
		this.t = t
	}
	
	// s, t preceds p
	ExpressionBuilder respondsTo(ExpressionVariable p) {
		new ExpressionBuilder(ExpressionPattern.RESPONSE_CHAIN_ONE, [s,t,p])
	}
	
	// s preceds p, t
	ExpressionBuilder respondsTo(ExpressionVariable s, ExpressionVariable t) {
		new ExpressionBuilder(ExpressionPattern.RESPONSE_CHAIN_TWO, [p,s,t])
	}
}
