package domain.Ad.exceptions;

public class AdExistsAlreadyException extends RuntimeException {
    String message = "ad exists already in the catalog";
    public String getMessage(){
        return message;
    }
}
