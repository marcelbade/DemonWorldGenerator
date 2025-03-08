package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionDTO;
import marcel.demonworld.armygenerator.entities.game.Faction;
import marcel.demonworld.armygenerator.mappingInterfaces.game.FactionMapper;
import marcel.demonworld.armygenerator.repositories.game.FactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactionService {

    @Autowired
    private FactionRepository repo;

    @Autowired
    private FactionMapper mapper;

    public List<FactionDTO> returnAll() {
        List<Faction> all = repo.findAll();
        return all.stream().map(a -> mapper.factionToFactionDTO(a) ).collect(Collectors.toList());
    }

}
