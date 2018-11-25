package remote;

import java.io.Serializable;

public class AutoHeader implements Serializable {
	public State expectedRecipientState;
	public Class<?> bodyType;
	public AutoHeader setState(State s) {
		this.expectedRecipientState = s;
		return this;
	}
	public AutoHeader setBodyType(Class<?> bodyType) {
		this.bodyType = bodyType;
		return this;
	}
}
