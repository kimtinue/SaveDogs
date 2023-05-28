package exception;

public class VworkException extends RuntimeException {
	private String url;
	public VworkException(String msg, String url) {
		super(msg);
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}