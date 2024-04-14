package az.ingress.announcementapp.dto.announcementdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDetailRequestDto {

    private String title;
    private String description;
    private String price;

}
