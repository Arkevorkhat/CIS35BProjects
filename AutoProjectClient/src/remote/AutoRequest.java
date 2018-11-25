package remote;

import java.io.NotSerializableException;
import java.io.Serializable;

public class AutoRequest<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public AutoHeader head;
	public Object body;
	public AutoRequest(AutoHeader head, T body) throws NotSerializableException {
		this.head = head;
		if (body instanceof Serializable) {
			this.body = body;
		} else {
			throw new NotSerializableException();
		}
	}
}
