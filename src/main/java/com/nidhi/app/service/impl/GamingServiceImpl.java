/*
 * Copyright (c) 2021
 */

package com.nidhi.app.service.impl;

import com.nidhi.app.dto.rqst.RequestTicketRqstDTO;
import com.nidhi.app.exception.type.CustomException;
import com.nidhi.app.exception.type.ExceptionType;
import com.nidhi.app.model.Ticket;
import com.nidhi.app.repo.GamingRepo;
import com.nidhi.app.service.GamingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ricky
 * @project gaming-service
 */

@Service
public class GamingServiceImpl implements GamingService {
    private static final Logger log = LogManager.getLogger(String.valueOf(MethodHandles.lookup().lookupClass()));



    @Autowired
    GamingRepo repo;

    @Override
    public String getTicket(RequestTicketRqstDTO rqst) {
        int totalCount = repo.getTotalCountOfTickets();
        if (totalCount>20)
            throw new CustomException(ExceptionType.USERDEFINED, "TICKET_LIMIT_REACHED");
        Optional<Ticket> optTicket = repo.findByUserEmail(rqst.getUserEmail());
        if(optTicket.isPresent())
            throw new CustomException(ExceptionType.USERDEFINED, "USER_ALREADY_EXISTS",rqst.getUserName(), rqst.getUserEmail());
       Ticket ticket = new Ticket();
       BeanUtils.copyProperties(rqst, ticket);
       ticket.setDateAllotted(new Date());
       ticket.setTicket(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
       repo.save(ticket);
       return ticket.getTicket();
    }
}
