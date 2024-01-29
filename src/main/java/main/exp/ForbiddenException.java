package main.exp;

public class ForbiddenException extends RuntimeException{
    // 403 qaytaradi !
    public ForbiddenException(String message) {
        super(message);
    }
}
