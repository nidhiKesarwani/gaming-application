/*
 * Copyright (c) 2021
 */

package com.nidhi.app.repo;

import com.nidhi.app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ricky
 * @project gaming-service
 */

@Repository
public interface GamingRepo extends JpaRepository<Ticket,String> {

    @Query(value="select count(*) from ticket", nativeQuery = true)
    int getTotalCountOfTickets();

    Optional<Ticket> findByUserEmail(String userEmail);

    @Query(value="select * from ticket order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Ticket> computeFinalWinner();
}
