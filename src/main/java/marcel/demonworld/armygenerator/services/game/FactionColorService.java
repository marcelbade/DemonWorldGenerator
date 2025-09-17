package marcel.demonworld.armygenerator.services.game;


import lombok.AllArgsConstructor;
import marcel.demonworld.armygenerator.dto.game.EntityDTOs.FactionColorDTO;
import marcel.demonworld.armygenerator.mappingInterfaces.FactionColorMapper;
import marcel.demonworld.armygenerator.repositories.game.FactionColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactionColorService {

    @Autowired
    FactionColorRepository repo;

    @Autowired
    FactionColorMapper mapper;


    public List<FactionColorDTO> returnAll() {
        return repo.findAll().stream().map(entity -> mapper.mapEntityToDTO(entity)).collect(Collectors.toList());
    }

}
