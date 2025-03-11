package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.entities.game.ItemCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCardRepository extends JpaRepository<ItemCard, Integer> {

    @Query("SELECT i FROM ItemCard i WHERE i.itemName = :passedName AND i.faction = :passedFaction")
    Optional<ItemCard> findByNameAndFaction(@Param("passedName") String itemName, @Param("passedFaction") String faction);

}
