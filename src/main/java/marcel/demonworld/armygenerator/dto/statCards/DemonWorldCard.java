package marcel.demonworld.armygenerator.dto.statCards;


/**
 * Interface for all stat cards in the game. Needed for subtytping so that methods can add the point
 * cost for both items and units.
 */
public interface DemonWorldCard {

    public int getPoints();
    public String getSubFaction();
}
