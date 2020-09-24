package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.statCards.ItemCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCard, Integer> {
}
