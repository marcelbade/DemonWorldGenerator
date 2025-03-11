package marcel.demonworld.armygenerator.entities.auth;


import jakarta.persistence.*;
import lombok.*;
import marcel.demonworld.armygenerator.entities.game.ArmyList;
import marcel.demonworld.armygenerator.entities.game.Event;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", columnDefinition = "text")
    private String userName;

    @Column(name = "userPassword", columnDefinition = "text")
    private String password;

    @Column(name = "isAdmin", columnDefinition = "integer")
    private Boolean isAdmin;

    @Column(name = "isOwner", columnDefinition = "integer")
    private Boolean isOwner;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyList> armyLists = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Event> events = new ArrayList<>();

    // helper functions - ArmyLists

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

    // helper functions - Events

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void addAllEvent(List<Event> events) {
        this.events.addAll(events);
    }

    public void deleteEvent(Event event) {
        this.events.remove(event);
    }

    public void deleteMultipleEvents(List<Event> events) {
        this.events.removeAll(events);
    }


}
