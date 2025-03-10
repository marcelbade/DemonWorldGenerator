package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.JSONConverter.JSONObjectConverter;
import marcel.demonworld.armygenerator.entities.auth.User;
import org.json.simple.JSONObject;

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

    @Lob
    @Column(name = "list", columnDefinition = "JSON")
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject list;


    @Column(name = "isVisibleToOrganizer", columnDefinition = "integer")
    private Boolean isVisibleToOrganizer;

    @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    Event event;

}






















