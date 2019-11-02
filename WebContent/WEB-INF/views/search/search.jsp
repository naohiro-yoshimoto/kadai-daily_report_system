<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>検索ページ</h2>

        <form method="GET" action="<c:url value='/search' />">
            <label for="title">検索内容を入力してください。</label><br />
            <input type="text" name="search" value="${search}" />
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">検索</button>
        </form>

        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>

