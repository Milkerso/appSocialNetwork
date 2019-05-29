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
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
   	<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
   	<link rel="stylesheet" type="text/css" href="/resources/css/registersteptwo.css" />
   	
   	
   	</head>
<body background="/resources/images/background.jpg">
     
    <%@include file="/page/incl/menu.app"%>
<sf:form id="usersForm" action="registersteptwoend" modelAttribute="userProfile"
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
			  				
					    	 <div class="form-group">
					      <sf:select multiple="true" path="language" class="form-control" id="language">
   							 	 <%@include file="/page/incl/language.app"%>
							</sf:select>
				  		</div>
				  		
				  		
					  	<div class="form-group">
					    	<label for="telephone"><s:message code="register.number"/></label>
					    	<sf:input path="number" type="tel" class="form-control" id="number"/>
			  			</div>
			  		</div>
			  		</div>
			  		
			  		<script>
   	$("select").mousedown(function(e){
   	    e.preventDefault();
   	    
   			var select = this;
   	    var scroll = select.scrollTop;
   	    
   	    e.target.selected = !e.target.selected;
   	    
   	    setTimeout(function(){select.scrollTop = scroll;}, 0);
   	    
   	    $(select).focus();
   	}).mousemove(function(e){e.preventDefault()});
</script>
			  		
			  
			  		
         
         
         <div class="col-md-6">
         
         <div class="form-group">
					    	<label for="exampleInputEmail"><s:message code="register.character"/></label>
					    	 <sf:select path="character" class="form-control" id="character" style="height:35px;">
   							 	<sf:option value="Nie wiem" ><s:message code="register.character5"/></sf:option>
  								<sf:option value="Sangwinik"><s:message code="register.character1"/></sf:option>
  								<sf:option value="Choleryk"><s:message code="register.character2"/></sf:option>
  								<sf:option value="Melancholik"><s:message code="register.character3"/></sf:option>
  									<sf:option value="Flagmatyk"><s:message code="register.character4"/></sf:option>
							</sf:select>
					  	</div>	
			  			     <div class="form-group">
          <label class="control-label col-sm-3"><s:message code="register.dateBirth"/> <span class="text-danger">*</span></label>
         <div class="input-group registration-date-time">
            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
            		<sf:input path="birthDate" id="birthDate" class="form-control" type="date"/>
            		</div>
          
           
			  			<div>
			  				<button type="submit"  value=<s:message code="register.accept"/> class="btn btn-default submit"><i class="fa fa-paper-plane" aria-hidden="true" style="bottom: 5px;"><s:message code="register.accept"/></i></button>
			  			</div>
			  			
			  			
					</div>
					</div>
				
				</form>
			</div>
		</section>

		</sf:form>
		
			
</body>
</html>