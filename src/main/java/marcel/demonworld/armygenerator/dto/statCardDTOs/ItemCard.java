package marcel.demonworld.armygenerator.dto.statCardDTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "itemcards")
public class ItemCard implements DemonWorldCard {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "faction", columnDefinition = "text")
    private String faction;
    @Column(name = "name", columnDefinition = "text")
    private String itemName;
    @Column(name = "pointCost")
    private Integer pointCost;
    @Column(name = "typeOfItem", columnDefinition = "text")
    private String typeOfItem;
    @Column(name = "rules", columnDefinition = "text")
    private String rules;
    @Column(name = "usedBy", columnDefinition = "text")
    private String usedBy;
    @Column(name = "uniqueItem", columnDefinition = "text")
    private Boolean uniqueItem;


    @Override
    public int getPoints() {
        return this.pointCost;
    }

    @Override
    public String getName() {
        return this.itemName;
    }

    @Override
    public String getSubFaction() {
        return getUsedBy();
    }

}
