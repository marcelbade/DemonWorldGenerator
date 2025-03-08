package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.JSONConverter.JSONObjectConverter;
import marcel.demonworld.armygenerator.entities.auth.User;
import org.json.simple.JSONObject;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "armylists")
public class ArmyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Lob
    @Column(name = "list", columnDefinition = "JSON")
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject list;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User creator;


}
