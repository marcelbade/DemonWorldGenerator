package marcel.demonworld.armygenerator.dto.statCardDTOs;

import javax.persistence.*;

/**
 * one dto for all army cards, no matter what the unit type is.
 * Equipped Items are store in a simple list<ItemCard>
 */

@Entity
@Table(name = "unitcards")
public class UnitCard implements DemonWorldCard {

    @Id
    private Integer id;


    //mySQL doesn't use text, it uses "text". in order to avoid a code breaking SQL error columnDef. must be set!
    // TODO: you accidentally used text instead of int for one of the weapon values!
    @Column(name = "faction", columnDefinition = "text")
    private String faction;

    @Column(name = "name", columnDefinition = "text")
    private String unitName;

    @Column(name = "rangedWapon", columnDefinition = "text")
    private String rangedWeapon;

    @Column(name = "rangedAttackStats", columnDefinition = "text")
    private String rangedAttackStats;

    @Column(name = "unique", columnDefinition = "text")
    private Boolean uniqueUnit;

    @Column(name = "leader", columnDefinition = "text")
    private Boolean leader;

    @Column(name = "standardBearer", columnDefinition = "text")
    private Boolean standardBearer;

    @Column(name = "musician", columnDefinition = "text")
    private Boolean musician;

    @Column(name = "unitType", columnDefinition = "text")
    private String unitType;

    @Column(name = "wedgeFormation", columnDefinition = "text")
    private Boolean wedgeFormation;

    @Column(name = "skirmishFormation", columnDefinition = "text")
    private Boolean skirmishFormation;

    @Column(name = "squareFormation", columnDefinition = "text")
    private Boolean squareFormation;

    @Column(name = "horde", columnDefinition = "text")
    private Boolean horde;

    @Column(name = "commandStars")
    private Integer commandStars;

    @Column(name = "magic")
    private Integer magic;

    @Column(name = "subfaction", columnDefinition = "text")
    private String subFaction;

    @Column(name = "points")
    private Integer points;

    @Column(name = "elements")
    private Integer numberOfElements;

    @Column(name = "move")
    private Integer move;

    @Column(name = "charge")
    private Integer charge;

    @Column(name = "skirmish")
    private Integer skirmish;

    @Column(name = "hold_maneuvers")
    private Integer hold_maneuvers;

    @Column(name = "controlZone_OverRun")
    private Integer controlZone_OverRun;

    @Column(name = "Initiative")
    private Integer initiative;

    @Column(name = "size")
    private Integer unitSize;

    @Column(name = "armourRange")
    private Integer armourRange;

    @Column(name = "armourMelee")
    private Integer armourMelee;

    @Column(name = "weapon1")
    private Integer weapon1;

    @Column(name = "weapon2")
    private Integer weapon2;

    @Column(name = "skillMelee", columnDefinition = "text")
    private Integer skillMelee;

    @Column(name = "skillRange")
    private Integer skillRange;

    @Column(name = "fear")
    private Integer fear;

    @Column(name = "moral1")
    private Integer moral1;

    @Column(name = "moral2")
    private Integer moral2;

    @Column(name = "hitpoints")
    private Integer hitpoints;

    @Column(name = "specialRules", columnDefinition = "text")
    private String specialRules;

    @Override
    public String getSubFaction() {
        return subFaction;
    }

    public void setSubFaction(String subFaction) {
        this.subFaction = subFaction;
    }

    @Override
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRangedWeapon() {
        return rangedWeapon;
    }

    public void setRangedWeapon(String rangedWeapon) {
        this.rangedWeapon = rangedWeapon;
    }

    public String getRangedAttackStats() {
        return rangedAttackStats;
    }

    public void setRangedAttackStats(String rangedAttackStats) {
        this.rangedAttackStats = rangedAttackStats;
    }

    public Boolean getUniqueUnit() {
        return uniqueUnit;
    }

    public void setUniqueUnit(Boolean uniqueUnit) {
        this.uniqueUnit = uniqueUnit;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    public Boolean getStandardBearer() {
        return standardBearer;
    }

    public void setStandardBearer(Boolean standardBearer) {
        this.standardBearer = standardBearer;
    }

    public Boolean getMusician() {
        return musician;
    }

    public void setMusician(Boolean musician) {
        this.musician = musician;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Boolean getWedgeFormation() {
        return wedgeFormation;
    }

    public void setWedgeFormation(Boolean wedgeFormation) {
        this.wedgeFormation = wedgeFormation;
    }

    public Boolean getSkirmishFormation() {
        return skirmishFormation;
    }

    public void setSkirmishFormation(Boolean skirmishFormation) {
        this.skirmishFormation = skirmishFormation;
    }

    public Boolean getSquareFormation() {
        return squareFormation;
    }

    public void setSquareFormation(Boolean squareFormation) {
        this.squareFormation = squareFormation;
    }

    public Boolean getHorde() {
        return horde;
    }

    public void setHorde(Boolean horde) {
        this.horde = horde;
    }

    public Integer getCommandStars() {
        return commandStars;
    }

    public void setCommandStars(Integer commandStars) {
        this.commandStars = commandStars;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }


    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getMove() {
        return move;
    }

    public void setMove(Integer move) {
        this.move = move;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getSkirmish() {
        return skirmish;
    }

    public void setSkirmish(Integer skirmish) {
        this.skirmish = skirmish;
    }

    public Integer getHold_maneuvers() {
        return hold_maneuvers;
    }

    public void setHold_maneuvers(Integer hold_maneuvers) {
        this.hold_maneuvers = hold_maneuvers;
    }

    public Integer getControlZone_OverRun() {
        return controlZone_OverRun;
    }

    public void setControlZone_OverRun(Integer controlZone_OverRun) {
        this.controlZone_OverRun = controlZone_OverRun;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(Integer unitSize) {
        this.unitSize = unitSize;
    }

    public Integer getArmourRange() {
        return armourRange;
    }

    public void setArmourRange(Integer armourRange) {
        this.armourRange = armourRange;
    }

    public Integer getArmourMelee() {
        return armourMelee;
    }

    public void setArmourMelee(Integer armourMelee) {
        this.armourMelee = armourMelee;
    }

    public Integer getWeapon1() {
        return weapon1;
    }

    public void setWeapon1(Integer weapon1) {
        this.weapon1 = weapon1;
    }

    public Integer getWeapon2() {
        return weapon2;
    }

    public void setWeapon2(Integer weapon2) {
        this.weapon2 = weapon2;
    }

    public Integer getSkillMelee() {
        return skillMelee;
    }

    public void setSkillMelee(Integer skillMelee) {
        this.skillMelee = skillMelee;
    }

    public Integer getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(Integer skillRange) {
        this.skillRange = skillRange;
    }

    public Integer getFear() {
        return fear;
    }

    public void setFear(Integer fear) {
        this.fear = fear;
    }

    public Integer getMoral1() {
        return moral1;
    }

    public void setMoral1(Integer moral1) {
        this.moral1 = moral1;
    }

    public Integer getMoral2() {
        return moral2;
    }

    public void setMoral2(Integer moral2) {
        this.moral2 = moral2;
    }

    public Integer getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Integer hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getSpecialRules() {
        return specialRules;
    }

    public void setSpecialRules(String specialRules) {
        this.specialRules = specialRules;
    }
}
