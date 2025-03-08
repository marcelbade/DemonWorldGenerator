package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.dto.game.EntityDTOs.ItemCardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCardDTO, Integer> {
}
