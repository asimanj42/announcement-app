package az.ingress.announcementapp.dto.enums.response;


import org.springframework.http.HttpStatus;


public enum ErrorMessages implements ResponseMessage {

    ANNOUNCEMENT_NOT_FOUND("announcement.not.found", "Announcement not found", HttpStatus.NOT_FOUND);

    private final String key;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorMessages(String key, String message, HttpStatus httpStatus) {
        this.key = key;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
