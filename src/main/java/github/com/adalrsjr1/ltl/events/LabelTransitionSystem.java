package github.com.adalrsjr1.ltl.events;

public interface LabelTransitionSystem {

	String getId();

	String getName();

	String getSource();

	String getMessage();
	
	LabelTransitionSystem getEmpty();
	
}