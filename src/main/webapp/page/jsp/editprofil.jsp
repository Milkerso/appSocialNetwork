<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/registersteptwo.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/menu.css"/>
    <title><s:message code="profilEdit.pageName"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>

<h2 align="center"><s:message code="profilEdit.pageName"/></h2>

<sf:form id="usersForm" action="updateprofil" modelAttribute="user"
         enctype="multipart/form-data" method="POST">

    <sf:hidden path="id"/>
    <div class="section-content">
        <div class="container">
            <form>

                <div class="row">
                    <div class="col-md-6">
                        <label><s:message code="profil.name"/></label>
                    </div>
                    <div class="col-md-6">
                        <p><sf:input path="userProfile.name" id="userProfile.name"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label><s:message code="register.lastName"/></label>
                    </div>
                    <div class="col-md-6">
                        <p><sf:input path="userProfile.lastName" id="userProfile.lastName"/></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label><s:message code="register.email"/></label>
                    </div>
                    <div class="col-md-6">
                        <p><sf:input path="email" id="email"/></p>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary"><s:message code="button.save"/></button>
                <button type="button" class="btn btn-danger"
                        onclick="window.location.href='${pageContext.request.contextPath}/'">
                    <s:message code="button.cancel"/></button>

            </form>
        </div>
    </div>


</sf:form>

</body>
</html>