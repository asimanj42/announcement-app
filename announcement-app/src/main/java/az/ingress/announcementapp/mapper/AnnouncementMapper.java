package az.ingress.announcementapp.mapper;

import az.ingress.announcementapp.dto.announcement.AnnouncementRequest;
import az.ingress.announcementapp.dto.announcement.AnnouncementResponse;
import az.ingress.announcementapp.dto.announcementdetail.AnnouncementDetailResponseDto;
import az.ingress.announcementapp.entity.Announcement;
import az.ingress.announcementapp.entity.AnnouncementDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnnouncementMapper {

    public Announcement mapAnnouncementRequestToEntity(AnnouncementRequest announcementRequest) {
        return Announcement.builder()
                .announcementDetail(AnnouncementDetail.builder()
                        .title(announcementRequest.getAnnouncementDetail().getTitle())
                        .description(announcementRequest.getAnnouncementDetail().getDescription())
                        .price(announcementRequest.getAnnouncementDetail().getPrice())
                        .build())
                .build();
    }

    public AnnouncementResponse mapAnnouncementEntityToResponse(Announcement announcement) {
        return AnnouncementResponse.builder()
                .id(announcement.getId())
                .viewCount(announcement.getViewCount())
                .userId(announcement.getUser().getId())
                .announcementDetail(AnnouncementDetailResponseDto.builder()
                        .id(announcement.getAnnouncementDetail().getId())
                        .title(announcement.getAnnouncementDetail().getTitle())
                        .description(announcement.getAnnouncementDetail().getDescription())
                        .price(announcement.getAnnouncementDetail().getPrice())
                        .createDate(announcement.getAnnouncementDetail().getCreateDate().toString())
                        .updateDate(announcement.getAnnouncementDetail().getUpdateDate().toString())
                        .build())
                .build();
    }

    public List<AnnouncementResponse> mapListAnnouncementEntityToListResponse(List<Announcement> announcements) {
        return announcements.stream()
                .map(this::mapAnnouncementEntityToResponse)
                .collect(Collectors.toList());
    }
}
