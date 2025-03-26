package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "list", columnDefinition = "text")
    private String list;

    @Column(name = "isVisibleToOrganizer", columnDefinition = "integer")
    private Boolean isVisibleToOrganizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "String", columnDefinition = "text")
    private String faction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    Event event;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyListAccess> UsersWithAccess = new ArrayList<>();

    // helper functions - Access

    public void addAccess(ArmyListAccess access) {
        this.UsersWithAccess.add(access);
        access.setSharedList(this);
    }

    public void addMultipleArmyLists(List<ArmyListAccess> accesses) {
        this.UsersWithAccess.addAll(accesses);
        accesses.forEach(l -> l.setSharedList(this));
    }

    public void deleteArmyList(ArmyListAccess access) {
        this.UsersWithAccess.remove(access);
    }

    public void deleteMultipleArmyLists(List<ArmyListAccess> accesses) {
        this.UsersWithAccess.removeAll(accesses);
    }

}






















