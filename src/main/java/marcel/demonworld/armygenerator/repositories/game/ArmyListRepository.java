package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.entities.ArmyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArmyListRepository extends JpaRepository<ArmyList, Long> {

    @Query("SELECT a FROM ArmyList a WHERE a.user.userName = :userName")
    List<ArmyList> findAllListsByUser(@Param("userName") String userName);

    @Query("SELECT a FROM ArmyList a Where a.listName = :passedName")
    ArmyList findListByName(@Param("passedName") String listName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM armylists WHERE id = ?1",
            nativeQuery = true)
    void deleteArmyList(@Param("passedName") Long Id);

}
