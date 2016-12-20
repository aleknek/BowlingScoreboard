<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
<script src="/resources/js/bowlingGame.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<div>

    <form action="/bowlingGame" method="post">

        <input type="button" class="buttonNewGame" value="New game" onClick='newGame()'>

        <c:if test="${newGame == true}">

            <c:if test="${endNewGame == false}">
                <table class="tableChoose">
                    <tr>
                        <td class="tdChoose">Choose the number of pins knocked down:</td>
                        <td class="tdChoose">
                            <select name="chooser">
                                <c:forEach var="counter" begin="0" end="${maxCountPins}">
                                    <option>${counter}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="tdChoose"><input type="submit" value="Choose" id="button"></td>
                    </tr>
                </table>
            </c:if>

            <table class="tableBowlingScore">

                <c:set var="total" value="${0}"/>

                <c:if test="${frames eq null}">
                    <tr>
                        <c:forEach var="counter" begin="1" end="21">
                            <td></td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach var="counter" begin="1" end="10">
                            <c:choose>
                                <c:when test="${counter != 10}">
                                    <th colspan="2"></th>
                                </c:when>
                                <c:otherwise>
                                    <th colspan="3"></th>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:if>

                <c:if test="${frames ne null}">
                    <tr>
                        <c:forEach items="${frames}" var="frame">
                            <c:set var="str" value="${frame.getView()}"/>
                            <c:forTokens items="${str}" delims="," var="token">
                                <td>${token}</td>
                            </c:forTokens>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach items="${frames}" var="frame">
                            <c:choose>
                                <c:when test="${frames.indexOf(frame) != 9}">
                                    <c:if test="${frameDAO.isNotActiveFrame(frame)}">
                                        <th colspan="2"></th>
                                    </c:if>
                                    <c:if test="${!frameDAO.isNotActiveFrame(frame)}">
                                        <c:set var="total" value="${total + frameDAO.getScoreOfFrame(frame)}"/>
                                        <th colspan="2">${total}</th>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${frameDAO.isNotActiveFrame(frame)}">
                                        <th colspan="3"></th>
                                    </c:if>
                                    <c:if test="${!frameDAO.isNotActiveFrame(frame)}">
                                        <c:set var="total" value="${total + frameDAO.getScoreOfFrame(frame)}"/>
                                        <th colspan="3">${total}</th>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:if>
            </table>
            <p class="total">Total score: ${total}</p>
        </c:if>
    </form>
</div>
