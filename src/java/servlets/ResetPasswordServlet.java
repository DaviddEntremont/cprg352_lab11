/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //need a conditional that checks for the uuid
        String UUID = request.getParameter("uuid");

        if (UUID != null) {
            request.setAttribute("UUID", UUID);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();

        String action = request.getParameter("action");

        switch (action) {
            case "sendemail":
                String email = request.getParameter("email");
                String path = getServletContext().getRealPath("/WEB-INF");
                String url = request.getRequestURL().toString();
                request.setAttribute("messagesent", true);
                try {
                    as.resetPassword(email, path, url);
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
                break;
            case "resetpassword":
                String UUID = request.getParameter("thisuuid");
                String newPassword = request.getParameter("newpassword");
                try {
                    as.changePassword(UUID, newPassword);
                    request.setAttribute("passwordreset", true);
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);

                }
                break;
        }        
        getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        return;
    }
}
