<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/places.css"/>

    <title><s:message code="profilEdit.pageName"/></title>

</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="#"
      crossorigin="anonymous">
<div class="container-fluid">
    <div class="row">
        <c:forEach var="place" items="${places }">
            <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                <div class="productbox">
                    <img src="data:image/jpeg;base64,${place.photoEncoded}" style="width: 100%;height: auto;"
                         class="img-responsive">
                    <div class="producttitle"><c:out value="${place.title }"/></div>
                    <p class="text-justify"><c:out value="${place.description }"/></p>
                    <address>
                        <c:out value="${place.address}"/>
                        </br>

                        <span title="Phone">Telefon: </span> <c:out value="${place.number }"/>
                    </address>

                    <address>
                        <strong>Zapraszam</strong><br>
                        <a style="color: black;" href="mailto:#"><c:out value="${place.email }"/></a>
                    </address>
                    <a style="margin-top: -30px;" href="<c:out value="${place.link }"/>" class="btn btn-success btm-sm"
                                                role="button">Strona miejsca<span
                            class="glyphicon glyphicon-cutlery"></span></a>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
</body>
</html>