/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Setter
@Getter
@NoArgsConstructor
public class EventDTO {

    private int eventId;
    private String receiver;
    private UserDTO sender;
    private MessageEventDTO event;

    public EventDTO(String receiver, UserDTO sender, MessageEventDTO event) {
        this.receiver = receiver;
        this.sender = sender;
        this.event = event;
    }
}
