package github.com.adalrsjr1.ltl.specpatterns.mappings

import github.com.adalrsjr1.ltl.specpatterns.ExpressionBuilder
import github.com.adalrsjr1.ltl.specpatterns.ExpressionPattern
import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import groovy.util.logging.Slf4j

@Slf4j
class BoundedExpression {
	ExpressionVariable p
	
	BoundedExpression(ExpressionVariable p) {
		this.p = p
	}
	
	ExpressionBuilder occurs(int nTimes) {
		if(nTimes == 0) {
			throw new RuntimeException("Number of occurrence must be greater then 0")
		}
		List vars = []
		for(i in 1..nTimes) {
			vars << p
		}
		new ExpressionBuilder(ExpressionPattern.BOUNDED_EXISTENCE, vars)
	}
}
