package marcel.demonworld.armygenerator.mappingInterfaces;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.Faction;


public interface FactionMapper {

    FactionDTO factionToFactionDTO(Faction faction);

}
