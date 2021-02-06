/*
 * Copyright (c) 2021
 */

package com.nidhi.app.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Ricky
 * @project gaming-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsRespDTO {
    private String reward;
    private String time;
    private String rewardDay;
}
