package io.spring.items;

public class Error extends Exception{
    private String errorMessage;
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error()
    {

    }
}
