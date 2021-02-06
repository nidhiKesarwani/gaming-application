/*
 * Copyright (c) 2021
 */

package com.nidhi.app.ctlr;

import com.nidhi.app.dto.resp.CommonResp;
import com.nidhi.app.dto.resp.EventDetailsRespDTO;
import com.nidhi.app.dto.resp.FinalWinnerRespDTO;
import com.nidhi.app.dto.rqst.RequestTicketRqstDTO;
import com.nidhi.app.service.GamingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.text.ParseException;

@RestController
@RequestMapping(path = ROOTURLS.GAMING_CTLR_BASE_URL)
@Api(tags = {"Gaming Service APIs"})
public class GamingRestService {
    private static final Logger log = LogManager.getLogger(String.valueOf(MethodHandles.lookup().lookupClass()));

    @Autowired
    GamingService serv;

    @ApiOperation(value = "Generates Raffle Ticket")
    @PostMapping(path = ROOTURLS.GET_RAFFLE_TICKET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommonResp> getTicket(@RequestBody @Valid RequestTicketRqstDTO rqst) {
        return new ResponseEntity<>(new CommonResp(serv.getTicket(rqst)), HttpStatus.OK);
    }

    @ApiOperation(value = "Fetches the event and reward details")
    @GetMapping(path = ROOTURLS.GET_EVENT_AND_REWARD, produces = "application/json")
    public ResponseEntity<EventDetailsRespDTO> getEventDetails() throws ParseException {
        return new ResponseEntity<>(serv.getEventDetails(), HttpStatus.OK);
    }

    @ApiOperation(value = "Declares the winner based on raffle tickets")
    @PostMapping(path = ROOTURLS.COMPUTE_FINAL_WINNER,  produces = "application/json")
    public ResponseEntity<FinalWinnerRespDTO> computeFinalWinner() {
        return new ResponseEntity<>(serv.computeFinalWinner(), HttpStatus.OK);
    }

}


