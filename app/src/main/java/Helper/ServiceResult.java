package Helper;

/**
 * Created by marchest on 26.11.2016.
 */
public class ServiceResult<T> {

    private int StatusCode;
    private String ErrorMessage;
    private T Result;

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
