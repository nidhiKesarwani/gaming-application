/*
 * Copyright (c) 2021
 */

package com.nidhi.app.service;

import com.nidhi.app.dto.rqst.RequestTicketRqstDTO;

/**
 * @author Ricky
 * @project gaming-service
 */
public interface GamingService {
    String getTicket(RequestTicketRqstDTO rqst);
}
