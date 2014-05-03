package in.thousandspas.api.response;

public interface GenericResponse {
  public boolean isSuccess();
  public void setSuccess(boolean success);
  public String getMessage();
  public void setMessage(String message);
}
