package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.Exceptions.AppException;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.UnitCardDTO;
import marcel.demonworld.armygenerator.entities.game.UnitCard;
import marcel.demonworld.armygenerator.mapperImplementations.game.unitCardMapperImplementation;
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
     * Method returns all units in the game.
     *
     * @return List<UnitCard>
     */
    public List<UnitCardDTO> returnAll() { //
        List<UnitCard> all = repo.findAll();

        return all.stream().filter(Objects::nonNull).map(mapper::entityToDto).collect(Collectors.toList());
    }

    public void updateUnitCard(UnitCardDTO unit) {
        repo.save(mapper.dtoToEntity(unit));
    }

    public void createNewUnit(UnitCardDTO newUnit) {

        Optional<UnitCard> foundUnit = repo.findByNameAndFaction(newUnit.getUnitName(), newUnit.getFaction());

        if (foundUnit.isPresent()) {
            throw new AppException("unit already exists for this faction!", HttpStatus.BAD_REQUEST);
        }

        repo.save(mapper.dtoToEntity(newUnit));
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









