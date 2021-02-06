/*
 * Copyright (c) 2021
 */

package com.nidhi.app.service;

import com.nidhi.app.dto.resp.EventDetailsRespDTO;
import com.nidhi.app.dto.resp.FinalWinnerRespDTO;
import com.nidhi.app.dto.rqst.RequestTicketRqstDTO;

import java.text.ParseException;

/**
 * @author Ricky
 * @project gaming-service
 */
public interface GamingService {
    String getTicket(RequestTicketRqstDTO rqst);

    EventDetailsRespDTO getEventDetails() throws ParseException;

    FinalWinnerRespDTO computeFinalWinner();
}
