package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.UnitCard;
import marcel.demonworld.armygenerator.mapperImplementations.unitCardMapperImplementation;
import marcel.demonworld.armygenerator.repositories.game.UnitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UnitCardService {

    @Autowired
    private final UnitCardRepository repo;

    @Autowired
    private final unitCardMapperImplementation mapper;

    /**
     * Method returns all units in the DB as DTOs.
     *
     * @return List<UnitCardDTO>
     */
    public List<UnitCardDTO> findAllUnitDTOs() { //
        List<UnitCard> all = repo.findAll();

        return all.stream().filter(Objects::nonNull).map(mapper::entityToDto).collect(Collectors.toList());
    }


    /**
     * Method returns all unitCards in the DB as entities.
     *
     * @return List<UnitCard>
     */
    public List<UnitCard> findAllUnitEntities() { //
        return repo.findAll();
    }


    public void updateUnitCard(UnitCardDTO unit) {
        repo.save(mapper.dtoToEntity(unit));
    }

    /**
     * Method stores a new unit in the DB.
     *
     * @param newUnit unitCardDTO object
     * @return the generated identifier
     */
    public UnitCard createNewUnit(UnitCardDTO newUnit) {

        Optional<UnitCard> foundUnit = repo.findByNameAndFaction(newUnit.getUnitName(), newUnit.getFaction());

        if (foundUnit.isPresent()) {
            throw new AppException("unit already exists for this faction!", HttpStatus.BAD_REQUEST);
        }

        return repo.save(mapper.dtoToEntity(newUnit));
    }

    public void deleteUnit(String customItemName, String faction) {

        Optional<UnitCard> foundUnit = repo.findByNameAndFaction(customItemName, faction);
        if (!foundUnit.isPresent()) {
            throw new AppException(customItemName + " not found ", HttpStatus.BAD_REQUEST);
        } else if (foundUnit.get().getIsDeleted()) {
            throw new AppException(customItemName + " already deleted", HttpStatus.NOT_FOUND);
        } else {
            foundUnit.get().setIsDeleted(true);
            repo.save(foundUnit.get());
        }
    }


}









