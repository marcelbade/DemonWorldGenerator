package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.entities.game.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UnitCardRepository extends JpaRepository<UnitCard, Integer> {

    @Query("SELECT u FROM UnitCard u WHERE u.unitName = :passedName AND u.faction = :passedFaction")
    Optional<UnitCard> findByNameAndFaction(@Param("passedName") String name, @Param("passedFaction") String faction);

    @Query("SELECT u FROM UnitCard u WHERE u.faction = :passedFaction OR u.faction = 'Special'")
    List<UnitCard> findAllForFaction(@Param("passedFaction") String faction);

}
