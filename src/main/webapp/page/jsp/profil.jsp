<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/profil.css" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title><s:message code="profil.userDane"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<div class="container emp-profile">
           <sf:form id="usersForm" action="changephoto" modelAttribute="photo"
		enctype="multipart/form-data" method="POST">
		
		<sf:hidden path="id"/>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" style="height:150px;" >
                              <img src="data:image/jpeg;base64,${image}" alt="...">

                            <div class="file btn btn-lg btn-primary">
                              <s:message code="profil.changePhoto"  />
                               <sf:input type="file" onchange="this.form.submit()" id="photo" path="multipartFile" name="photo"/>


                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                       <c:out value="${user.name }"/>
                                       <c:out value="${user.lastName }"/>
                                    </h5>
                             
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><s:message code="profil.data" /></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><s:message code="profil.about" /></a>
                                </li>
                            </ul>
                              <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.sex" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.sex }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.birthDate" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.birthDate }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.email" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.email }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.phone" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.number }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.city" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                 <p><c:out value="${user.city }"/></p>
                                            </div>
                                        </div>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="register.character" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.character }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="register.language" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.language }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="register.freeTime" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.freeTime }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="register.hobby" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.hobby }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="register.physicalActivity" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.physicalActivity }"/></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label><s:message code="profil.about" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.description }"/></p>
                                            </div>
                                        </div>
                              
                                
                            </div>
                           
                        </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                  
                        <input type="button" class="profile-edit-btn btn" onclick="location.href='/editprofil';" name="btnAddMore" value="Edytuj Profil" style="height:45px;" />
                        
                      
                    </div>
                    </div>
         
                     <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                        </div>
                    </div>
                   
                </div>
                </sf:form>
        </div>
        
</body>
</html>