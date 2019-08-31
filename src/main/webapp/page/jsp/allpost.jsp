
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/allpoststyle.css" />
<title><s:message code="profilEdit.pageName"/></title>

</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="#" crossorigin="anonymous">


<div class="container-fluid">
	<div class="row">
<%@include file="/page/incl/allgroup.app" %><div class="container">

	<h2 class="text-center"> <c:out value="${allGroup.name }"/></h2>

 <c:forEach var="post" items="${posts }">
	<div style="margin-bottom:10px;"class="card">
	    <div class="card-body">
	        <div class="row">
        	    <div class="col-md-2">
        	        <img src="data:image/jpeg;base64,${post.photoEncoded}" class="img img-rounded img-fluid"/>
        	        <p class="text-secondary text-center"><c:out value="${post.timeAgo }"/></p>
					<p><a href="/otherprofile/${post.postUserId.userProfile.id}"><strong><c:out value="${post.postUserId.userProfile.name }"/> <c:out value="${post.postUserId.userProfile.lastName }"/></strong></a></p>
				</div>
        	    <div class="col-md-10">
        	        <p>
        	            <p class="float-left"> <strong> <c:out value="${post.postTitle }"/></strong></p>
        	       </p>
        	       <div class="clearfix"></div>
        	        <p> <c:out value="${post.content }"/></p>
        	        <p>
        	            <a class="float-right btn text-white btn-danger"> <i class="fa fa-heart"></i>Like</a>
        	       </p>
        	    </div>
	        </div>
	           <c:forEach var="comment" items="${post.comment }">
	        	<div class="card card-inner">
            	    <div class="card-body">
            	        <div class="row">
                    	    <div class="col-md-2">
								<img src="data:image/jpeg;base64,${comment.photoEncoded}" class="img img-rounded img-fluid"/>
								<p class="text-secondary text-center"><c:out value="${comment.commentTimeAgo }"/></p>
								<p><a href="/profil"><strong><c:out value="${comment.commentUserId.userProfile.name }"/> <c:out value="${comment.commentUserId.userProfile.lastName }"/></strong></a></p>

                    	    </div>
                    	    <div class="col-md-10">
                    	        <p><a href="https://maniruzzaman-akash.blogspot.com/p/contact.html"><strong>Maniruzzaman Akash</strong></a></p>
                                 <p> <c:out value="${comment.commentContent }"/></p>

                    	        <p>
                    	            <a class="float-right btn btn-outline-primary ml-2">  <i class="fa fa-reply"></i> Reply</a>
                    	            <a class="float-right btn text-white btn-danger"> <i class="fa fa-heart"></i> Like</a>
                    	       </p>
                    	    </div>
            	        </div>
            	    </div>
	            </div>
	        </c:forEach>
   	<form action="/addcomment/${post.id}"th:action="@{/addcomment/${post.id}}" th:object="${comment}" method="post">


             <div class="col-12">
                <textarea style="margin-top:10px;"id="commentContent" name="commentContent" th:field="*{commentContent}" cols="40" rows="5" class="form-control"></textarea>

                    </div>

                    <div class="col-md-12" >
                        <input type="submit" style="height:30px; margin-top:7px; margin-right:20px;" class="btn btn-sm btn-primary pull-right" value="Dodaj post">
                    </div>

	    </div>
	</div>
	</form>
	         </c:forEach>
</div>
</div>
</div>

</body>