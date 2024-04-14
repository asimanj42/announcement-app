package az.ingress.announcementapp.dto.enums.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {

    String getKey();
    String getMessage();
    HttpStatus getHttpStatus();
}
