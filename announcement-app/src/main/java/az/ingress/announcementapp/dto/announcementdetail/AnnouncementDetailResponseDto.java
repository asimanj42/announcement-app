package az.ingress.announcementapp.dto.announcementdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDetailResponseDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private String price;
    private String createDate;
    private String updateDate;

}
