package az.ingress.announcementapp.dto.enums.response;


import org.springframework.http.HttpStatus;


public enum ErrorMessages implements ResponseMessage {

    ANNOUNCEMENT_NOT_FOUND("announcement.not.found", "Announcement not found", HttpStatus.NOT_FOUND),
    ANNOUNCEMENT_ALREADY_EXISTS("announcement.already.exists", "Announcement already exists", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND("account.not.found", "Account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_ALREADY_EXISTS("account.already.exists", "Account already exists", HttpStatus.BAD_REQUEST),
    VERIFICATION_FAILED("verification.failed", "Verification failed", HttpStatus.BAD_REQUEST);

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
