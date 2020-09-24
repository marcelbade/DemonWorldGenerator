package marcel.demonworld.armygenerator.dto.statCards;

import javax.persistence.*;


public class ItemCard implements DemonWorldCard {

    @Id
    private Integer id;
    private String faction;
    private String name;
    private Integer pointCost;
    private String typeOfItem;
    private String rules;
    private String usedBy;
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
