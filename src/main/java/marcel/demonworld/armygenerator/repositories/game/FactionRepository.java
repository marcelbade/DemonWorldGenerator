package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.dto.game.FactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<FactionDTO, Integer> {
}
