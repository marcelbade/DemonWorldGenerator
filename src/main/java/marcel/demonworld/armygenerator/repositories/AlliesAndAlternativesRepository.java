package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.AllianceAndAlternatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlliesAndAlternativesRepository extends JpaRepository<AllianceAndAlternatives, Integer> {
}
