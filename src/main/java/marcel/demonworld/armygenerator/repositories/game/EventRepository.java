package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("SELECT e from Event e WHERE e.organizer.userName = :userName")
    List<Event> findAllEventsForUser(@Param("userName") String userName);

    @Query("SELECT e from Event e WHERE e.eventName = :eventName")
    Optional<Event> findEventByName(@Param("eventName") String eventName);
}
