package pl.dominisz.account;

public class FaultException extends RuntimeException {

  public FaultException(String message, Throwable cause) {
    super(message, cause);
  }
}
