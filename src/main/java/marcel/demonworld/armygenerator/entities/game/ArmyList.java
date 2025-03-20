package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.entities.auth.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "armylists")
public class ArmyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listName", columnDefinition = "text")
    private String listName;

    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "list", columnDefinition = "text")
    private String list;

    @Column(name = "isVisibleToOrganizer", columnDefinition = "integer")
    private Boolean isVisibleToOrganizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    Event event;

}






















