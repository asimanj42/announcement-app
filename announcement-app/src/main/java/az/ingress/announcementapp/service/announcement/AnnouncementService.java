package az.ingress.announcementapp.service.announcement;

import az.ingress.announcementapp.dto.announcement.AnnouncementRequest;
import az.ingress.announcementapp.dto.announcement.AnnouncementResponse;
import az.ingress.announcementapp.dto.pagination.PageResponse;
import az.ingress.announcementapp.specification.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnnouncementService {

    AnnouncementResponse createAnnouncement(AnnouncementRequest announcementRequest);
    PageResponse<AnnouncementResponse> findAllWithSpecification(List<SearchCriteria> searchCriteriaList, Pageable pageable);
}
