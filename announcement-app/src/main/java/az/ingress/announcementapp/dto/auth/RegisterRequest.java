package az.ingress.announcementapp.dto.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private String name;
    private String surname;
    private String username;
    private String password;
}
