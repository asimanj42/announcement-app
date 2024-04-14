package az.ingress.announcementapp.service.announcement;

import az.ingress.announcementapp.dto.announcement.AnnouncementRequest;
import az.ingress.announcementapp.dto.announcement.AnnouncementResponse;
import az.ingress.announcementapp.dto.pagination.PageResponse;
import az.ingress.announcementapp.entity.Announcement;
import az.ingress.announcementapp.entity.AnnouncementDetail;
import az.ingress.announcementapp.entity.User;
import az.ingress.announcementapp.mapper.AnnouncementMapper;
import az.ingress.announcementapp.mapper.PageResponseMapper;
import az.ingress.announcementapp.repository.AnnouncementDetailRepository;
import az.ingress.announcementapp.repository.AnnouncementRepository;
import az.ingress.announcementapp.specification.SearchCriteria;
import az.ingress.announcementapp.specification.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementDetailRepository announcementDetailRepository;
    private final AnnouncementMapper announcementMapper;
    private final PageResponseMapper pageResponseMapper;

    @Override
    public AnnouncementResponse createAnnouncement(AnnouncementRequest announcementRequest) {

        Announcement announcement = announcementMapper.mapAnnouncementRequestToEntity(announcementRequest);
        announcement.setUser(getLoggedInUser());
        announcementDetailRepository.save(announcement.getAnnouncementDetail());
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.mapAnnouncementEntityToResponse(savedAnnouncement);

    }

    @Override
    public PageResponse<AnnouncementResponse> findAllWithSpecification(List<SearchCriteria> searchCriteriaList, Pageable pageable) {

        SearchSpecification<Announcement> searchSpecification = new SearchSpecification<>(searchCriteriaList);
        Page<Announcement> announcements = announcementRepository.findAll(searchSpecification, pageable);
        Page<AnnouncementResponse> announcementResponses = mapPageAnnouncementEntityToPageAnnouncementResponse(announcements);

        return getCustomAnnouncementResponsePage(announcementResponses);
    }

    private Page<AnnouncementResponse> mapPageAnnouncementEntityToPageAnnouncementResponse(Page<Announcement> announcements) {
        return announcements.map(announcementMapper::mapAnnouncementEntityToResponse);
    }

    private PageResponse<AnnouncementResponse> getCustomAnnouncementResponsePage(Page<AnnouncementResponse> announcementResponses) {
        return pageResponseMapper.mapPageResponse(announcementResponses);
    }

    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
