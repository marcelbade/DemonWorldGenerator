package marcel.demonworld.armygenerator.services.game;


import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SpellDTO;
import marcel.demonworld.armygenerator.dto.game.WrapperDTOs.SpellFactionDTO;
import marcel.demonworld.armygenerator.entities.Faction;
import marcel.demonworld.armygenerator.mappingInterfaces.SpellMapper;
import marcel.demonworld.armygenerator.repositories.game.FactionRepository;
import marcel.demonworld.armygenerator.repositories.game.SpellRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellService {

    @Autowired
    SpellMapper mapper;

    @Autowired
    SpellRepostiory spellRepo;

    @Autowired
    FactionRepository factionRepo;

    public List<SpellDTO> getAllSpells() {
        return spellRepo.findAll().stream().map(s -> mapper.mapEntityToDTO(s)).collect(Collectors.toList());
    }

    public List<SpellFactionDTO> getAllSpellsOrderedByFaction() {

        List<SpellFactionDTO> result = new ArrayList<>();
        List<String> factions = factionRepo.findAll().stream().map(Faction::getFactionName).collect(Collectors.toList());

        for (String faction : factions) {
            List<SpellDTO> factionSpells = spellRepo
                    .findAll()
                    .stream()
                    .filter(spell -> spell.getFaction().equals(faction))
                    .map(spell -> mapper.mapEntityToDTO(spell)).collect(Collectors.toList());

            result.add(SpellFactionDTO.builder()
                    .factionName(faction)
                    .factionSpells(factionSpells)
                    .build());
        }
        return result;
    }


    public void updateUnitCard(SpellDTO spell) {
        spellRepo.save(mapper.mapDtoToEntity(spell));
    }


}
