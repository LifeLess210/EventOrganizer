package org.example.Repositories;


import org.example.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE " +
            "FUNCTION('WEEK', e.start) = :weekNumber AND " +
            "FUNCTION('YEAR', e.start) = :year")
    List<Event> getEventsForWeek(@Param("weekNumber") Integer weekNumber,
                                 @Param("year") Integer year);
}
