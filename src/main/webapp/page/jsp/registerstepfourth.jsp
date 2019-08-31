<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/profil.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/registersteptwo.css"/>


</head>
<body background="/resources/images/background.jpg">

<%@include file="/page/incl/menu.app" %>
<sf:form id="usersForm" action="registerstepfourthend" modelAttribute="photo"
         enctype="multipart/form-data" method="POST">

<sf:hidden path="id"/>
<section id="contact">
    <div class="section-content">
        <h1 class="section-header"><s:message code="register.step"/> <span class="content-header wow fadeIn "
                                                                           data-wow-delay="0.2s" data-wow-duration="2s"> 3</span>
        </h1>
        <h3>Wstaw zdjęcie profilowe</h3>
    </div>
    <div class="contact-section">
        <div class="container">
            <div class="full-width">
                <div style=" position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
                    <div class="profile-img" style=" height: 200px; width: 300px;">
                        <img src="data:image/jpeg;base64,${image}" alt="..." class="img-fluid img-thumbnail">
                        <div class="file btn btn-lg btn-primary">
                            <s:message code="profil.changePhoto"/>
                            <sf:input type="file" onchange="this.form.submit()" id="photo" path="multipartFile"
                                      name="photo"/>
                        </div>
                    </div>

                </div>
                <div style="margin-top: 175px; margin-right: 220px;">

                    </sf:form>
                    <form action="/index">
                        <button type="submit" onclick="location.href='/profil';" value=
                        <s:message code="register.accept"/> class="btn btn-default submit
                        "style="margin-top=50px;"><i class="fa fa-paper-plane" aria-hidden="true"><s:message
                            code="register.accept"/></i></button>
                    </form>
                </div>


            </div>


        </div>
    </div>
    </div>


</section>


</body>
</html>