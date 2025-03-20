package marcel.demonworld.armygenerator.Encoding;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;

import java.util.List;

public interface ArmyListEncoder {
    String encode(List<UnitCardDTO> armyList);

    List<UnitCardDTO> decode(String encodedList, String factionName);

}
