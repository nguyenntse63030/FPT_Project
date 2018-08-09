/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.servlet;

import SE63030.tbl_Account.AccountDAO;
import SE63030.tbl_Student.StudentDAO;
import SE63030.tbl_Student.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NguyenNTSE63030
 */
public class LoginServlet extends HttpServlet {

    private final String viewMarksServlet = "ViewMarks";
    private final String invalidPage = "invalid.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = invalidPage;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            AccountDAO accountDAO = new AccountDAO();
            boolean result = accountDAO.checkLogin(username, password);
            if (result) {
                url = viewMarksServlet;

                StudentDAO studentDAO = new StudentDAO();
                StudentDTO studentDTO = studentDAO.findByPrimaryKey(username);
                HttpSession session = request.getSession();

                session.setAttribute("STUDENTID", username.toUpperCase());
                session.setAttribute("USERNAME", studentDTO.getLastName() + " " + studentDTO.getMiddleName() + " " + studentDTO.getFirstName());
            }
        } catch (NamingException e) {
            log("LoginServlet - NamingException " + e);
        } catch (SQLException e) {
            log("LoginServlet - SQLException " + e);
        } finally {
            response.sendRedirect(url);

            out.close();
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
