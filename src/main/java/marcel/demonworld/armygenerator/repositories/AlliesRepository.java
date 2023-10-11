package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.AlliancesDTO.Alliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlliesRepository extends JpaRepository<Alliance, Integer> {
}
