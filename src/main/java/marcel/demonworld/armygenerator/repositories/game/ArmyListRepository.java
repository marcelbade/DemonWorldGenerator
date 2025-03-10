package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.entities.game.ArmyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmyListRepository extends JpaRepository<ArmyList, Long> {

    @Query("SELECT a FROM ArmyList a WHERE a.user.userName = :userName")
    List<ArmyList> findAllListsByUser(@Param("userName") String userName);

}
