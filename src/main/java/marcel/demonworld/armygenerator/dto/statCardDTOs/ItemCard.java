package marcel.demonworld.armygenerator.dto.statCards;

import javax.persistence.*;


@Entity
@Table(name = "itemcards")
public class ItemCard implements DemonWorldCard {

    @Id
    private Integer id;
    @Column(name = "faction", columnDefinition = "text")
    private String faction;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "pointCost")
    private Integer pointCost;
    @Column(name = "typeOfItem", columnDefinition = "text")
    private String typeOfItem;
    @Column(name = "rules", columnDefinition = "text")
    private String rules;
    @Column(name = "usedBy", columnDefinition = "text")
    private String usedBy;
    @Column(name = "unique", columnDefinition = "text")
    private Boolean unique;

    public String getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(String typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return pointCost;
    }

    @Override
    public String getSubFaction() {
        return getUsedBy();
    }

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }


    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
