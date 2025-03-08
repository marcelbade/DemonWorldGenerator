package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.entities.game.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Integer> {
}
