package github.com.adalrsjr1.ltl.core

import github.com.adalrsjr1.ltl.core.checker.TransitionChecker
import github.com.adalrsjr1.ltl.events.LabelTransitionSystem
import jhoafparser.ast.BooleanExpression
import jhoafparser.storage.StoredAutomaton
import jhoafparser.storage.StoredState

interface LabeledTransitionSystem {
	StoredState getInitialState()
	void init(StoredAutomaton storedAutomaton, TransitionChecker cheker)
	
	/**
	 * Return a Violation event if reach an non-acceptance state
	 * implementation based on spot monitors: https://spot.lrde.epita.fr/tut11.html
	 * @param state Automata State
	 * @param symptom Symptom to be evaluated
	 * @return
	 */
	LabeledTransitionSystemEvent next(LabeledTransitionSystemState state, LabelTransitionSystem symptom)
	
	Iterable getTransitions(StoredState state)
	
	Iterable getStates()
	
	StoredState getState(int label)
	
	String getTransitionLabel(BooleanExpression booleanExpression)
}
