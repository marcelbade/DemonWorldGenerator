package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.JSONConverter.JSONObjectConverter;
import marcel.demonworld.armygenerator.entities.auth.User;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "armylists")
public class ArmyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "text")
    private String listName;

    @Lob
    @Column(name = "list", columnDefinition = "JSON")
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject list;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ListShare> listShares = new ArrayList<>();


    // helper functions

    public void addListShare(ListShare share) {
        this.listShares.add(share);
        share.setArmyList(this);
    }

    public void addMultipleShares(List<ListShare> shares) {
        this.listShares.addAll(shares);
        shares.forEach(l -> l.setArmyList(this));
    }

    public void deleteShare(ListShare share) {
        this.listShares.remove(share);
    }

    public void deleteShare(List<ListShare> shares) {
        this.listShares.removeAll(shares);
    }

}






















