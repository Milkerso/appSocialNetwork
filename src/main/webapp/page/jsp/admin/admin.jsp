<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<title><s:message code="menu.adminPage"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>
<h2><s:message code="menu.adminPage"/></h2>
<%@include file="/page/incl/admenu.app" %>
</body>
</html>