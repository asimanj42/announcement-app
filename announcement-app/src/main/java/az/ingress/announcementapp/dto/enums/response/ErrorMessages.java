package az.ingress.announcementapp.dto.enums.response;


import org.springframework.http.HttpStatus;


public enum ErrorMessages implements ResponseMessage {

    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "User already exists", HttpStatus.BAD_REQUEST),
    FROM_ACCOUNT_NOT_FOUND("FROM_ACCOUNT_NOT_FOUND", "From account not found", HttpStatus.NOT_FOUND),
    TO_ACCOUNT_NOT_FOUND("TO_ACCOUNT_NOT_FOUND", "To account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND", "Account not found", HttpStatus.NOT_FOUND),
    TRANSACTION_NOT_FOUND("TRANSACTION_NOT_FOUND", "Transaction not found", HttpStatus.NOT_FOUND),
    INSUFFICIENT_BALANCE("INSUFFICIENT_BALANCE", "Insufficient balance", HttpStatus.BAD_REQUEST),
    INVALID_AMOUNT("INVALID_AMOUNT", "Invalid amount", HttpStatus.BAD_REQUEST),
    INVALID_ACCOUNT_NUMBER("INVALID_ACCOUNT_NUMBER", "Invalid account number", HttpStatus.BAD_REQUEST),
    INVALID_USER_ID("INVALID_USER_ID", "Invalid user id", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND("ADDRESS_NOT_FOUND", "Address not found", HttpStatus.NOT_FOUND),
    CARD_NOT_FOUND("CARD_NOT_FOUND", "Card not found", HttpStatus.NOT_FOUND),
    CARD_BENEFIT_NOT_FOUND("CARD_BENEFIT_NOT_FOUND", "Card benefit not found", HttpStatus.NOT_FOUND),
    CARD_ALREADY_EXISTS("CARD_ALREADY_EXISTS", "Card already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND);

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
