package remote;

public class ACK {
	public RequestType requestedOperation;
	public State senderState;
	private boolean accepted;
	public boolean isAccepted() {
		return this.accepted;
	}
	public ACK(boolean accepted, State myState, RequestType requestedOperation) {
		this.accepted = accepted;
		this.senderState = myState;
		this.requestedOperation = requestedOperation;
	}
}
