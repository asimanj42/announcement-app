package az.ingress.announcementapp.dto.announcement;

import az.ingress.announcementapp.dto.announcementdetail.AnnouncementDetailResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementResponse implements Serializable {

    private Long id;
    private Long viewCount;
    private Long userId;
    private AnnouncementDetailResponseDto announcementDetail;
}
