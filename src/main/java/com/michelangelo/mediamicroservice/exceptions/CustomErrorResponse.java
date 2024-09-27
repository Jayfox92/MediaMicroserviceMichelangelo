package com.michelangelo.mediamicroservice.exceptions;

import org.springframework.http.HttpStatusCode;


/* Representerar ett felmeddelande till användaren. */
public class CustomErrorResponse {
    private HttpStatusCode status;
    private String errorType;
    private String message;

    public CustomErrorResponse(HttpStatusCode status, String errorType, String message) {
        this.status = status;
        this.errorType = errorType;
        this.message = message;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public String getErrorType() {
        return errorType;
    }
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
