package marcel.demonworld.armygenerator.entities.auth;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.entities.game.ArmyList;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "userName", columnDefinition = "text")
    private String userName;
    @Column(name = "userPassword", columnDefinition = "text")
    private String password;
    @Column(name = "isAdmin", columnDefinition = "text")
    private Boolean isAdmin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyList> armyLists = new ArrayList<>();

    // helper functions

    public void addArmyList(ArmyList list) {
        this.armyLists.add(list);
        list.setUser(this);
    }

    public void addMultipleArmyLists(List<ArmyList> lists) {
        this.armyLists.addAll(lists);
        lists.forEach(l -> l.setUser(this));
    }

    public void deleteArmyList(ArmyList list) {
        this.armyLists.remove(list);
    }

    public void deleteMultipleArmyLists(List<ArmyList> lists) {
        this.armyLists.removeAll(lists);
    }
}
