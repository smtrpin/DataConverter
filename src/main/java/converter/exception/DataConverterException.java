package converter.exception;

public class DataConverterException extends Exception {
  public DataConverterException(Throwable cause) {
    super(cause);
  }

  DataConverterException(String message) {
    super(message);
  }
}
