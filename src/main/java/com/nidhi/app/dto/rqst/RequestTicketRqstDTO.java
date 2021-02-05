/*
 * Copyright (c) 2021
 */

package com.nidhi.app.dto.rqst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ricky
 * @project gaming-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTicketRqstDTO {
    @NotEmpty
    private String userEmail;
    private String userName;
}
