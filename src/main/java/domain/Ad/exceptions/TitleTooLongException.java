package domain.Ad.exceptions;

public class TitleTooLongException extends RuntimeException{
    String message = "title too long";
    public String getMessage(){
        return message;
    }
}
