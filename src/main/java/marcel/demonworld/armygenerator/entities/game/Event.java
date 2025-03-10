package marcel.demonworld.armygenerator.entities.game;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marcel.demonworld.armygenerator.entities.auth.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "list_sharedWith")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "text")
    private String eventName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyList> armyLists;

    @ManyToOne()
    @JoinColumn(name ="organizer")
    private User organizer;

//    @Column( name ="", columnDefinition = "")
//    Date eventDate;

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
