<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/index.css" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great&display=swap&subset=latin-ext" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title><s:message code="menu.mainPage"/></title>
</head>
<body background="/resources/images/background.jpg">
<%@include file="/page/incl/menu.app" %>

<h3 align="center"><c:out value="${message }" /></h3>

<!-- Swiper Silder
    ================================================== --> 
<!-- Slider main container -->
<div class="swiper-container main-slider" id="myCarousel">
  <div class="swiper-wrapper">
    <div class="swiper-slide slider-bg-position" style="background:url('/resources/images/tlo2.jpg')" data-hash="slide1" >
      <h2>Nudzi Cię aktywny tryb życia w pojedynkę?</h2>
    </div>
    <div class="swiper-slide slider-bg-position" style="background:url('/resources/images/tlo1.jpg')" data-hash="slide2">
      <h2>To dobrze trafiłeś z nami nie będziesz się nudził</h2>
    </div>
  </div>
  <!-- Add Pagination -->
  <div class="swiper-pagination"></div>
  <!-- Add Navigation -->
  <div class="swiper-button-prev"><i class="fa fa-chevron-left"></i></div>
  <div class="swiper-button-next"><i class="fa fa-chevron-right"></i></div>
</div>

<!-- Benefits
    ================================================== -->
<section class="service-sec" id="benefits">
  <div  style="margin-bottom: -50px;" class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="heading">
      <h2><small style="color: white;">Aktywny tryb życia</small>Dla dobrego samopoczucia i zdrowego trybu życia aktywność fizyczna jest niezbędna</h2>
    </div>
        </div>
      <div class="col-md-8">
        <div class="row">
            <div class="col-md-6 text-sm-center service-block"> <i style="color: white;" class="fa fa-plus" aria-hidden="true"></i>
          <h3 style="color: white;">Lepszy sen</h3>
          <p style="color: white;">Wysiłek fizyczny budzi Twoje ciało, przez co potrzebujesz regeneracji i masz zdrowszy sen</p>
        </div>
        <div class="col-md-6 text-sm-center service-block"> <i style="color:white;" class="fa fa-leaf" aria-hidden="true"></i>
          <h3 style="color: white;">Zmniejszenie wagi</h3>
          <p style="color: white;">Nie dość, że aktywność fizyczna wspomoaga stracenie paru zbędnych kilogramów to jeszcze rzezbi Twoje ciało by wyglądało atrakcyjniej</p>
        </div>
        <div class="col-md-6 text-sm-center service-block"> <i  style="color: white;" class="fa fa-leaf" aria-hidden="true"></i>
          <h3 style="color: white;">Wytrzymałość</h3>
          <p style="color: white;">Systematyczna aktywność fizyczna zwiększa Twoją wytrzymałość, dzięki czemu wysiłek który robiłeś kiedyś nie sprawia Ci już problemów</p>
        </div>
        <div class="col-md-6 text-sm-center service-block"> <i style="color: white;" class="fa fa-bell" aria-hidden="true"></i>
          <h3 style="color: white;">Umiejętności</h3>
          <p style="color: white;">Przez powtarzanie tych samych czynności, dochodzimy do perfekcji. Ćwicz abyś był mistrzem</p>
        </div>
        </div>
      </div>
      <div class="col-md-4"> <img src="/resources/images/exercise.jpg" class="img-fluid" /> </div>
    </div>
    <!-- /.row --> 
  </div>
</section>

<!-- About 
    ================================================== -->
<section class="about-sec parallax-section" id="about">
  <div style="margin-top: -50px;" class="container">
    <div class="row">
      <div class="col-md-3">
        <h2><small>Kim jesteśmy</small>O<br>
          Serwisie</h2>
      </div>
      <div class="col-md-4">
        <p>Nie masz motywacji do aktywnego trybu życia bądz nudzi Cię to, że nie masz z kim się podzielić sukcesami i porażkami?</p>
        <p>Mamy dla Ciebie rozwiązanie, to właśnie u nas znajdziesz partnera do wspólnego biegania, spacerowania, na siłownie</p>
      </div>
      <div class="col-md-4">
        <p>Lubisz uprawiać sporty grupowę, lecz zawsze jest ciężko zebrać całą drużynę? Albo nawet jak przyjdą to trzeba ich namawiać i nie mają motywacji do gry?</p>
        <p>Dzięki nowemu algorytmowi, dopasujemy Cię od razu do nowej drużyny, wystarczy się zarejestrować i przekazać nam swoje zainteresowania</p>
        <p><a href="/register" class="btn btn-transparent-white btn-capsul">Zarejestruj się</a></p>
      </div>
    </div>
  </div>
</section>


<script src="/resources/script/myScript.js"></script>
</body>
</html>