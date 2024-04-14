package az.ingress.announcementapp.controller;

import az.ingress.announcementapp.dto.announcement.AnnouncementRequest;
import az.ingress.announcementapp.dto.announcement.AnnouncementResponse;
import az.ingress.announcementapp.dto.pagination.PageResponse;
import az.ingress.announcementapp.service.announcement.AnnouncementService;
import az.ingress.announcementapp.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementResponse createAnnouncement(@RequestBody AnnouncementRequest announcementRequest) {
        return announcementService.createAnnouncement(announcementRequest);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnnouncementResponse> findAllAnnouncement(@RequestBody List<SearchCriteria> searchCriteriaList, Pageable pageable) {
        return announcementService.findAllWithSpecification(searchCriteriaList, pageable);
    }

    @PutMapping("/{announcementId}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse updateAnnouncement(@PathVariable("announcementId") Long announcementId, @RequestBody AnnouncementRequest announcementRequest) {
        return announcementService.updateAnnouncement(announcementId, announcementRequest);
    }

    @DeleteMapping("/{announcementId}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse deleteAnnouncement(@PathVariable("announcementId") Long announcementId) {
        return announcementService.deleteAnnouncement(announcementId);
    }

    @GetMapping("/own")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnnouncementResponse> getAllOwnerAnnouncements(Pageable pageable) {
        return announcementService.getAllOwnAnnouncement(pageable);
    }

    @GetMapping("/{announcementId}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse getAnnouncementById(@PathVariable("announcementId") Long announcementId) {
        return announcementService.getAnnouncementById(announcementId);
    }

    @GetMapping("/own/{announcementId}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse getOwnAnnouncementWithId(@PathVariable("announcementId") Long announcementId) {
        return announcementService.getOwnAnnouncementWithId(announcementId);
    }

    @GetMapping("/own/most-viewed")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncementResponse getOwnMostViewedAnnouncement() {
        return announcementService.getOwnMostViewedAnnouncement();
    }

    @GetMapping("/most-viewed")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AnnouncementResponse> getMostViewedAnnouncements(Pageable pageable) {
        return announcementService.getMostViewedAnnouncements(pageable);
    }

}
