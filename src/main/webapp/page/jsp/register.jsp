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
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/register.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title><s:message code="menu.register"/></title>
</head>
<body>
<%@include file="/page/incl/menu.app" %>


<p align="center">
		<c:out value="${message }" />
</p>

	<sf:form id="usersForm" action="adduser" modelAttribute="user" 
	enctype="multipart/form-data" method="POST">
		
	<div class="container">
<hr>


<div class="row justify-content-center">
<div class="col-md-6">
<div class="card">
<header class="card-header">
	<a href="/login" class="float-right btn btn-outline-primary mt-1"><s:message code="menu.login"/></a>
	<h4 class="card-title mt-2"><s:message code="menu.register"/></h4>
</header>
<article class="card-body">
<form>
	<div class="form-row">
		<div class="col form-group">
			<label><s:message code="register.name"/></label>   
		   <sf:input path="name"
						size="28" id="name" type="text" class="form-control" /> 
						
			<label><font color="red"><sf:errors path="name"/></font></label>   
		</div> <!-- form-group end.// -->
		<div class="col form-group">
			<label><s:message code="register.lastName"/></label>
				 <sf:input path="lastName"
						size="28" type="text" class="form-control" placeholder=""/>
						<label><font color="red"><sf:errors path="lastName"/></font></label> 
		</div> <!-- form-group end.// -->
	</div> <!-- form-row end.// -->
	<div class="form-group">
		<label><s:message code="register.email"/></label>
	<sf:input path="email" size="28" type="email" class="form-control" placeholder="" />
		<label><font color="red"><sf:errors path="email"/></font></label> 
		<small class="form-text text-muted"><s:message code="register.noShare"/></small>
	</div> <!-- form-group end.// -->
	<div class="form-group">

	</div> <!-- form-group end.// -->
	<div class="form-row">
		<div class="form-group col-md-6">
		  <label><s:message code="register.city"/></label>
		   <sf:input path="city" size="28" id="city" type="text" class="form-control" /> 
		</div> <!-- form-group end.// -->
		<div class="form-group col-md-6">
		  <label><s:message code="register.sex"/></label>
		<sf:select path="sex" id="sex" class="form-control">
		    <sf:option value="kobieta"><s:message code="register.female"/></sf:option>
		    <sf:option value="meżczyzna"><s:message code="register.male"/></sf:option>
		    </sf:select>
		</div> <!-- form-group end.// -->
	</div> <!-- form-row.// -->
	<div class="form-group">
		<label><s:message code="register.password"/></label>
	    <sf:password path="password" size="28" class="form-control" /> 
		<label><font color="red"><sf:errors path="password"/></font></label> 
	</div> <!-- form-group end.// -->  
    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block formbutton"> <s:message code="button.register"/>  </button>
    </div> <!-- form-group// -->      
    <small class="text-muted"><s:message code="register.policy"/></small>                                          
</form>
</article> <!-- card-body end .// -->

<div class="border-top card-body text-right"> <s:message code="register.haveAccount"/> <a href="/login"><s:message code="menu.login"/></a></div>
</div> <!-- card.// -->
</div> <!-- col.//-->

</div> <!-- row.//-->


</div> 
	</sf:form>
<!--container end.//-->



</body>
</html>
