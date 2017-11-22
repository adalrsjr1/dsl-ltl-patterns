package github.com.adalrjsr1.ltl.specpatterns;

import github.com.adalrsjr1.ltl.specpatterns.ExpressionVariable
import github.com.adalrsjr1.ltl.specpatterns.Property
import github.com.adalrsjr1.ltl.specpatterns.PropertyParser
import github.com.adalrsjr1.ltl.specpatterns.PropertyPattern
import groovy.util.GroovyTestCase

class TestDsl extends GroovyTestCase {

	static final ExpressionVariable p = new ExpressionVariable("P")
	static final ExpressionVariable q = new ExpressionVariable("Q")
	static final ExpressionVariable r = new ExpressionVariable("R")
	static final ExpressionVariable s = new ExpressionVariable("S")
	static final ExpressionVariable t = new ExpressionVariable("T")
	static final ExpressionVariable z = new ExpressionVariable("Z")

	static final PropertyPattern pp = Property.create()
	
	void testDslString() {
		assert PropertyParser.evaluate("""
			property {
				absence "p"
				isFalse
				globally
			}
			""", new PropertyParser()).toString() == "[](!p)"
	}
	
	void testGroovyDsl() {
		assert new PropertyParser().create {
			absence "p"
			isFalse
			globally
		}.toString() == "[](!p)"
	}
	
	void testGrovyDsl2() {
		assert new PropertyParser().create {
			boundedExistence "P"
			occurs 2
			justAfter "Q"
		}.toString() == "<>Q -> (!Q U (Q & (!P W (P W (!P W (P W []!P))))))"
	}
	
	void testFileDsl1() {
		File f = new File("src/test/resources/property1.property")
		
		assert PropertyParser.evaluate(f, new PropertyParser()).toString() == "[](!p)"
	}
	
	void testFileDsl2() {
		File f = new File("src/test/resources/property2.property")
		
		assert PropertyParser.evaluate(f, new PropertyParser()).toString() == "[](!q)"
	}
	
	void testFileDsl3() {
		File f = new File("src/test/resources/property2.property")
		
		assert PropertyParser.evaluate(f, new PropertyParser()).toString() != "[](!p)"
	}
	
	void testFileDsl4() {
		File f = new File("src/test/resources/property3.property")
		
		assert PropertyParser.evaluate(f, new PropertyParser()).toString() != "[]((Q & <>R) -> ((!P & !R) U (R | ((P & !R) U (R | ((!P & !R) U (R | ((P & !R) U (R | (!P U R))))))))))"
	}
}
