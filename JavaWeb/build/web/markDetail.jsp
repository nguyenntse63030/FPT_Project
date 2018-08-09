<%-- 
    Document   : viewMarkDetails
    Created on : Jun 28, 2018, 3:19:26 AM
    Author     : NguyenNTSE63030
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mark Detail</title>
    </head>
    <body>
        <h1><font color="green">View Mark Details Page</font></h1>
        <h2>
            <font color="purple">
            Welcome, ${sessionScope.USERNAME}
            </font>
        </h2>
        <form action="logOut">
            <input type="submit" value="Log Out" name="btAction" />
        </form>
        <br/><br/>
        Subject's mark details<br/>
        <br/>
        <c:set var="markdetails" value="${requestScope.MARKDETAILS}"/>
        <c:if test="${not empty markdetails}">
            subject ID: ${requestScope.SUBJECTID}<br/><br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Subject Name</th>
                        <th>Block</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${markdetails}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${requestScope.SUBJECTNAME}
                            </td>
                            <td>
                                ${detail.block}
                            </td>
                            <td>
                                ${detail.semester}
                            </td>
                            <td>
                                ${detail.year}
                            </td>
                            <td>
                                <c:if test="${detail.status eq 'Passed'}">
                                    <font color='green'>${detail.status}</font>
                                </c:if>
                                <c:if test="${detail.status eq 'Fail'}">
                                    <font color='red'>${detail.status}</font>
                                </c:if>
                                <c:if test="${detail.status eq 'Not_Started'}">
                                    <font color='gray'>${detail.status}</font>
                                </c:if>
                                <c:if test="${detail.status eq 'Improved'}">
                                    <font color='orange'>${detail.status}</font>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" align="center">
                            Number Of Studying: ${requestScope.NOS}
                        </td>
                    </tr>
                </tbody>
            </table><br/>
        </c:if>
        <a href="ViewMarks">Click Here To See Your All Marks</a>
    </body>
</html>
