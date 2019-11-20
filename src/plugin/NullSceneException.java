package plugin;

@SuppressWarnings("serial")
public class NullSceneException extends Exception {
	public NullSceneException(String message) {
		super(message);
	}

	public NullSceneException(String message, Throwable cause) {
		super(message, cause);
	}
}