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
    <link rel="stylesheet" type="text/css" href="/resources/css/edituser.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/gentleSelect.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="/resources/css/js/gentleSelect.js"></script>
    <script src="/resources/css/js/gentleSelect-min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#example-title').gentleSelect({
                columns: 3,
                itemWidth: 100,
                title: "Select a fruit",
                hideOnMouseOut: true
            });
        });
    </script>
    <title><s:message code="profilEdit.pageName"/></title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body background="/resources/images/background.jpg">
<script src="/resources/css/js/gentleSelect.js"></script>
<script src="/resources/css/js/gentleSelect-min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example-title').gentleSelect({
            columns: 3,
            itemWidth: 100,
            title: "Select a fruit",
            hideOnMouseOut: true
        });
    });
</script>
<%@include file="/page/incl/menu.app" %>
<section id="contact">
    <div class="section-content">
        <h1 class="section-header">Edycja profilu <span class="content-header wow fadeIn " data-wow-delay="0.2s"
                                                        data-wow-duration="2s"></span></h1>
    </div>
    <p align="center">
        <c:out value="${message }"/>
    </p>
    <sf:form id="usersForm" action="${pageContext.request.contextPath}/admin/updateuser/${user.id}"
             modelAttribute="user"
             enctype="multipart/form-data" method="POST">
        <sf:hidden path="id" value="${user.id}"/>
        <div class="section-content">
            <div class="container">
                <form>
                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label><s:message code="profil.name"/></label>
                        </div>
                        <div class="col-md-3">
                            <p><c:out value="${user.userProfile.name}"/></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label><s:message code="register.lastName"/></label>
                        </div>
                        <div class="col-md-3">
                            <p><c:out value="${user.userProfile.lastName}"/></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label><s:message code="register.email"/></label>
                        </div>
                        <div class="col-md-3">
                            <p><c:out value="${user.email}"/></p>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label><s:message code="profil.rola"/></label>
                        </div>
                        <div class="col-md-3">
                        <p><sf:select class="custom-select" cssStyle="width: 140px;   background-color:  #B0E0E6;
    color: white;
    border: 0 none;
    border-radius: 20px;
    padding: 6px 20px;" path="active" items="${roleMap}"/></p>
                    </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div  style="margin-top: -10px;" class="col-md-3">
                            <label><s:message code="profil.czyAktywny"/></label>
                        </div>

                        <div class="col-md-3">
                            <p><sf:select class="custom-select" cssStyle="width: 90px;   background-color:  #B0E0E6;
    color: white;
    border: 0 none;
    border-radius: 20px;
    padding: 6px 20px;" path="active" items="${activityMap}"/></p>
                        </div>
                    </div>


                    <input type="submit" class="btn btn-success" value="<s:message code="button.save"/>"/>
                    <input type="button" class="btn btn-danger" value="<s:message code="button.cancel"/>"
                           onclick="window.location.href='${pageContext.request.contextPath}/admin/users/1'"/>
                </form>
            </div>
        </div>


    </sf:form>
</section>
</body>
</html>