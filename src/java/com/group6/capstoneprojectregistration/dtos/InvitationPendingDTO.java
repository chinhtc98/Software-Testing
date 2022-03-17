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
public class InvitationPendingDTO {

    private int id;
    private UserStatusDTO status;
    private UserDTO user;
    private GroupDTO group;
    private String userInvited;
 
//    public InvitationPendingDTO(UserStatusDTO status, UserDTO user, GroupDTO group, String userInvited) {
//        this.status = status;
//        this.user = user;
//        this.group = group;
//        this.userInvited = userInvited;
//    }

    public InvitationPendingDTO(UserStatusDTO status, UserDTO user, GroupDTO group, String userInvited) {
        this.status = status;
        this.user = user;
        this.group = group;
        this.userInvited = userInvited;
    }
    

}
