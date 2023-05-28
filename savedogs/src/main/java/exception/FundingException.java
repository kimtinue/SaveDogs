package exception;

public class FundingException extends RuntimeException {
	private String url;
	public FundingException(String msg, String url) {
		super(msg);
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}