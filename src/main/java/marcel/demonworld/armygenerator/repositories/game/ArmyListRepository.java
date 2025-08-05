package marcel.demonworld.armygenerator.repositories.game;


import marcel.demonworld.armygenerator.entities.ArmyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface ArmyListRepository extends JpaRepository<ArmyList, Long> {

    @Query("SELECT a FROM ArmyList a WHERE a.user.userName = :userName")
    List<ArmyList> findAllListsByUser(@Param("userName") String userName);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM armylists WHERE id = :id",
            nativeQuery = true)
    void deleteArmyList(@Param("id") Long Id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE armylists set " +
            "name = :listName, " +
            "list = :list, " +
            "event= :eventName, " +
            "faction = :faction, " +
            "userID= :userID,  " +
            "creationDate = :creationDate, " +
            "teamName = :teamName " +
            "WHERE id = :id;",
            nativeQuery = true)
    void updateArmyList(@Param("id") Long id,
                        @Param("listName") String listName, //
                        @Param("list") String list,
                        @Param("eventName") String eventName,
                        @Param("faction") String faction,
                        @Param("userID") Long userID,
                        @Param("creationDate") Date creationDate,
                        @Param("teamName") String teamName);

}
