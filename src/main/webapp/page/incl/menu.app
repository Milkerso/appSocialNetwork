
<header>
	
		<nav class="navbar bg-jumpers navbar-expand-lg">
		
			<a class="nav-link" href="/index"><span style="color:white" class="fa fa-futbol-o fa-2x"></span> <font color="white"><s:message code="menu.mainPage"/></font></a>
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu" aria-controls="mainmenu" aria-expanded="false" aria-label="Przełącznik nawigacji">
				<span style="color:white"class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mainmenu">
				<sec:authorize access="isAuthenticated()">
			<a class="nav-link" href="/addpost"><span style="color:white" class="fa fa-comments-o fa-2x"></span> <font color="white">Dodaj post</font></a>
			<a class="nav-link" href="/allpeople"><span style="color:white" class="fa fa-user fa-2x"></span> <font color="white">Osoby</font></a>
		    <a class="nav-link" href="/allpost"><span style="color:white" class="fa fa-users fa-2x"></span> <font color="white">Grupy</font></a>
		    <a class="nav-link" href="/places"><span style="color:white" class="fa fa-map-marker fa-2x"></span> <font color="white">Miejsca</font></a>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="nav-link" href="/admin/addplace"><span style="color:white" class="fa fa-plus fa-2x"></span> <font color="white">Dodaj miejsca</font></a>
            </sec:authorize>


				</sec:authorize>
			
				
				<form class="form-inline mr-auto">
				
					
				
				</form>			
			
				<ul class="navbar-nav">
				
					<li class="nav-item active">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a style="color:white" class="nav-link" href="/admin"><span style="color:white; margin-right:5px;" class="fa fa-cogs fa-2x"></span><s:message code="menu.adminPage"/></a>
						</sec:authorize>
					</li>
					
					
					<sec:authorize access="hasRole('ANONYMOUS')">
					<li class="nav-item">
						<a style="color:white" class="nav-link" href="/login"><span style="color:white; margin-right:5px;" class="fa fa-sign-in fa-2x"></span><s:message code="menu.login"/></a>
					</li>
					
					<li class="nav-item">
						<a style="color:white" class="nav-link" href="/register"><span style="color:white; margin-right:5px;" class="fa fa-user-plus fa-2x"></span><s:message code="menu.register"/></a>&nbsp;&nbsp;
					</li>
						</sec:authorize>
						
					<sec:authorize access="isAuthenticated()">

					<li class="nav-item">
						<a style="color:white" class="nav-link" href="/profil"><span style="color:white; margin-right:5px;" class="fa fa-user-circle-o fa-2x"></span><s:message code="menu.profil"/></a>
					</li>
					<li class="nav-item">
						<a style="color:white" class="nav-link" href="/logout"><span style="color:white; margin-right:5px;" class="fa fa-sign-out fa-2x"></span><s:message code="menu.logout"/></a>
					</li>
						</sec:authorize>


				</ul>
			
				
			
			</div>
		
		</nav>
	
	</header>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	
<script src="/resources/css/js/bootstrap.min.js"></script>
