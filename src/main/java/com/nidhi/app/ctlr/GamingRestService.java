/*
 * Copyright (c) 2021
 */

package com.nidhi.app.ctlr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(path = ROOTURLS.GAMING_CTLR_BASE_URL)
@Api(tags = {"Gaming Service APIs"})
public class GamingRestService {

    private static final Logger log = LogManager.getLogger(String.valueOf(MethodHandles.lookup().lookupClass()));

    @PostMapping(path = ROOTURLS.TEST_URL, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "", notes = "")
    public void testMethod() {

    }
}

