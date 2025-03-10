package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.game.ArmyList;
import marcel.demonworld.armygenerator.mappingInterfaces.game.ArmyListMapper;
import marcel.demonworld.armygenerator.repositories.game.ArmyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArmyListService {

    @Autowired
    private final ArmyListRepository repo;

    @Autowired
    private final ArmyListMapper mapper;


    public List<ArmyListDTO> returnListsForUser(String userName) {

        List<ArmyList> all = repo.findAllListsByUser(userName);

        return all.stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

}
