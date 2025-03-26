package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.Faction;
import marcel.demonworld.armygenerator.mappingInterfaces.FactionMapper;
import marcel.demonworld.armygenerator.repositories.game.FactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactionService {

    @Autowired
    private final FactionRepository repo;

    @Autowired
    private final FactionMapper mapper;

    public List<FactionDTO> returnAll() {
        List<Faction> all = repo.findAll();
        return all.stream().filter(Objects::nonNull).map(mapper::factionToFactionDTO).collect(Collectors.toList());
    }

}
