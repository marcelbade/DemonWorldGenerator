package marcel.demonworld.armygenerator.repositories.auth;


import marcel.demonworld.armygenerator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u WHERE u.userName = :passedUserName")
    Optional<User> findByUserName(@Param("passedUserName") String userName);

    @Query("SELECT u.userName FROM User u")
    List<String> findAllUserNames();

    @Modifying
    @Transactional
    @Query(value = "UPDATE app_users set displayDeleteConfirmation = :displayDeleteDialog  where userName = :userName;", nativeQuery = true)
    void setDisplayDeleteConfirmation(@Param("displayDeleteDialog") Boolean displayDeleteDialog, @Param("userName") String userName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE app_users set displayDeleteConfirmation = :displayDeleteDialog where userName = :userName;", nativeQuery = true)
    void setDisplayOverrideConfirmation(@Param("displayDeleteDialog") Boolean displayDeleteDialog, @Param("userName") String userName);

}
