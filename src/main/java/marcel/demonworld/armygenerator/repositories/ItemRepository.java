package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.statCardDTOs.ItemCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCard, Integer> {
}
