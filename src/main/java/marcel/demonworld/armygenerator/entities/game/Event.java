package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.entities.auth.User;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyList> armyLists;

    @ManyToOne()
    @JoinColumn(name = "organizer")
    private User organizer;


    // helper functions
    public void addArmyList(ArmyList list) {
        this.armyLists.add(list);
        list.setEvent(this);
    }

    public void addMultipleArmyLists(List<ArmyList> lists) {
        this.armyLists.addAll(lists);
        lists.forEach(l -> l.setEvent(this));
    }

    public void deleteArmyList(ArmyList list) {
        this.armyLists.remove(list);
    }

    public void deleteMultipleArmyLists(List<ArmyList> lists) {
        this.armyLists.removeAll(lists);
    }

}
