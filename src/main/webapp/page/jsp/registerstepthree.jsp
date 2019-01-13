<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
   	<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
   	<link rel="stylesheet" type="text/css" href="/resources/css/registersteptwo.css" />
   	</head>
  <body>
    <%@include file="/page/incl/menu.app"%>
<sf:form id="usersForm" action="registerstepthreeend" modelAttribute="user"
		enctype="multipart/form-data" method="POST">
		
		<sf:hidden path="id"/>
<section id="contact">
			<div class="section-content">
				<h1 class="section-header"><s:message code="register.step"/> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"> 1</span></h1>
				<h3><s:message code="register.registerNextStep"/></h3>
			</div>
			<div class="contact-section">
			<div class="container">
				<form>
					<div class="col-md-6 form-line">
			  			<div class="form-group">
			  				<label for="exampleInputUsername"><s:message code="register.language"/></label>
			  			
					    	<sf:input path="freeTime" type="text" class="form-control" id="freeTime"/>
				  		</div>
				  		
				  		<div class="form-group">
					    	<label for="exampleInputEmail"><s:message code="register.character"/></label>
					    	<sf:input path="language" type="text" class="form-control" id="language" />
					  	</div>	
					  	<div class="form-group">
					    	<label for="telephone"><s:message code="register.number"/></label>
					    	<sf:input path="number" type="tel" class="form-control" id="number"/>
			  			</div>
			  		</div>
			  
			  		
         
         
        	<div class="col-md-6">
			  			<div class="form-group">
			  				<label for ="description"> Message</label>
			  			 	<sf:textarea  class="form-control" id="description" ></sf:textarea>
			  			</div>
			  			<div>

			  				<button type="submit" class="btn btn-default submit"><i class="fa fa-paper-plane" aria-hidden="true"></i><s:message code="button.continue"/></button>
			  			</div>
			  			
					</div>
				
				</form>
			</div>
		</section>
		<button class="btn btn-light" type="submit">Znajdź</button>
		</sf:form>
</body>
</html>