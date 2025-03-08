package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.dto.game.FactionsDTO.FactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<FactionDTO, Integer> {
}
