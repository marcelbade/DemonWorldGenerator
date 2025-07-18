package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ArmyListDTO;
import marcel.demonworld.armygenerator.entities.ArmyList;
import marcel.demonworld.armygenerator.mappingInterfaces.ArmyListMapper;
import marcel.demonworld.armygenerator.repositories.game.ArmyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public ArmyListDTO findListByName(String listName) {
        ArmyList list = repo.findListByName(listName);
        return mapper.entityToDTO(list);
    }

    public ArmyListDTO findListById(Long Id) {

        Optional<ArmyList> result = repo.findById(Id);

        if(result.isPresent()){
            return mapper.entityToDTO(result.get());
        }else{
            throw new AppException("list" + Id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public void addArmyList(ArmyListDTO armyListDTO) {
        repo.save(mapper.dtoToEntity(armyListDTO));
    }

    public void deleteList(Long id) {
        repo.deleteArmyList(id);
    }


}
