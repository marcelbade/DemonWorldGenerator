package marcel.demonworld.armygenerator.repositories;


import marcel.demonworld.armygenerator.dto.FactionsDTO.FactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<FactionDTO, Integer> {
}
