package az.ingress.announcementapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String surname;
    private String username;
    private String password;
    private Long age;
}
