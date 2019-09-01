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
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <title><s:message code="profilEdit.pageName"/></title>

</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="#"
      crossorigin="anonymous">

<form action="/addnewpost/${groupId}" th:action="@{/addnewpost/${groupid}}" th:object="${post}" method="post">
    <div class="container-fluid">
        <div class="row">
            <%@include file="/page/incl/addpostallgroup.app" %>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 border-right">
                                <h4 style="font-weight: 600;"><c:out value="${allGroup.name }"/></h4>
                            </div>


                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">


                                <div class="form-group row">

                                    <label for="text" style="font-weight: 600;" class="col-12 col-form-label">Tytuł</label>
                                    <div class="col-12">
                                        <input style="height: 30px; border-radius: 7px 7px 7px 7px;" id="text"
                                               th:field="*{postTitle}" name="postTitle" class="form-control here"
                                               required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label style="font-weight: 600; border-radius: 7px 7px 7px 7px;" for="textarea"
                                           class="col-12 col-form-label">Treść postu</label>
                                    <div class="col-12">
                                        <textarea id="content" name="content" th:field="*{content}" cols="40" rows="5"
                                                  class="form-control"></textarea>

                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <input type="submit" style="height:30px; float: right;
															clear: both;" class="btn btn-primary" value="Dodaj">
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</div>
</body>