package github.com.adalrsjr1.ltl.events;
import java.util.Objects;

import com.google.common.base.MoreObjects;

public class LabelTransitionSystemEventImpl implements LabelTransitionSystem {
	public static final LabelTransitionSystemEventImpl EMPTY_EVENT = new LabelTransitionSystemEventImpl();
	
	private String id = "";
	private String name = "";
	private String source = "";
	private String message = "";
	
	private LabelTransitionSystemEventImpl() { }

	private LabelTransitionSystemEventImpl(String id, String name, String source, String message) {
		this.id = id;
		this.name = name;
		this.source = source;
		this.message = message;
	}
	
	private LabelTransitionSystemEventImpl(LabelTransitionSystemEventImpl event) {
		this(event.getId(), event.getName(), event.getSource(), event.getMessage());
	}
	
	public static LabelTransitionSystemEventImpl newEvent(String containerId, String containerName, String source, String message) {
		return new LabelTransitionSystemEventImpl(containerId, containerName, source, message);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
							 .add("containerId", id)
							 .add("containerName", name)
							 .add("source", source)
						  .toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(! (o instanceof LabelTransitionSystemEventImpl)) return false;
		
		LabelTransitionSystem event = (LabelTransitionSystem) o;
		return Objects.equals(id, event.getId()) && 
			   Objects.equals(name, event.getName()) && 
			   Objects.equals(message, event.getMessage()) && 
			   Objects.equals(source, event.getSource());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, source, message);
	}
	
	@Override
	public LabelTransitionSystem getEmpty() {
		return EMPTY_EVENT;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
