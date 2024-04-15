package az.ingress.announcementapp.service.announcement;

import az.ingress.announcementapp.config.security.SecurityConfig;
import az.ingress.announcementapp.dto.announcement.AnnouncementRequest;
import az.ingress.announcementapp.dto.announcement.AnnouncementResponse;
import az.ingress.announcementapp.dto.pagination.PageResponse;
import az.ingress.announcementapp.entity.Announcement;
import az.ingress.announcementapp.entity.User;
import az.ingress.announcementapp.exception.type.BaseException;
import az.ingress.announcementapp.mapper.AnnouncementMapper;
import az.ingress.announcementapp.mapper.PageResponseMapper;
import az.ingress.announcementapp.repository.AnnouncementDetailRepository;
import az.ingress.announcementapp.repository.AnnouncementRepository;
import az.ingress.announcementapp.specification.SearchCriteria;
import az.ingress.announcementapp.specification.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

import static az.ingress.announcementapp.dto.enums.response.ErrorMessages.ANNOUNCEMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementDetailRepository announcementDetailRepository;
    private final SecurityConfig securityConfig;
    private final AnnouncementMapper announcementMapper;
    private final PageResponseMapper pageResponseMapper;

    @Override
    public AnnouncementResponse createAnnouncement(AnnouncementRequest announcementRequest) {
        Announcement announcement = announcementMapper.mapAnnouncementRequestToEntity(announcementRequest);
        announcement.setUser(getLoggedInUser());
        announcementDetailRepository.save(announcement.getAnnouncementDetail());
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return getAnnouncementResponse(savedAnnouncement);
    }

    @Override
    public PageResponse<AnnouncementResponse> findAllWithSpecification(List<SearchCriteria> searchCriteriaList, Pageable pageable) {
        SearchSpecification<Announcement> searchSpecification = new SearchSpecification<>(searchCriteriaList);
        Page<Announcement> announcements = announcementRepository.findAll(searchSpecification, pageable);
        Page<AnnouncementResponse> announcementResponses = mapPageAnnouncementEntityToPageAnnouncementResponse(announcements);
        return getCustomAnnouncementResponsePage(announcementResponses);
    }

    @Override
    public AnnouncementResponse updateAnnouncement(Long id, AnnouncementRequest announcementRequest) {
        Announcement announcement = checkAnnouncementExistingGivenId(id);
        Announcement updatedAnnouncement = updateIfNotNull(announcementRequest, announcement);
        updatedAnnouncement.setId(id);
        Announcement savedNewAnnouncement = announcementRepository.save(updatedAnnouncement);
        return getAnnouncementResponse(savedNewAnnouncement);
    }

    @Override
    public AnnouncementResponse deleteAnnouncement(Long id) {
        Announcement announcement = checkAnnouncementExistingGivenId(id);
        announcementRepository.delete(announcement);
        return getAnnouncementResponse(announcement);
    }

    @Override
    @Cacheable(cacheNames = "ownAnnouncements", key = "@securityConfig.loggedInUser.id")
    public PageResponse<AnnouncementResponse> getAllOwnAnnouncement(Pageable pageable) {
        User user = getLoggedInUser();
        Page<Announcement> announcements = announcementRepository.findAllByUser(user, pageable);
        Page<AnnouncementResponse> announcementResponses = mapPageAnnouncementEntityToPageAnnouncementResponse(announcements);
        return getCustomAnnouncementResponsePage(announcementResponses);
    }

    @Override
    public AnnouncementResponse getAnnouncementById(Long id) {
        Announcement announcement = checkAnnouncementExistingGivenId(id);
        announcement.setViewCount(announcement.getViewCount() + 1);
        announcementRepository.save(announcement);
        return getAnnouncementResponse(announcement);
    }

    @Override
    public AnnouncementResponse getOwnAnnouncementWithId(Long id) {
        User user = getLoggedInUser();
        Announcement announcement = announcementRepository.findByIdAndUser(id, user).orElseThrow(supplierAnnouncementNotFoundException());
        return getAnnouncementResponse(announcement);
    }

    @Override
    public AnnouncementResponse getOwnMostViewedAnnouncement() {
        User user = getLoggedInUser();
        Announcement announcement = announcementRepository.findTopByUserOrderByViewCountDesc(user).orElseThrow(supplierAnnouncementNotFoundException());
        return getAnnouncementResponse(announcement);
    }

    @Override
    public PageResponse<AnnouncementResponse> getMostViewedAnnouncements(Pageable pageable) {
        Page<Announcement> announcements = announcementRepository.findMostViewedAnnouncements(pageable);
        Page<AnnouncementResponse> announcementResponses = mapPageAnnouncementEntityToPageAnnouncementResponse(announcements);
        return getCustomAnnouncementResponsePage(announcementResponses);
    }

    private User getLoggedInUser() {
        return securityConfig.getLoggedInUser();
    }

    private AnnouncementResponse getAnnouncementResponse(Announcement announcement) {
        return announcementMapper.mapAnnouncementEntityToResponse(announcement);
    }

    private Announcement checkAnnouncementExistingGivenId(Long id) {
        return announcementRepository.findById(id).orElseThrow(supplierAnnouncementNotFoundException());
    }

    private Announcement updateIfNotNull(AnnouncementRequest announcementRequest, Announcement announcement) {
        if (announcementRequest.getAnnouncementDetail().getTitle() != null) {
            announcement.getAnnouncementDetail().setTitle(announcementRequest.getAnnouncementDetail().getTitle());
        }
        if (announcementRequest.getAnnouncementDetail().getDescription() != null) {
            announcement.getAnnouncementDetail().setDescription(announcementRequest.getAnnouncementDetail().getDescription());
        }
        if (announcementRequest.getAnnouncementDetail().getPrice() != null) {
            announcement.getAnnouncementDetail().setPrice(announcementRequest.getAnnouncementDetail().getPrice());
        }
        return announcement;
    }

    private Supplier<BaseException> supplierAnnouncementNotFoundException() {
        return () -> BaseException.of(ANNOUNCEMENT_NOT_FOUND);
    }

    private Page<AnnouncementResponse> mapPageAnnouncementEntityToPageAnnouncementResponse(Page<Announcement> announcements) {
        return announcements.map(announcementMapper::mapAnnouncementEntityToResponse);
    }

    private PageResponse<AnnouncementResponse> getCustomAnnouncementResponsePage(Page<AnnouncementResponse> announcementResponses) {
        return pageResponseMapper.mapPageResponse(announcementResponses);
    }


}
