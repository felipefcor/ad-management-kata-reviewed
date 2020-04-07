package domain.Ad.exceptions;

public class AdDoesNotExistException extends RuntimeException {
    String message = "ad does not exists";
    public String getMessage(){
        return message;
    }
}
