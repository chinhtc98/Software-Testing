/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.controllers;

import com.group6.capstoneprojectregistration.daos.InvitationPendingDAO;
import com.group6.capstoneprojectregistration.dtos.InvitationPendingDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "InvitationPendingController", urlPatterns = {"/InvitationPendingController"})
public class InvitationPendingController extends HttpServlet {

    private static final String ERROR = "userpending.jsp";
    private static final String SUCCESS = "userpending.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = ERROR;
        
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String userEmail = user.getUserId();
            

            InvitationPendingDAO ivDao = new InvitationPendingDAO();
            List<InvitationPendingDTO> invitation = ivDao.getUserPedingByLoginUserAndStatus(userEmail,1);
            
            if (invitation.size() > 0) {
                session.setAttribute("INVITATION", invitation);
                url = SUCCESS;
            } else {
                request.setAttribute("INVITATION", "Nothing here");
                url = SUCCESS;
            }
//            EventDAO evDao = new EventDAO();
//            List<EventDTO> listEvent = evDao.getAllEventByReceiverEmail(userEmail);
//            String event = evDao.getEventOf(userEmail).getEvent().getMessageEvent();
//            if (!listEvent.isEmpty()) {
//                if ("Invite".equals(event)) request.setAttribute("INVITE", "You received an invitation to join the group by");
//                if ("Accept".equals(event)) request.setAttribute("ACCEPT", "Your invitation has been accepted by");
//                session.setAttribute("MESSAGE_USER", listEvent);
//                url = SUCCESS;
//            } else {
//                request.setAttribute("MESSAGE_USER", "You don't have any messages");
//            }

        } catch (Exception e) {
            log("Error at MessageController" + e.toString());
        
      } finally {
           request.getRequestDispatcher(url).forward(request, response);
        }  

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
