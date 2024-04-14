package az.ingress.announcementapp.exception.type;

import az.ingress.announcementapp.dto.enums.response.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final ResponseMessage responseMessage;

    public static BaseException of(ResponseMessage responseMessage) {
        return BaseException.builder()
                .responseMessage(responseMessage)
                .build();
    }

    @Override
    public String getMessage() {
        return responseMessage.getMessage();
    }
}
