package remote;

import java.io.NotSerializableException;
import java.io.Serializable;

public class AutoRequest implements Serializable{
	public AutoHeader head;
	public Object body;
	public AutoRequest(AutoHeader head, Object body) throws NotSerializableException{
		this.head = head;
		if(bod)
		this.body = body;
	}
}
