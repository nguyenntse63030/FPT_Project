/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE63030.servlet;

import SE63030.feedbackForm.FeedBackSubject;
import SE63030.feedbackForm.Feedback;
import SE63030.tbl_feedback.FeedbackDAO;
import SE63030.tbl_feedback.FeedbackDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
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
public class SendFeedbackServlet extends HttpServlet {

    private final String success = "success.html";
    private final String fail = "fail.html";

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
        PrintWriter out = response.getWriter();
        String url = fail;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Feedback feedback = (Feedback) session.getAttribute("FEEDBACK");
                if (feedback != null) {
                    Map<Integer, FeedBackSubject> feedbackInfo = feedback.getFeedbackForm();
                    if (feedbackInfo != null) {
                        String[] selectedFeedback = request.getParameterValues("chkCheck");
                        if (selectedFeedback != null) {
                            for (String selected : selectedFeedback) {
                                int subIndex = selected.lastIndexOf("_");
                                int id = Integer.parseInt(selected.substring(0, subIndex));
                                int index = Integer.parseInt(selected.substring(subIndex + 1, selected.length()));
                                String[] content = request.getParameterValues("txtContents");

                                FeedBackSubject value = feedbackInfo.get(id);
                                FeedbackDTO feedbackDTO = new FeedbackDTO();
                                feedbackDTO.setFbDate(new Date());
                                String contents = value.getCode() + "_"
                                        + value.getName() + "_"
                                        + value.getAvg() + "_"
                                        + value.getStatus() + ":"
                                        + content[index - 1];
                                feedbackDTO.setContents(contents);
                                feedbackDTO.setStudentID((String) session.getAttribute("STUDENTID"));
                                feedbackDTO.setStatus(false);

                                FeedbackDAO feedbackDAO = new FeedbackDAO();
                                boolean result = feedbackDAO.insertFeedback(feedbackDTO);
                                if (result) {
                                    url = success;
                                    session.removeAttribute("FEEDBACK");
                                }
                            }
                        }
                    }
                }
            }
        } catch (NamingException e) {
            log("SendFeedbackServlet _ NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SendFeedbackServlet _ SQLException " + e.getMessage());
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
