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
<link rel="stylesheet" type="text/css" href="/resources/css/registersteptwo.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
<title><s:message code="profilEdit.pageName"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<section id="contact">
			<div class="section-content">
				<h1 class="section-header">Edycja profilu <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"></span></h1>
			</div>
<p align="center">
		<c:out value="${message }" />
</p>
<sf:form id="usersForm" action="${pageContext.request.contextPath}/admin/updateuser/${user.id}" modelAttribute="user"
		enctype="multipart/form-data" method="POST">
		<sf:hidden path="id" value="${user.id}"/>
		
			<div class="section-content">
			<div class="container">
				<form>
			  			<div class="row">
                                <div class="col-md-6">
                                                <label><s:message code="profil.name" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.name}"/></p>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                <div class="col-md-6">
                                                <label><s:message code="register.lastName" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.lastName}"/></p>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                <div class="col-md-6">
                                                <label><s:message code="register.email" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><c:out value="${user.email}"/></p>
                                            </div>
                                        </div>
                               
				
                                        <div class="row">
                                <div class="col-md-6">
                                                <label><s:message code="profil.rola" /></label>
                                            </div>
                                            <div class="col-md-6">
                                               <p]> <sf:select path="nrRoli" items="${roleMap}"/></p>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                <div class="col-md-6">
                                                <label><s:message code="profil.czyAktywny" /></label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><sf:select path="active" items="${activityMap}"/></p>
                                            </div>
                                        </div>
                                        
                                        
                                        <input type="submit"  class="btn btn-success" value="<s:message code="button.save"/>" />
					<input type="button"  class="btn btn-danger" value="<s:message code="button.cancel"/>" 
						onclick="window.location.href='${pageContext.request.contextPath}/admin/users/1'"/>
                                        </form>
                                        </div>
                                        </div>
                                        
			  			
		
	</sf:form>
	</section>
</body>
</html>