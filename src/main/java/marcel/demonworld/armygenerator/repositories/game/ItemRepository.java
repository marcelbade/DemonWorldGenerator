package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.dto.game.statCardDTOs.ItemCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCard, Integer> {
}
