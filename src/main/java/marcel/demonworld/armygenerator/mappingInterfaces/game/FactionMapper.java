package marcel.demonworld.armygenerator.mappingInterfaces.game;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.game.Faction;
import org.springframework.stereotype.Component;


public interface FactionMapper {

    FactionDTO factionToFactionDTO(Faction faction);

}
