/*
 * Copyright (c) 2021
 */

package com.nidhi.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Ricky
 * @project gaming-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    private String ticket;
    private String userEmail;
    private String userName;
    private Date dateAllotted;

    public Ticket(String ticket) {
        this.ticket = ticket;
    }
}


