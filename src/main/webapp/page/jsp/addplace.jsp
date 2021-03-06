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
<sf:form id="usersForm" action="addnewplace" modelAttribute="place"
         enctype="multipart/form-data" method="POST">

    <sf:hidden path="id"/>
    <section id="contact">
        <div class="section-content">
            <h1><s:message code="place.addPlace"/></h1>
        </div>
        <div class="contact-section">
            <div class="container">
                <form>
                    <div class="col-md-4 form-line">
                        <div class="form-group">
                            <div class="form-group">
                                <label for="telephone"><s:message code="place.title"/></label>
                                <sf:input path="title" type="text" class="form-control" id="title"/>
                            </div>
                            <div class="form-group">
                                <label for="telephone"><s:message code="place.number"/></label>
                                <sf:input path="number" type="tel" class="form-control" id="number"/>
                            </div>
                            <div class="form-group">
                                <label for="telephone"><s:message code="place.email"/></label>
                                <sf:input path="email" type="email" class="form-control" id="email"/>
                            </div>

                            <div class="form-group">
                                <label for="telephone"><s:message code="place.address"/></label>
                                <sf:input path="address" type="text" class="form-control" id="address"/>
                            </div>




                        </div>
                    </div>
                    <div class="col-md-4 form-line">
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
                                <sf:select multiple="true" path="physicalActivity" class="form-control"  style="height:70px;" id="physicalActivity">
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
                            <div class="form-group">
                                <label for="telephone"><s:message code="place.link"/></label>
                                <sf:input path="link" type="text" class="form-control" id="link"/>
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





                    <div class="col-md-4">

                        <div class="form-group">
                            <label for="exampleInputUsername"><s:message code="place.photo"/></label>


                            <div class="form-group">
                                <div class="profile-img" >
                                    <div class="file btn btn-lg btn-primary">
                                        <s:message code="place.changePhoto" />
                                        <sf:input type="file" id="photo" path="photo" name="photo"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for ="description"]><s:message code="place.description"/></label>
                                <sf:textarea path="description" class="form-control" id="description"></sf:textarea>
                            </div>
                        </div>
                        <div>
                            <button type="submit" value=<s:message code="register.accept"/> class="btn btn-default submit"style="margin-top=50px;"><i class="fa fa-paper-plane" aria-hidden="true"><s:message code="register.accept"/></i></button>
                        </div>

                    </div>
            </div>

            </form>
        </div>
    </section>

</sf:form>

</body>
</html>