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
    public PageResponse<AnnouncementResponse> findAll(@RequestBody List<SearchCriteria> searchCriteriaList, Pageable pageable) {
        return announcementService.findAllWithSpecification(searchCriteriaList, pageable);
    }

}
