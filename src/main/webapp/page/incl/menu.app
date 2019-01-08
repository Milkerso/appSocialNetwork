
<header>
	
		<nav class="navbar navbar-dark bg-jumpers navbar-expand-lg">
		
			<a class="nav-link" href="/index"><img src="/resources/images/logo.png" width="50" height="50" > <font color="white"><s:message code="menu.mainPage"/></font></a>
		
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu" aria-controls="mainmenu" aria-expanded="false" aria-label="Przełącznik nawigacji">
				<span class="navbar-toggler-icon"></span>
			</button>
		
			<div class="collapse navbar-collapse" id="mainmenu">
				
				<form class="form-inline mr-auto">
				
					<input class="form-control mr-1" type="search" placeholder="Wyszukaj" aria-label="Wyszukaj">
					<button class="btn btn-light" type="submit">Znajdź</button>
				
				</form>			
			
				<ul class="navbar-nav">
				
					<li class="nav-item active">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a class="nav-link" href="/admin"><s:message code="menu.adminPage"/></a>
						</sec:authorize>
					</li>
					
					
					<sec:authorize access="hasRole('ANONYMOUS')">
					<li class="nav-item">
						<a class="nav-link" href="/login"><s:message code="menu.login"/></a>&nbsp;&nbsp;
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="/register"><s:message code="menu.register"/></a>&nbsp;&nbsp;
					</li>
						</sec:authorize>
						
					<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="/profil"><s:message code="menu.profil"/></a>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="/logout"><s:message code="menu.logout"/></a>
					</li>
						</sec:authorize>
			
				
				</ul>
			
				
			
			</div>
		
		</nav>
	
	</header>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	
<script src="/resources/css/js/bootstrap.min.js"></script>
