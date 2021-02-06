/*
 * Copyright (c) 2021
 */

package com.nidhi.app.service.impl;

import com.nidhi.app.dto.resp.EventDetailsRespDTO;
import com.nidhi.app.dto.resp.FinalWinnerRespDTO;
import com.nidhi.app.dto.rqst.RequestTicketRqstDTO;
import com.nidhi.app.exception.type.CustomException;
import com.nidhi.app.exception.type.ExceptionType;
import com.nidhi.app.model.RewardDateMapping;
import com.nidhi.app.model.Ticket;
import com.nidhi.app.repo.GamingRepo;
import com.nidhi.app.repo.RewardDateMappingRepo;
import com.nidhi.app.service.GamingService;
import jdk.internal.joptsimple.internal.Strings;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ricky
 * @project gaming-service
 */

@Service
public class GamingServiceImpl implements GamingService {
    private static final Logger log = LogManager.getLogger(String.valueOf(MethodHandles.lookup().lookupClass()));

    @Autowired
    GamingRepo repo;

    @Autowired
    RewardDateMappingRepo rewardRepo;

    @Override
    public String getTicket(RequestTicketRqstDTO rqst) {
        int totalCount = repo.getTotalCountOfTickets();
        if (totalCount>100)
            throw new CustomException(ExceptionType.USERDEFINED, "sorry, we have already distributed all the tickets");
        Optional<Ticket> optTicket = repo.findByUserEmail(rqst.getUserEmail());
        if(optTicket.isPresent())
            throw new CustomException(ExceptionType.USERDEFINED,  "sorry, user " + rqst.getUserName() +" with email " +rqst.getUserEmail()+" has already been allotted the ticket");
       Ticket ticket = new Ticket();
       BeanUtils.copyProperties(rqst, ticket);
       ticket.setDateAllotted(new Date());
       ticket.setTicket(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
       repo.save(ticket);
       return ticket.getTicket();
    }

    @Override
    public EventDetailsRespDTO getEventDetails() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);

        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        Date limit = format2.parse("08:00");
        Date today = new Date();
        int dayIndex = 0;
        String day1 = "Today";
        if(today.after(limit))
        {
            dayIndex = day == 7 ? 1 : day + 1;
            day1 = "Tomorrow";
        }
        else
        {
            dayIndex = day;
        }

        Optional<RewardDateMapping> resp =  rewardRepo.findByDayIndex(dayIndex);
        if(resp.isPresent())
        {
            RewardDateMapping rdm = resp.get();
            return new EventDetailsRespDTO(rdm.getReward(), "8 AM", day1);
        }
        return new EventDetailsRespDTO();
    }

    @Override
    public FinalWinnerRespDTO computeFinalWinner() {
        Optional<Ticket> tick = repo.computeFinalWinner();
        if(tick.isPresent())
        {
           return new FinalWinnerRespDTO(tick.get().getTicket(), tick.get().getUserEmail(), tick.get().getUserName());
        }
        return null;
    }


}
