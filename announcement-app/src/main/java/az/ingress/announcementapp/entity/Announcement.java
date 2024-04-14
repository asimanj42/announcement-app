package az.ingress.announcementapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long viewCount;

    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private AnnouncementDetail announcementDetail;
}
