package exception;

public class AdoptException extends RuntimeException {
	private String url;
	public AdoptException(String msg, String url) {
		super(msg);
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
