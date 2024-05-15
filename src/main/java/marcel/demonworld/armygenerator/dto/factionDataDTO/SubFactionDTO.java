package marcel.demonworld.armygenerator.dto.factionDataDTO;

import lombok.Data;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;


@Data
public class SubFactionDTO {

    // name of the subFaction
    private String name;
    // some faction have several alternative army lists with different sub factions as options.
    private boolean alternativeListOption;
    // true, if the sub faction is the selected option
    private boolean selectedAlternativeOption;
    /**
     * true, if all units have been blocked by validation logic, i.e., if none of the units in this sub faction can be
     * selected by the player.
     */
    private boolean hasNoValidUnits;
    // units that belong to this sub faction
    private List<UnitCard> units;
}
