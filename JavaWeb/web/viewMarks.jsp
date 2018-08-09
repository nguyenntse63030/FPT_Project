<%-- 
    Document   : viewMark
    Created on : Jun 27, 2018, 1:35:41 AM
    Author     : NguyenNTSE63030
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Mark</title>
    </head>
    <body>
        <h1><font color="green">View Mark Page</font></h1>
        <h2>
            <font color="purple">
            Welcome, ${sessionScope.USERNAME}
            </font>
        </h2>
        <form action="logOut">
            <input type="submit" value="Log Out" name="btAction"/>
        </form>
        <br/><br/>
        subject's mark details<br/><br/>
        <c:set var="markTableInfo" value="${requestScope.MARKTABLEINFO}"/>
        <c:if test="${not empty markTableInfo}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Subject Name</th>
                        <th>Block</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>Avg</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="feedback">
                    <c:forEach var="result" items="${markTableInfo}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${result.subjectName}
                            </td>
                            <td>
                                ${result.block}
                            </td>
                            <td>
                                ${result.semester}
                            </td>
                            <td>
                                ${result.year}
                            </td>
                            <td>
                                ${result.avg}
                            </td>
                            <td>
                                <c:if test="${result.status eq 'Passed'}">
                                    <font color='green'>${result.status}</font>
                                </c:if>
                                <c:if test="${result.status eq 'Fail'}">
                                    <font color='red'>${result.status}</font>
                                </c:if>
                                <c:if test="${result.status eq 'Not_Started'}">
                                    <font color='gray'>${result.status}</font>
                                </c:if>
                                <c:if test="${result.status eq 'Improved'}">
                                    <font color='orange'>${result.status}</font>
                                </c:if>
                            </td>
                            <td>
                                <c:url var="viewDetailLink" value="markDetail">
                                    <c:param name="subjectID" value="${result.subjectID}"/>
                                    <c:param name="subjectName" value="${result.subjectName}"/>
                                    <c:param name="numberOfStudying" value="${result.block}"/>
                                </c:url>
                                <a href="${viewDetailLink}"><b>View Details</b></a>
                            </td>
                            <td>
                                <input type="checkbox" name="chkFeedback" value="${result.subjectID}"
                                       <c:if test="${result.status eq 'Not_Started'}">
                                           disabled
                                       </c:if>
                                       />
                                <input type="hidden" name="subjectName" value="${result.subjectName}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            Pass Credit: ${requestScope.PASSCREDIT}
                        </td>
                        <td colspan="3">
                            GPA: ${requestScope.GPA}
                        </td>
                        <td colspan="2" align="center">
                            <input type="submit" value="Send Feedback" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty markTableInfo}">
        <h1>You don't have any mark report</h1>
    </c:if>
</body>
</html>
