package domain.Ad.exceptions;

public class TitleAndDescriptionAreTheSameException extends RuntimeException{
    String message = "title and description are the same";

    public String getMessage(){
        return message;
    }
}
