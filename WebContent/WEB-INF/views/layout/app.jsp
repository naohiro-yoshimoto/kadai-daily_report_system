<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>日報管理システム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
               <div id="header_menu">
                  <h1><a href="<c:url value='/' />">日報管理システム</a></h1>&nbsp;&nbsp;&nbsp;
                  <c:if test="${sessionScope.login_employee != null}">
                        <c:if test="${sessionScope.login_employee.admin_flag == 1}">  <%--管理者なら管理可能--%>
                            <a href="<c:url value='/employees/index' />">従業員管理</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="<c:url value='/reports/index' />">日報管理</a>&nbsp;&nbsp;
                        <a href="<c:url value='/search/new' />">検索</a>&nbsp;&nbsp;
                  </c:if>
               </div>
               <c:if test="${sessionScope.login_employee != null}"> <%--ログイン状態なら表示--%>
                  <div id="employee_name">
                         <c:out value="${sessionScope.login_employee.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                         <a href="<c:url value='/logout' />">ログアウト</a>
                  </div>
               </c:if>
            </div>
         <div id="content">
            ${param.content}
         </div>
         <div id="footer">
             by Taro Kirameki.
         </div>
       </div>
    </body>
</html>