package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternativesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlliesAndAlternativesRepository extends JpaRepository<AllianceAndAlternativesDTO, Integer> {
}
