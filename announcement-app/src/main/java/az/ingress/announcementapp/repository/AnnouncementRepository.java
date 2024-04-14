package az.ingress.announcementapp.repository;

import az.ingress.announcementapp.entity.Announcement;
import az.ingress.announcementapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {

    Page<Announcement> findAllByUser(User user, Pageable pageable);

    Optional<Announcement> findByIdAndUser(Long id, User user);

    @Query("select a from Announcement a where a.user = ?1 order by a.viewCount desc limit 1")
    Optional<Announcement> findTopByUserOrderByViewCountDesc(User user);

    @Query("select a from Announcement a order by a.viewCount desc")
    Page<Announcement> findMostViewedAnnouncements(Pageable pageable);

}