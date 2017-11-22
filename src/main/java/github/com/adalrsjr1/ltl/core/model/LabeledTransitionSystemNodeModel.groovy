package github.com.adalrsjr1.ltl.core.model

import groovy.json.*
import groovy.transform.ToString
import jhoafparser.storage.StoredState

@ToString
class LabeledTransitionSystemNodeModel {
	
	final String caption
	final String type
	final int id
	
	public LabeledTransitionSystemNodeModel(StoredState state) {
		type = state.getAccSignature()
		id = state.getStateId()
		caption = state.getLabelExpr().toString()
	}
	
	public String toJson() {
		JsonOutput.toJson(this)
	}
}
