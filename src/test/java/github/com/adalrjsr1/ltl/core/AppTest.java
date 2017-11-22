package github.com.adalrjsr1.ltl.core;

import github.com.adalrsjr1.ltl.core.LabeledTransitionSystem;
import github.com.adalrsjr1.ltl.core.LabeledTransitionSystemEvent;
import github.com.adalrsjr1.ltl.core.LabeledTransitionSystemFactory;
import github.com.adalrsjr1.ltl.core.LabeledTransitionSystemState;
import github.com.adalrsjr1.ltl.core.NonViolationEvent;
import github.com.adalrsjr1.ltl.core.ViolationEvent;
import github.com.adalrsjr1.ltl.core.checker.TransitionChecker;
import github.com.adalrsjr1.ltl.events.LabelTransitionSystem;
import github.com.adalrsjr1.ltl.events.LabelTransitionSystemEventImpl;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	static class PropertyChecker implements TransitionChecker {

		@Override
		public boolean check(String transitionFormula, LabelTransitionSystem symptom) {
			return transitionFormula.equals(symptom.getName());
		}
		
	}
	
	// it checks that yellow never occurs immediately after red.
	static final String PROPERTY_1 = "!F(red & X(yellow))";
	
	// it checks that 'a' is the second action and an eventual 'b' or a global 'c' always happen or
	// 'c' always happen
	static final String PROPERTY_2 = "(Xa & Fb) | Gc";
	
	static final String PROPERTY_3 = "Fa->Gb";

	static final NonViolationEvent nv = NonViolationEvent.getInstance();
	static final ViolationEvent v = ViolationEvent.getInstance();
	
	public void testGetInitialStateProperty3() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_3, new PropertyChecker());
		assertEquals(lts.getInitialState().getStateId(), 2);
	}
	
	public void testViolationEvent() {
		assertTrue(ViolationEvent.getInstance() instanceof ViolationEvent);
	}
	
	public void testNonViolationEvent() {
		assertTrue(NonViolationEvent.getInstance() instanceof NonViolationEvent);
	}
	
	public void testProperty1ViolationRedYellow() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_1, new PropertyChecker());
		LabeledTransitionSystemState state = new LabeledTransitionSystemState("");
		state.setInitialState(lts.getInitialState());
		
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "red", "", "")), nv);
		assertEquals(state.isInAcceptanceState(), true);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", "")), v);
		assertEquals(state.isInAcceptanceState(), false);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", "")), v);
		assertEquals(state.isInAcceptanceState(), false);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "green", "", "")), v);
		assertEquals(state.isInAcceptanceState(), false);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "red", "", "")), v);
		assertEquals(state.isInAcceptanceState(), false);
	}
	
	public void testProperty1NonViolationStartingWithYellow() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_1, new PropertyChecker());
		LabeledTransitionSystemState state = new LabeledTransitionSystemState("");
		state.setInitialState(lts.getInitialState());
		
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", "")), nv);
		assertEquals(state.isInAcceptanceState(), true);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", "")), nv);
		assertEquals(state.isInAcceptanceState(), true);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "green", "", "")), nv);
		assertEquals(state.isInAcceptanceState(), true);
		assertEquals(lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "red", "", "")), nv);
		assertEquals(state.isInAcceptanceState(), true);
	}
	
	public void testProperty1Violation() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_1, new PropertyChecker());
		LabeledTransitionSystemState state = new LabeledTransitionSystemState("");
		state.setInitialState(lts.getInitialState());
		
		LabeledTransitionSystemEvent event;
		
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "green", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "red", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		
		assertSame(event, v);
	}
	
	public void testProperty1NonViolation() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_1, new PropertyChecker());
		LabeledTransitionSystemState state = new LabeledTransitionSystemState("");
		state.setInitialState(lts.getInitialState());
		
		LabeledTransitionSystemEvent event;
		
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "green", "", ""));
		event = lts.next(state, LabelTransitionSystemEventImpl.newEvent("", "red", "", ""));
		
		assertSame(event, nv);
	}
	
	public void testProperty1MultipleStates() {
		LabeledTransitionSystem lts = LabeledTransitionSystemFactory.create(PROPERTY_1, new PropertyChecker());
		
		LabeledTransitionSystemState state1 = new LabeledTransitionSystemState("1");
		state1.setInitialState(lts.getInitialState());
		
		LabeledTransitionSystemState state2 = new LabeledTransitionSystemState("2");
		state2.setInitialState(lts.getInitialState());
		
		LabeledTransitionSystemState state3 = new LabeledTransitionSystemState("3");
		state3.setInitialState(lts.getInitialState());
		
		LabeledTransitionSystemEvent event1;
		LabeledTransitionSystemEvent event2;
		LabeledTransitionSystemEvent event3;
		
		event1 = lts.next(state1, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event2 = lts.next(state2, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event3 = lts.next(state3, LabelTransitionSystemEventImpl.newEvent("", "green", "", ""));
		event1 = lts.next(state1, LabelTransitionSystemEventImpl.newEvent("", "red", "", ""));
		event2 = lts.next(state2, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event3 = lts.next(state3, LabelTransitionSystemEventImpl.newEvent("", "red", "", ""));
		event1 = lts.next(state1, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event2 = lts.next(state2, LabelTransitionSystemEventImpl.newEvent("", "yellow", "", ""));
		event3 = lts.next(state3, LabelTransitionSystemEventImpl.newEvent("", "green", "", ""));

		assertSame(event1, v);
		assertSame(event2, nv);
		assertSame(event3, nv);
	}
}
