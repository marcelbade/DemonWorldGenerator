package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "dwEvents")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eventName", columnDefinition = "text")
    private String eventName;

    @Column(name = "eventDate", columnDefinition = "date")
    private Date eventDate;

    @Column(name = "location", columnDefinition = "text")
    private String location;

    @Column(name = "URL", columnDefinition = "text")
    private String url;

    @ManyToOne()
    @JoinColumn(name = "eventOrganizer")
    private User eventOrganizer;

}
