package az.ingress.announcementapp.repository;

import az.ingress.announcementapp.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {

}