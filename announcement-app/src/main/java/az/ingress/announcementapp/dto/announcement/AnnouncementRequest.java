package az.ingress.announcementapp.dto.announcement;

import az.ingress.announcementapp.dto.announcementdetail.AnnouncementDetailRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementRequest {

    private AnnouncementDetailRequestDto announcementDetail;

}
