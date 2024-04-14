package az.ingress.announcementapp.dto.announcement;

import az.ingress.announcementapp.dto.announcementdetail.AnnouncementDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementResponse {

    private Long id;
    private Long viewCount;
    private Long userId;
    private AnnouncementDetailResponse announcementDetail;
}
