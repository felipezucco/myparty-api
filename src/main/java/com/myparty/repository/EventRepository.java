package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e "+
                    " INNER JOIN House h ON h = e.house "+
                    " INNER JOIN Local l ON l = h.local "+
                    " INNER JOIN Organization o ON o = l.organization "+
                 " WHERE o.id = :id")
    List<Event> findEventByOrganizationId(@Param("id") Long id);

}
