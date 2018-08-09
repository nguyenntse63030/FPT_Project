/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.servlet;

import SE63030.markTable.MarkTableProcess;
import SE63030.markTable.MarkTable;
import SE63030.tbl_Subject.SubjectDAO;
import SE63030.tbl_Subject.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NguyenNTSE63030
 */
public class ViewMarksServlet extends HttpServlet {

    private final String viewMarksPage = "viewMarks.jsp";

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

        String url = viewMarksPage;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String studentID = (String) session.getAttribute("STUDENTID");
                MarkTableProcess markTableDAO = new MarkTableProcess();
                markTableDAO.createMarkTableInfo(studentID);
                List<MarkTable> result = markTableDAO.getListMarkTable();

                if (result != null) {
                    request.setAttribute("MARKTABLEINFO", result);
                }

                float gpa = 0;
                int numberOfPassed = 0;
                int passCredit = 0;
                for (MarkTable markTable : result) {
                    SubjectDAO subjectDAO = new SubjectDAO();
                    SubjectDTO subjectDTO = subjectDAO.findByPrimaryKey(markTable.getSubjectID());

                    markTable.setSubjectName(subjectDTO.getSubjectName());

                    //Khởi tạo gpa, numberOfPassed, passCredit
                    gpa += markTable.getAvg();
                    numberOfPassed++;
                    passCredit += subjectDTO.getCredits();
                }
                gpa = (float) Math.ceil((gpa * 10 / numberOfPassed)) / 10;
                request.setAttribute("GPA", gpa);
                request.setAttribute("PASSCREDIT", passCredit);
            }
        } catch (NamingException e) {
            log("ViewMarksServlet - NamingException " + e);
        } catch (SQLException e) {
            log("ViewMarksServlet - SQLException " + e);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
