<%-- 
    Document   : feedBackForm
    Created on : Jun 30, 2018, 2:57:50 PM
    Author     : NguyenNTSE63030
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <font color="green">
            Feedback Form
            </font>
        </h1>

        <h2>
            <font color="purple">
            Student Id: ${sessionScope.STUDENTID}<br/>
            Student Name: ${sessionScope.USERNAME}<br/>
            </font>
        </h2>

        <form action="logOut">
            <input type="submit" value="Log Out" name="btAction" />
        </form>
        <br/><br/>

        <c:if test="${not empty sessionScope}">
            <c:if test="${not empty sessionScope.FEEDBACK}">
                <c:set var="feedback" value="${sessionScope.FEEDBACK}" />
                <c:if test="${not empty feedback.feedbackForm}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Avg</th>
                                <th>Status</th>
                                <th>Contents</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="feedbackAction">
                            <c:forEach var="result" items="${feedback.feedbackForm}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${result.value.code}
                                    </td>
                                    <td>
                                        ${result.value.name}
                                    </td>
                                    <td>
                                        ${result.value.avg}
                                    </td>
                                    <td>
                                        ${result.value.status}
                                    </td>
                                    <td>
                                        <input type="text" name="txtContents" value=""/>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkCheck" value="${result.key}_${counter.count}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5" align="center">
                                    <input type="submit" value="Send" name="btAction"/>
                                </td>
                                <td align="center">
                                    <input type="submit" value="Remove" name="btAction"/>

                                </td>
                            </tr>
                        </form>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty feedback.feedbackForm}">
                <h2>Don't have any feedback subject</h2>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.FEEDBACK}">
            <h2>Don't have any feedback subject</h2>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope}">
        <h2>Don't have any feedback subject</h2>
    </c:if>
    <a href="ViewMarks">Click Here To See Your Marks</a>
</body>
</html>
