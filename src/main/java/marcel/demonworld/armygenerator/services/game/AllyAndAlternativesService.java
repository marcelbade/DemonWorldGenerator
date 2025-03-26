package marcel.demonworld.armygenerator.services.game;

import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.AllianceAndAlternativesDTO;
import marcel.demonworld.armygenerator.entities.AllianceAndAlternatives;
import marcel.demonworld.armygenerator.mappingInterfaces.AlliesAndAlternativesMapper;
import marcel.demonworld.armygenerator.repositories.game.AlliesAndAlternativesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AllyAndAlternativesService {

    @Autowired
    private final AlliesAndAlternativesRepository repo;

    @Autowired
    private final AlliesAndAlternativesMapper mapper;

    public List<AllianceAndAlternativesDTO> returnAll() {

        List<AllianceAndAlternatives> all = repo.findAll();

        return all.stream().filter(Objects::nonNull).map(mapper::mapEntityToDTO).collect(Collectors.toList());

    }
}
