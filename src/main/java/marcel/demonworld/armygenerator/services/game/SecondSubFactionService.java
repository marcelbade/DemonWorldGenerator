package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.SecondSubFactionDTO;
import marcel.demonworld.armygenerator.entities.SecondSubFaction;
import marcel.demonworld.armygenerator.mappingInterfaces.SecondSubFactionMapper;
import marcel.demonworld.armygenerator.repositories.game.SecondSubFactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SecondSubFactionService {

    @Autowired
    private final SecondSubFactionRepository repo;

    @Autowired
    private final SecondSubFactionMapper mapper;


    public List<SecondSubFactionDTO> returnAll() {

        List<SecondSubFaction> all = repo.findAll();
        return all.stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

}
