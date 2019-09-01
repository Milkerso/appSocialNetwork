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
    <link rel="stylesheet" type="text/css" href="/resources/css/stylepeople.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <title><s:message code="profilEdit.pageName"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="#"
      crossorigin="anonymous">

<div class="container-fluid">
    <div class="row">
        <%@include file="/page/incl/allpeople.app" %>
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="panel-heading c-list">
                        <span class="title">Czlonkowie</span>
                    </div>

                    <div class="row" style="display: none;">
                        <div class="col-xs-12">
                            <div class="input-group c-search">
                                <input type="text" class="form-control" id="contact-list-search">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button"><span
                                        class="glyphicon glyphicon-search text-muted"></span></button>
                            </span>
                            </div>
                        </div>
                    </div>

                    <ul class="list-group" id="contact-list">
                        <c:forEach var="user" items="${users }">
                            <li class="list-group-item">
                                <a href="/otherprofile/${user.id}">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6">
                                            <img src="data:image/jpeg;base64,${user.photoEncoded}"
                                                 style="width:100px;height:100px;" class="img img-rounded img-fluid"/>
                                            <div>
                                                <span class="name" style="color: black"><c:out
                                                        value="${user.name }"/> <c:out
                                                        value="${user.lastName }"/></span><br/>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <span class="glyphicon glyphicon-map-marker text-muted c-info"
                                                  data-toggle="tooltip" title="5842 Hillcrest Rd"></span>
                                            <span class="visible-xs"> <span class="text-muted"><c:out
                                                    value="${user.city }"/></span><br/></span>
                                            <span class="glyphicon glyphicon-earphone text-muted c-info"
                                                  data-toggle="tooltip" title="(870) 288-4149"></span>
                                            <span class="visible-xs"> <span class="text-muted"><c:out
                                                    value="${user.number }"/></span><br/></span>
                                            <span class="fa fa-comments text-muted c-info" data-toggle="tooltip"
                                                  title=""></span>
                                            <span class="visible-xs"> <span class="text-muted"><c:out
                                                    value="${user.email }"/></span><br/></span>
                                            <span class="glyphicon glyphicon-earphone text-muted c-info"
                                                  data-toggle="tooltip" title="(870) 288-4149"></span>
                                            <span class="visible-xs"> <span class="text-muted"><c:out
                                                    value="${user.age }"/></span><br/></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </a>
                            </li>

                        </c:forEach>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>
</body>