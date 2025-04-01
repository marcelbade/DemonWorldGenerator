package marcel.demonworld.armygenerator.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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

    @Column(name = "name", columnDefinition = "text")
    private String listName;

    @Column(name = "list", columnDefinition = "text")
    private String list;

    @JoinColumn(name = "String", columnDefinition = "text")
    private String faction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "event", columnDefinition = "text")
    String eventName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ArmyListAccess> usersWithAccess = new ArrayList<>();

    @Column(name = "creationDate", columnDefinition = "Date")
    private Date creationDate;

    @Column(name = "teamName", columnDefinition = "text")
    private String teamName;


    // helper functions - Access

    public void addAccess(ArmyListAccess access) {
        this.usersWithAccess.add(access);
        access.setSharedList(this);
    }

    public void addMultipleAccesses(List<ArmyListAccess> accesses) {
        this.usersWithAccess.addAll(accesses);
        accesses.forEach(l -> l.setSharedList(this));
    }

    public void deleteAccess(ArmyListAccess access) {
        this.usersWithAccess.remove(access);
    }

    public void deleteMultipleAccesses(List<ArmyListAccess> accesses) {
        this.usersWithAccess.removeAll(accesses);
    }

}






















