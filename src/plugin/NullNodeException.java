package plugin;

@SuppressWarnings("serial")
public class NullNodeException extends Exception {
	public NullNodeException(String message) {
		super(message);
	}

	public NullNodeException(String message, Throwable cause) {
		super(message, cause);
	}
}
