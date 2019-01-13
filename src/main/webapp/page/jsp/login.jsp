<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/login.css" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title><s:message code="logowanie.pageName" /></title>
</head>
<body background="/resources/images/background.jpg">
	<%@include file="/page/incl/menu.app"%>
	

	<form id="loginForm" action="/login" method="POST">

<div class="wrapper fadeInDown zero-raduis">
  	  <div id="formContent">
  	    <!-- Tabs Titles -->

  	    <!-- Icon -->
  	    <div class="fadeIn first">
  	      <h2 class="my-5"><s:message code="login.login"/></h2>
  	    </div>

  	    <!-- Login Form -->
  	    <form>
  	  
  	      <input type="text" id="email" class="fadeIn second zero-raduis" name="email" placeholder=<s:message code="register.email"/>>
  	      <input type="password" id="password" class="fadeIn third zero-raduis" name="password" placeholder=<s:message code="register.password"/>>
		      <div id="formFooter">
  	      	<a class="underlineHover" href="#"><s:message code="login.forgotPassword"/></a>
  	      </div>
  	      <input type="submit" class="fadeIn fourth zero-raduis" value="login">
  	     <c:if test="${not empty param.error}">
				<font color="red"><s:message code="error.logowanie"/></font>
		 </c:if>
  	      <h2><s:message code="login.notAccount"/>       </h2>
  	      <a href="/register">
  	      <input type="button" class="fadeIn fourth zero-raduis pc" value="Rejestracja">
  	      </a>
  	    </form>
  	    

  	  </div>
  </div>
		
	</form>
</body>
</html>