package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.entities.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepostiory extends JpaRepository<Spell, Integer> {
}
