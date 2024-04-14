package az.ingress.announcementapp.dto.announcement;

import az.ingress.announcementapp.dto.announcementdetail.AnnouncementDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementRequest {

    private Long viewCount;
    private AnnouncementDetailRequest announcementDetail;

}
