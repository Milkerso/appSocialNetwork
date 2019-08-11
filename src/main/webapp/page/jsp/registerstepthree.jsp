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
	<sf:form id="usersForm" action="registerstepthreeend" modelAttribute="userProfile"
			 enctype="multipart/form-data" method="POST">
		
		<sf:hidden path="id"/>
<section id="contact">
			<div class="section-content">
				<h1 class="section-header"><s:message code="register.step"/> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"> 2</span></h1>
				<h3><s:message code="register.registerNextStep"/></h3>
			</div>
			<div class="contact-section">
			<div class="container">
				<form>
					<div class="col-md-6 form-line">
			  			<div class="form-group">
			  				<label for="exampleInputUsername"><s:message code="register.freeTime"/></label>
			  				
					    	 <div class="form-group">
					      <sf:select multiple="true" path="freeTime" class="form-control" id="freeTime" style="height:70px;">
   							 <sf:option value="1"><s:message code="register.freeTime1"/></sf:option>
   							 <sf:option value="2"><s:message code="register.freeTime2"/></sf:option>
   							 <sf:option value="3"><s:message code="register.freeTime3"/></sf:option>
							</sf:select>
				  		</div>
				  		<label for="exampleInputUsername"><s:message code="register.physicalActivity"/></label>
					  	   	 <div class="form-group">
					      <sf:select multiple="true" path="physicalActivity" class="form-control" id="physicalActivity">
   							 <sf:option value="1"><s:message code="activity.basketball"/></sf:option>
   							 <sf:option value="2"><s:message code="activity.football"/></sf:option>
   							 <sf:option value="3"><s:message code="activity.running"/></sf:option>
   							 <sf:option value="4"><s:message code="activity.walking"/></sf:option>
   							 <sf:option value="5"><s:message code="activity.skate"/></sf:option>
   							 <sf:option value="6"><s:message code="activity.skiing"/></sf:option>
   							 <sf:option value="7"><s:message code="activity.hockey"/></sf:option>
   							 <sf:option value="8"><s:message code="activity.tennis"/></sf:option>
   							 <sf:option value="9"><s:message code="activity.tennisTable"/></sf:option>
   							 <sf:option value="10"><s:message code="activity.snowboard"/></sf:option>
   							 <sf:option value="11"><s:message code="activity.horseRiding"/></sf:option>
							</sf:select>
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
			  				<label for="exampleInputUsername"><s:message code="register.whoSearch"/></label>
			  				
					    	 <div class="form-group">
					      <sf:select path="whoSearch" class="form-control" id="whoSearch" style="height:35px;">
   							 <sf:option value="male"><s:message code="whoSearch.male"/></sf:option>
   							 <sf:option value="female"><s:message code="whoSearch.female"/></sf:option>
   							 <sf:option value="gender"><s:message code="whoSearch.gender"/></sf:option>
							</sf:select>
				  		</div>
			  			    <div class="form-group">
			  				<label for ="description"]><s:message code="register.description"/></label>
			  			 	<sf:textarea path="description" class="form-control" id="description"></sf:textarea>
			  			</div>
            		</div>
          
           
			  			<div>
			  				<button type="submit"  value=<s:message code="register.accept"/> class="btn btn-default submit"style="margin-top=50px;"><i class="fa fa-paper-plane" aria-hidden="true"><s:message code="register.accept"/></i></button>
			  			</div>
			  			
					</div>
					</div>
				
				</form>
			</div>
		</section>

		</sf:form>
		
</body>
</html>