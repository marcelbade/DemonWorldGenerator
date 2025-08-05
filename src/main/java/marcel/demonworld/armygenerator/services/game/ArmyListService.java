package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.ArmyList;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListMapper;
import marcel.demonworld.armygenerator.repositories.game.ArmyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return all.stream().filter(Objects::nonNull).map(mapper::entityToDTO).collect(Collectors.toList());
    }

    /**
     * Method either saves and persists a new entity in the DB or updates an
     * existing entity in the DB
     *
     * @param armyListDTO ArmyListDTO
     */
    public void saveOrUpdateArmyList(ArmyListDTO armyListDTO) {
        ArmyList listObj = mapper.dtoToEntity(armyListDTO);

        if (armyListDTO.getId() != null) {
            repo.updateArmyList(listObj.getId(),
                    listObj.getListName(),
                    listObj.getList(),
                    listObj.getEventName(),
                    listObj.getFaction(),
                    listObj.getUser().getId(),
                    listObj.getCreationDate(),
                    listObj.getTeamName());
        } else {
            repo.save(listObj);
        }
    }


    public void deleteList(Long id) {
        repo.deleteArmyList(id);
    }

}
