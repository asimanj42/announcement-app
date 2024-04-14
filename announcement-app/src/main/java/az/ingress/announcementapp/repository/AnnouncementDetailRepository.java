package az.ingress.announcementapp.repository;

import az.ingress.announcementapp.entity.AnnouncementDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDetailRepository extends JpaRepository<AnnouncementDetail, Long> {
}
