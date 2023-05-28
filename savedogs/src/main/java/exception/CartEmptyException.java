package exception;

//RuntimeException : 다른 예외 처리랑 다르게 예외처리를 생략할 수 있다.
public class CartEmptyException extends RuntimeException { 
	private String url;
	public CartEmptyException(String msg,String url) {
		super(msg);
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
