package Exceptions;

public class ErrorDialogException extends Exception {

    private final String titleMessage, errorMessage;

    public ErrorDialogException(String title, String message) {

        //Save information
        titleMessage = title;
        errorMessage = message;
    }
    
    public String getTitle() {
        return titleMessage;
    }
    
    public String getMessage() {
        return errorMessage;
    }
}